package com.leantaas.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertDialog extends Stage {

	public AlertDialog(Stage owner, String message) {
		setResizable(false);
		initModality(Modality.APPLICATION_MODAL);
		initStyle(StageStyle.DECORATED);
		Label label = new Label(message);
		Button button = new Button("OK");
		HBox.setMargin(button, new Insets(10));
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AlertDialog.this.close();
			}
		});
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(label);
		BorderPane.setAlignment(label, Pos.TOP_CENTER);
		HBox hbox2 = new HBox();
		hbox2.setAlignment(Pos.CENTER);
		hbox2.getChildren().add(button);
		borderPane.setBottom(hbox2);
		Scene scene = new Scene(borderPane, 300, 80);
		scene.setFill(Color.TRANSPARENT);
		setScene(scene);
		setX(owner.getX() + (owner.getWidth() / 2 - 300 / 2));
		setY(owner.getY() + (owner.getHeight() / 2 - 80 / 2));
	}
}
