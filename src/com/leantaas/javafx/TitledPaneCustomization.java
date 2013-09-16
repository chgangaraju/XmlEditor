package com.leantaas.javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

public class TitledPaneCustomization extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		VBox errorPane = VBoxBuilder.create()
				.style("-fx-padding: 10")
				.spacing(10)
				.children(new Label("500: Aug 8, 12:15pm"),
						new Label("404: Aug 7, 3:27am")).build();
		

		TitledPane connectivityPane = new TitledPane("", errorPane);
		Label connectivityErrorLabel = new Label("CONNECTIVITY ERROR");
		connectivityPane.setAnimated(false);
		connectivityPane.setGraphic(HBoxBuilder.create().spacing(2)
				.alignment(Pos.CENTER_RIGHT).styleClass("header")
				.children(connectivityErrorLabel, new Button("+"))
				.build());

		HBox layout = new HBox(10);
		layout.getChildren().addAll(connectivityPane);
		layout.setPrefHeight(150);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();

	
		Pane connectivityArrow = (Pane) connectivityPane.lookup(".arrow");
		Pane connectivityTitle = (Pane) connectivityPane.lookup(".header");
		connectivityTitle.translateXProperty().bind(
				connectivityArrow.widthProperty().multiply(0).negate());
	}
}