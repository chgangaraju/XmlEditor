package com.leantaas.javafx;

import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class CustomGridPane extends GridPane {
	public CustomGridPane() {
		super();
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(80);
		this.getColumnConstraints().addAll(column1, column2);
		this.setMinWidth(600);
		this.setVgap(10);
		this.setHgap(20);
		this.setPadding(new Insets(5, 5, 5, 5));
	}
}
