package com.leantaas.javafx;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBoxBuilder;

public class CustomTitledPane extends TitledPane {

	public CustomTitledPane() {
		super();
	}

	public CustomTitledPane(Node node, Label label, Button button, int space) {
		super(new String(""), node);
		this.setAnimated(false);
		this.setGraphic(HBoxBuilder.create().spacing(space)
				.alignment(Pos.CENTER_RIGHT).styleClass("header")
				.children(label, button).build());
	}
}
