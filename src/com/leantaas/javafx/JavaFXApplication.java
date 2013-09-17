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
		VBox vbox = createTestsuite(testsuite);
		primaryStage.setTitle("XML Editor");
		primaryStage.setScene(new Scene(vbox, 690, 770));
		primaryStage.show();
	}

	private VBox createTestsuite(final Testsuite testsuite) {
		final VBox vbox = new VBox();
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
		for (int i = 0; i < testsuite.getTestcase().size(); i++) {
			testsuiteVBox.getChildren().add(
					createTestcase(testsuite.getTestcase().get(i), component
							.getTestcase().get(i)));
		}
		Button[] testcaseAddButttons = new Button[testsuite.getTestcase().size()];
		for (int i = 0; i < testcaseAddButttons.length; i++) {
			testcaseAddButttons[i]=component.getTestcase().get(i).getButton();
			testcaseAddButttons[i].setOnAction(
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							Testcase testcase = new Testcase();
							TestcaseComponent testcaseComponent = new TestcaseComponent();
							testsuite.addTestcase(testcase);
							component.addTestcase(testcaseComponent);
							testsuiteVBox.getChildren()
									.add(createTestcase(testcase,
											testcaseComponent));
						}
					});
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
		scrollpane.setContent(testsuiteVBox);
		scrollpane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollpane.setMinSize(680, 700);
		vbox.getChildren().add(new TitledPane("Testsuite", scrollpane));
		vbox.getChildren().add(hbox);
		return vbox;
	}

	private void handle(Testsuite testsuite, TestsuiteComponent component) {
		testsuite.setName(component.getName().getText().toString());
		testsuite.setNodeOrder(component.getNodeOrder().getText().toString());
		testsuite.setDetails(component.getDetails().getText().toString());
		for (int i = 0; i < testsuite.getTestcase().size(); i++) {
			Testcase testcase = testsuite.getTestcase().get(i);
			TestcaseComponent testcaseComponent = component.getTestcase()
					.get(i);
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
			for (int j = 0; j < testcase.getSteps().getStep().size(); j++) {
				Step step = testcase.getSteps().getStep().get(j);
				StepComponent stepComponent = testcaseComponent.getSteps()
						.getStep().get(j);
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
			System.out.println("Changes are submitted to XML file");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private VBox createTestcase(final Testcase testcase, final TestcaseComponent component) {
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

		final VBox stepVBox = new VBox();
		for (int i = 0; i < testcase.getSteps().getStep().size(); i++) {
			stepVBox.getChildren().add(
					createStep(component.getSteps().getStep().get(i)));
		}
		Button[] stepAddButttons = new Button[testcase.getSteps().getStep().size()];
		for (int i = 0; i < stepAddButttons.length; i++) {
			stepAddButttons[i]=component.getSteps().getStep().get(i).getButton();
			stepAddButttons[i].setOnAction(
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							Step step = new Step();
							StepComponent stepComponent = new StepComponent();
							testcase.getSteps().addStep(step);
							component.getSteps().addStep(stepComponent);
							stepVBox.getChildren()
									.add(createStep(stepComponent));
							System.out.println("success");
						}
					});
		}
		TitledPane stepPane = new TitledPane("Steps", stepVBox);
		TestcaseVBox.getChildren().add(stepPane);
		Insets insets = new Insets(5, 5, 5, 5);
		VBox.setMargin(vbox, insets);
		Label testcaseLabel = new Label("Testcase");
		CustomTitledPane titledPane = new CustomTitledPane(TestcaseVBox,
				testcaseLabel, component.getButton(), 530);
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
		Label testcaseLabel = new Label("Step");
		CustomTitledPane titledPane = new CustomTitledPane(grid, testcaseLabel,
				component.getButton(), 550);
		vbox.getChildren().setAll(titledPane);
		return vbox;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
