package com.leantaas.javafx;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.leantaas.beans.Step;
import com.leantaas.beans.Steps;
import com.leantaas.beans.Testcase;
import com.leantaas.beans.Testsuite;
import com.leantaas.components.StepComponent;
import com.leantaas.components.StepsComponent;
import com.leantaas.components.TestcaseComponent;
import com.leantaas.components.TestsuiteComponent;
import com.leantaas.xmlhelpers.XMLReader;

public class JavaFXApplication extends Application {
	private final Logger LOGGER = Logger.getLogger(JavaFXApplication.class.getName());
	private AlertDialog dialog;
	private File file;
	private ObjectProperty<TitledPane> draggingTab;
	private static final String TITLEDPANE_DRAG_KEY = "titledpane";
	private TestsuiteComponent component;
	private Testsuite testsuite;
	private VBox testsuiteVBox;
	private VBox testcaseVBox;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {
		testcaseVBox = new VBox();
		draggingTab = new SimpleObjectProperty<TitledPane>();
		Button chooseXml = new Button("Choose XML File");
		primaryStage.setTitle("XML Editor");
		VBox chooseXmlVbox = new VBox();
		chooseXmlVbox.getChildren().add(chooseXml);
		chooseXmlVbox.setAlignment(Pos.CENTER);
		primaryStage.setScene(new Scene(chooseXmlVbox, 890, 570));
		primaryStage.show();
		chooseXml.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
						"XML Files (*.xml)", "*.xml");
				fileChooser.getExtensionFilters().add(extFilter);
				file = fileChooser.showOpenDialog(null);
				try {
					testsuite = XMLReader.getObjectFromXml(file);
					VBox vbox = createTestsuite(testsuite);
					primaryStage.setScene(new Scene(vbox, 890, 570));
					primaryStage.show();
					dialog = new AlertDialog(primaryStage,
							"Changes are written to XML file");
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Exception:", e.getMessage());
				}
			}
		});
	}

	private VBox createTestsuite(final Testsuite testsuite) {
		component = new TestsuiteComponent(testsuite);
		CustomGridPane grid = createTestsuiteFields(component);
		testsuiteVBox = new VBox();
		testsuiteVBox.getChildren().add(grid);
		for (int i = 0; i < testsuite.getTestcase().size(); i++) {
			createTestcase(testsuite.getTestcase().get(i), component
					.getTestcase().get(i));
		}
		testsuiteVBox.getChildren().add(testcaseVBox);
		createTestcaseAddButton(testsuite, component, testsuiteVBox);
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setContent(testsuiteVBox);
		scrollpane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollpane.setMinSize(880, 500);
		Label testsuiteLabel = new Label("Testsuite");
		final CustomTitledPane titledPane = new CustomTitledPane(scrollpane,
				testsuiteLabel, component.getButton(), 760);
		HBox hbox = createSubmitButton(testsuite, component);
		final VBox vbox = new VBox();
		vbox.getChildren().add(titledPane);
		vbox.getChildren().add(hbox);
		return vbox;
	}

	private CustomGridPane createTestsuiteFields(TestsuiteComponent component) {
		CustomGridPane grid = new CustomGridPane();
		grid.add(new Label("name:"), 0, 0);
		grid.add(component.getName(), 1, 0);
		grid.add(new Label("details:"), 0, 1);
		grid.add(component.getDetails(), 1, 1);
		grid.add(new Label("node_order:"), 0, 2);
		grid.add(component.getNodeOrder(), 1, 2);
		return grid;
	}

	private void createTestcaseAddButton(final Testsuite testsuite,
			final TestsuiteComponent component, final VBox testsuiteVBox) {
		Button testcaseAddButtton = component.getButton();
		testcaseAddButtton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Testcase testcase = new Testcase();
				TestcaseComponent testcaseComponent = new TestcaseComponent();
				testsuite.addTestcase(testcase);
				component.addTestcase(testcaseComponent);
				createTestcase(testcase, testcaseComponent);
			}
		});
	}

	private HBox createSubmitButton(final Testsuite testsuite,
			final TestsuiteComponent component) {
		HBox hbox = new HBox();
		Button button = new Button("Submit");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				JavaFXApplication.this.saveTextFieldsData(testsuite, component);
			}
		});
		HBox.setMargin(button, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		hbox.getChildren().add(button);
		return hbox;
	}

	private void saveTextFieldsData(Testsuite testsuite,
			TestsuiteComponent testsuiteComponent) {
		testsuite.setName(testsuiteComponent.getName().getText().toString());
		testsuite.setNodeOrder(testsuiteComponent.getNodeOrder().getText()
				.toString());
		testsuite.setDetails(testsuiteComponent.getDetails().getText()
				.toString());
		for (int i = 0; i < testsuite.getTestcase().size(); i++) {
			Testcase testcase = testsuite.getTestcase().get(i);
			TestcaseComponent component = testsuiteComponent.getTestcase().get(
					i);
			testcase.setExecutionType(component.getExecutionType().getText()
					.toString());
			testcase.setExternalid(component.getExternalid().getText()
					.toString());
			testcase.setImportance(component.getImportance().getText()
					.toString());
			testcase.setInternalid(component.getInternalid().getText()
					.toString());
			testcase.setName(component.getName().getText().toString());
			testcase.setNodeOrder(component.getNodeOrder().getText().toString());
			testcase.setPreconditions(component.getPreconditions().getText()
					.toString());
			testcase.setSummary(component.getSummary().getHtmlText().toString());
			testcase.setVersion(component.getVersion().getText().toString());
			for (int j = 0; j < testcase.getSteps().getStep().size(); j++) {
				Step step = testcase.getSteps().getStep().get(j);
				StepComponent stepComponent = component.getSteps().getStep()
						.get(j);
				step.setActions(stepComponent.getActions().getHtmlText()
						.toString());
				step.setExecutionType(stepComponent.getExecutionType()
						.getText().toString());
				step.setExpectedresults(stepComponent.getExpectedresults()
						.getHtmlText().toString());
				step.setStepNumber(stepComponent.getStepNumber().getText()
						.toString());
			}
		}
		try {
			XMLReader.setObjectToXml(testsuite, file);
			dialog.showAndWait();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception:", e.getMessage());
		}
	}

	private void createTestcase(final Testcase testcase,
			final TestcaseComponent testcaseComponent) {
		final VBox stepsVBox = new VBox();
		CustomGridPane grid = createTestcaseFields(testcaseComponent);
		stepsVBox.getChildren().add(grid);
		final VBox stepVBox = createStep(testcase.getSteps(),
				testcaseComponent.getSteps());
		Label stepLabel = new Label("Steps");
		CustomTitledPane stepPane = new CustomTitledPane(stepVBox, stepLabel,
				testcaseComponent.getSteps().getButton(), 750);
		stepsVBox.getChildren().add(stepPane);
		VBox.setMargin(testcaseVBox, new Insets(5));
		final TitledPane titledPane = new TitledPane("Testcase: "+testcase.getName(), stepsVBox);
		setEventHandler(titledPane);
		testcaseVBox.getChildren().add(titledPane);
	}

	private void setEventHandler(final TitledPane titledPane) {
		titledPane.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				final Dragboard dragboard = event.getDragboard();
				if (dragboard.hasString()
						&& TITLEDPANE_DRAG_KEY.equals(dragboard.getString())
						&& draggingTab.get() != null) {
					event.acceptTransferModes(TransferMode.MOVE);
					event.consume();
				}
			}
		});
		titledPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(final DragEvent event) {
				Dragboard dragBoard = event.getDragboard();
				if (dragBoard.hasString()) {
					Pane parent = (Pane) titledPane.getParent();
					Object source = event.getGestureSource();
					int sourceIndex = parent.getChildren().indexOf(source);
					int targetIndex = parent.getChildren().indexOf(titledPane);
					ArrayList<Testcase> testcases = testsuite.getTestcase();
					ArrayList<TestcaseComponent> testcaseComponents = component.getTestcase();
					if (sourceIndex < targetIndex) {
						Collections.rotate(testcases.subList(sourceIndex, targetIndex + 1), -1);
						Collections.rotate(testcaseComponents.subList(sourceIndex, targetIndex + 1), -1);
					} else {
						Collections.rotate(testcases.subList(targetIndex, sourceIndex + 1), 1);
						Collections.rotate(testcaseComponents.subList(targetIndex, sourceIndex + 1), 1);
					}
					testsuite.setTestcase(testcases);
					component.setTestcase(testcaseComponents);
					List<Node> nodes = new ArrayList<Node>(parent.getChildren());
					if (sourceIndex < targetIndex) {
						Collections.rotate(nodes.subList(sourceIndex, targetIndex + 1), -1);
					} else {
						Collections.rotate(nodes.subList(targetIndex, sourceIndex + 1), 1);
					}
					parent.getChildren().clear();
					parent.getChildren().addAll(nodes);
				}
				event.setDropCompleted(true);
				event.consume();
			}
		});
		titledPane.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard dragboard = titledPane.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent clipboardContent = new ClipboardContent();
				clipboardContent.putString(TITLEDPANE_DRAG_KEY);
				dragboard.setContent(clipboardContent);
				draggingTab.set(titledPane);
				event.consume();
			}
		});
	}

	private CustomGridPane createTestcaseFields(TestcaseComponent component) {
		CustomGridPane grid = new CustomGridPane();
		grid.add(new Label("name :"), 0, 0);
		grid.add(component.getName(), 1, 0);
		grid.add(new Label("internal_id :"), 0, 1);
		grid.add(component.getInternalid(), 1, 1);
		grid.add(new Label("node_order :"), 0, 2);
		grid.add(component.getNodeOrder(), 1, 2);
		grid.add(new Label("external_id :"), 0, 3);
		grid.add(component.getExternalid(), 1, 3);
		grid.add(new Label("version :"), 0, 4);
		grid.add(component.getVersion(), 1, 4);
		grid.add(new Label("summary :"), 0, 5);
		grid.add(component.getSummary(), 1, 5);
		grid.add(new Label("pre_conditions :"), 0, 6);
		grid.add(component.getPreconditions(), 1, 6);
		grid.add(new Label("execution_type :"), 0, 7);
		grid.add(component.getExecutionType(), 1, 7);
		grid.add(new Label("importance :"), 0, 8);
		grid.add(component.getImportance(), 1, 8);
		return grid;
	}

	private VBox createStep(final Steps steps, final StepsComponent stepsComponent) {
		final VBox stepVBox = new VBox();
		for (int i = 0; i < steps.getStep().size(); i++) {
			CustomGridPane grid=createStepFields(stepsComponent.getStep().get(i));
			TitledPane titledPane = new TitledPane("Step: "+steps.getStep().get(i).getStepNumber(), grid);
			stepVBox.getChildren().add(titledPane);
			setDragEvent(titledPane,steps,stepsComponent);
		}
		Button stepAddButtton = stepsComponent.getButton();
		stepAddButtton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Step step = new Step();
				StepComponent stepComponent = new StepComponent();
				steps.addStep(step);
				stepsComponent.addStep(stepComponent);
				CustomGridPane grid=createStepFields(stepComponent);
				TitledPane titledPane = new TitledPane("Step: ", grid);
				stepVBox.getChildren().add(titledPane);
				setDragEvent(titledPane,steps,stepsComponent);
			}
		});
		return stepVBox;
	}

	private void setDragEvent(final TitledPane titledPane,final Steps steps,final StepsComponent stepsComponent) {
		titledPane.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				final Dragboard dragboard = event.getDragboard();
				if (dragboard.hasString()
						&& TITLEDPANE_DRAG_KEY.equals(dragboard.getString())
						&& draggingTab.get() != null) {
					event.acceptTransferModes(TransferMode.MOVE);
					event.consume();
				}
			}
		});
		titledPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(final DragEvent event) {
				Dragboard dragBoard = event.getDragboard();
				if (dragBoard.hasString()) {
					Pane parent = (Pane) titledPane.getParent();
					Object source = event.getGestureSource();
					int sourceIndex = parent.getChildren().indexOf(source);
					int targetIndex = parent.getChildren().indexOf(titledPane);
					ArrayList<Step> step = steps.getStep();
					ArrayList<StepComponent> stepComponents = stepsComponent.getStep();
					if (sourceIndex < targetIndex) {
						Collections.rotate(step.subList(sourceIndex, targetIndex + 1), -1);
						Collections.rotate(stepComponents.subList(sourceIndex, targetIndex + 1), -1);
					} else {
						Collections.rotate(step.subList(targetIndex, sourceIndex + 1), 1);
						Collections.rotate(stepComponents.subList(targetIndex, sourceIndex + 1), 1);
					}
					steps.setStep(step);
					stepsComponent.setStep(stepComponents);
					List<Node> nodes = new ArrayList<Node>(parent.getChildren());
					if (sourceIndex < targetIndex) {
						Collections.rotate(nodes.subList(sourceIndex, targetIndex + 1), -1);
					} else {
						Collections.rotate(nodes.subList(targetIndex, sourceIndex + 1), 1);
					}
					parent.getChildren().clear();
					parent.getChildren().addAll(nodes);
				}
				event.setDropCompleted(true);
				event.consume();
			}
		});
		titledPane.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard dragboard = titledPane.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent clipboardContent = new ClipboardContent();
				clipboardContent.putString(TITLEDPANE_DRAG_KEY);
				dragboard.setContent(clipboardContent);
				draggingTab.set(titledPane);
				event.consume();
			}
		});
	}

	private CustomGridPane createStepFields(StepComponent component) {
		CustomGridPane grid = new CustomGridPane();
		grid.add(new Label("step_number"), 0, 0);
		grid.add(component.getStepNumber(), 1, 0);
		grid.add(new Label("actions"), 0, 1);
		grid.add(component.getActions(), 1, 1);
		grid.add(new Label("expectedresults"), 0, 2);
		grid.add(component.getExpectedresults(), 1, 2);
		grid.add(new Label("execution_type"), 0, 3);
		grid.add(component.getExecutionType(), 1, 3);
		return grid;
	}
}
