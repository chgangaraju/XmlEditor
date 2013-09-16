package com.leantaas.javafx;

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
import com.leantaas.beans.Testcase;
import com.leantaas.beans.Testsuite;
import com.leantaas.components.StepComponent;
import com.leantaas.components.TestcaseComponent;
import com.leantaas.components.TestsuiteComponent;

public class JavaFXApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Testsuite testsuite = JAXBExample.getObjectFromXml();
		CustomTitledPane pane = createTestsuite(testsuite);
		HBox layout = new HBox(10);
		layout.getChildren().addAll(pane);
		primaryStage.setTitle("XML Editor");
		primaryStage.setScene(new Scene(layout, 660, 770));
		primaryStage.show();		
	}

	private CustomTitledPane createTestsuite(final Testsuite testsuite) {
		ScrollPane scrollpane = new ScrollPane();
		CustomGridPane grid = new CustomGridPane();
		final TestsuiteComponent component = new TestsuiteComponent(testsuite);
		grid.add(new Label("name:"), 0, 0);
		grid.add(component.getName(), 1, 0);

		grid.add(new Label("details:"), 0, 1);
		grid.add(component.getDetails(), 1, 1);

		grid.add(new Label("node_order:"), 0, 2);
		grid.add(component.getNodeOrder(), 1, 2);

		final VBox testsuiteVBox = new VBox();
		testsuiteVBox.getChildren().add(grid);
		for (int i = 0; i < testsuite.getTestcase().length; i++) {
			testsuiteVBox.getChildren().add(
					createTestcase(testsuite.getTestcase()[i],
							component.getTestcase()[i]));
		}
		Button button = new Button("Submit");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				JavaFXApplication.this.handle(testsuite, component);
			}
		});
		HBox hbox = new HBox();
		HBox.setMargin(button, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		hbox.getChildren().add(button);
		testsuiteVBox.getChildren().add(hbox);
		scrollpane.setContent(testsuiteVBox);
		scrollpane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollpane.setMinSize(600, 700);
		Label label = new Label("Testsuite");
		Button plus=new Button("+");
		CustomTitledPane pane = new CustomTitledPane(scrollpane, label,plus);
		return pane;
	}

	private void handle(Testsuite testsuite, TestsuiteComponent component) {
		testsuite.setName(component.getName().getText().toString());
		testsuite.setNodeOrder(component.getNodeOrder().getText().toString());
		testsuite.setDetails(component.getDetails().getText().toString());
		for (int i = 0; i < testsuite.getTestcase().length; i++) {
			Testcase testcase = testsuite.getTestcase()[i];
			TestcaseComponent testcaseComponent = component.getTestcase()[i];
			testcase.setExecutionType(testcaseComponent.getExecutionType()
					.getText().toString());
			testcase.setExternalid(testcaseComponent.getExternalid().getText()
					.toString());
			testcase.setImportance(testcaseComponent.getImportance().getText()
					.toString());
			testcase.setInternalid(testcaseComponent.getInternalid().getText()
					.toString());
			testcase.setName(testcaseComponent.getName().getText().toString());
			testcase.setNodeOrder(testcaseComponent.getNodeOrder().getText()
					.toString());
			testcase.setPreconditions(testcaseComponent.getPreconditions()
					.getText().toString());
			testcase.setSummary(testcaseComponent.getSummary().getText()
					.toString());
			testcase.setVersion(testcaseComponent.getVersion().getText()
					.toString());
			for (int j = 0; j < testcase.getSteps().getStep().length; j++) {
				Step step = testcase.getSteps().getStep()[j];
				StepComponent stepComponent = testcaseComponent.getSteps()
						.getStep()[j];
				step.setActions(stepComponent.getActions().getText().toString());
				step.setExecutionType(stepComponent.getExecutionType()
						.getText().toString());
				step.setExpectedresults(stepComponent.getExpectedresults()
						.getText().toString());
				step.setStepNumber(stepComponent.getStepNumber().getText()
						.toString());
			}
		}
		try {
			JAXBExample.setObjectToXml(testsuite);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private VBox createTestcase(Testcase testcase, TestcaseComponent component) {
		final VBox vbox = new VBox();
		final VBox TestcaseVBox = new VBox();
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
		TestcaseVBox.getChildren().add(grid);

		VBox stepVBox = new VBox();
		for (int i = 0; i < testcase.getSteps().getStep().length; i++) {
			stepVBox.getChildren().add(
					createStep(component.getSteps().getStep()[i]));
		}
		Label stepLabel=new Label("Steps");
		Button stepPlus=new Button("+");
		CustomTitledPane stepPane=new CustomTitledPane(stepVBox,stepLabel,stepPlus);
		TestcaseVBox.getChildren().add(stepPane);
		Insets insets = new Insets(5, 5, 5, 5);
		VBox.setMargin(vbox, insets);
		Label testcaseLabel=new Label("Testcase");
		Button testcasePlus=new Button("+");
		CustomTitledPane titledPane = new CustomTitledPane(TestcaseVBox,testcaseLabel,testcasePlus);
		titledPane.setMinWidth(600);
		vbox.getChildren().add(titledPane);
		return vbox;
	}

	public VBox createStep(StepComponent component) {
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

		vbox.getChildren().setAll(new TitledPane("Step", grid));
		return vbox;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
