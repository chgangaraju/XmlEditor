package com.leantaas.javafx;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Testsuite testsuite = XMLReader.getObjectFromXml();
		VBox vbox = createTestsuite(testsuite);
		primaryStage.setTitle("XML Editor");
		primaryStage.setScene(new Scene(vbox, 890, 770));
		primaryStage.show();
		dialog = new AlertDialog(primaryStage,"Changes are written to XML file");
	}

	private VBox createTestsuite(final Testsuite testsuite) {
		final TestsuiteComponent component = new TestsuiteComponent(testsuite);
		CustomGridPane grid = createTestsuiteFields(component);
		final VBox testsuiteVBox = new VBox();
		testsuiteVBox.getChildren().add(grid);
		for (int i = 0; i < testsuite.getTestcase().size(); i++) {
			testsuiteVBox.getChildren().add(createTestcase(testsuite.getTestcase().get(i), component.getTestcase().get(i)));
		}
		createTestcaseAddButton(testsuite,component,testsuiteVBox);
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setContent(testsuiteVBox);
		scrollpane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollpane.setMinSize(880, 700);
		Label testsuiteLabel = new Label("Testsuite");
		CustomTitledPane titledPane = new CustomTitledPane(scrollpane,testsuiteLabel, component.getButton(), 760);
		HBox hbox=createSubmitButton(testsuite, component);
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

	private void createTestcaseAddButton(final Testsuite testsuite,final TestsuiteComponent component,final VBox testsuiteVBox) {
		Button testcaseAddButtton = component.getButton();
		testcaseAddButtton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Testcase testcase = new Testcase();
				TestcaseComponent testcaseComponent = new TestcaseComponent();
				testsuite.addTestcase(testcase);
				component.addTestcase(testcaseComponent);
				testsuiteVBox.getChildren().add(
						createTestcase(testcase, testcaseComponent));
			}
		});
	}
	
	private HBox createSubmitButton(final Testsuite testsuite,final TestsuiteComponent component) {
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

	private void saveTextFieldsData(Testsuite testsuite,TestsuiteComponent testsuiteComponent) {
		testsuite.setName(testsuiteComponent.getName().getText().toString());
		testsuite.setNodeOrder(testsuiteComponent.getNodeOrder().getText().toString());
		testsuite.setDetails(testsuiteComponent.getDetails().getText().toString());
		for (int i = 0; i < testsuite.getTestcase().size(); i++) {
			Testcase testcase = testsuite.getTestcase().get(i);
			TestcaseComponent component = testsuiteComponent.getTestcase().get(i);
			testcase.setExecutionType(component.getExecutionType().getText().toString());
			testcase.setExternalid(component.getExternalid().getText().toString());
			testcase.setImportance(component.getImportance().getText().toString());
			testcase.setInternalid(component.getInternalid().getText().toString());
			testcase.setName(component.getName().getText().toString());
			testcase.setNodeOrder(component.getNodeOrder().getText().toString());
			testcase.setPreconditions(component.getPreconditions().getText().toString());
			testcase.setSummary(component.getSummary().getHtmlText().toString());
			testcase.setVersion(component.getVersion().getText().toString());
			for (int j = 0; j < testcase.getSteps().getStep().size(); j++) {
				Step step = testcase.getSteps().getStep().get(j);
				StepComponent stepComponent = component.getSteps().getStep().get(j);
				step.setActions(stepComponent.getActions().getHtmlText().toString());
				step.setExecutionType(stepComponent.getExecutionType().getText().toString());
				step.setExpectedresults(stepComponent.getExpectedresults().getHtmlText().toString());
				step.setStepNumber(stepComponent.getStepNumber().getText().toString());
			}
		}
		try {
			XMLReader.setObjectToXml(testsuite);
			dialog.showAndWait();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception:", e.getMessage());
		}
	}

	private VBox createTestcase(final Testcase testcase,final TestcaseComponent component) {
		final VBox vbox = new VBox();
		final VBox TestcaseVBox = new VBox();
		CustomGridPane grid = createTestcaseFields(component);
		TestcaseVBox.getChildren().add(grid);
		final VBox stepVBox =createStep(testcase.getSteps(), component.getSteps());
		Label stepLabel = new Label("Steps");
		CustomTitledPane stepPane = new CustomTitledPane(stepVBox,stepLabel, component.getSteps().getButton(), 750);
		TestcaseVBox.getChildren().add(stepPane);
		VBox.setMargin(vbox, new Insets(5));
		TitledPane titledPane = new TitledPane("Testcase", TestcaseVBox);
		vbox.getChildren().add(titledPane);
		return vbox;
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
	
	private VBox createStep(final Steps steps,final StepsComponent component) {
		final VBox stepVBox = new VBox();
		for (int i = 0; i < steps.getStep().size(); i++) {
			stepVBox.getChildren().add(createStepFields(component.getStep().get(i)));
		}
		Button stepAddButtton = component.getButton();
		stepAddButtton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Step step = new Step();
				StepComponent stepComponent = new StepComponent();
				steps.addStep(step);
				component.addStep(stepComponent);
				stepVBox.getChildren().add(createStepFields(stepComponent));
			}
		});
		return stepVBox;
	}

	private VBox createStepFields(StepComponent component) {
		final VBox vbox = new VBox();
		CustomGridPane grid = new CustomGridPane();

		grid.add(new Label("step_number"), 0, 0);
		grid.add(component.getStepNumber(), 1, 0);

		grid.add(new Label("actions"), 0, 1);
		grid.add(component.getActions(), 1, 1);

		grid.add(new Label("expectedresults"), 0, 2);
		grid.add(component.getExpectedresults(), 1, 2);

		grid.add(new Label("execution_type"), 0, 3);
		grid.add(component.getExecutionType(), 1, 3);

		TitledPane titledPane = new TitledPane("Step", grid);
		vbox.getChildren().setAll(titledPane);
		return vbox;
	}

}
