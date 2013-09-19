package com.leantaas.javafx;

import java.util.Random;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class client extends Application {

	private static final String TAB_DRAG_KEY = "tab";
	private ObjectProperty<Tab> draggingTab;

	@Override
	public void start(Stage primaryStage) {
		draggingTab = new SimpleObjectProperty<>();
		TabPane tabPane1 = createTabPane();
		TabPane tabPane2 = createTabPane();
		VBox root = new VBox(10);
		root.getChildren().addAll(tabPane1, tabPane2);
		final Random rng = new Random();
		for (int i = 1; i <= 8; i++) {
			final Tab tab = createTab("Tab " + i);
			final StackPane pane = new StackPane();
			int red = rng.nextInt(256);
			int green = rng.nextInt(256);
			int blue = rng.nextInt(256);
			String style = String.format(
					"-fx-background-color: rgb(%d, %d, %d);", red, green, blue);
			pane.setStyle(style);
			final Label label = new Label("This is tab " + i);
			label.setStyle(String.format("-fx-text-fill: rgb(%d, %d, %d);",
					256 - red, 256 - green, 256 - blue));
			pane.getChildren().add(label);
			pane.setMinWidth(600);
			pane.setMinHeight(250);
			tab.setContent(pane);
			if (i <= 4) {
				tabPane1.getTabs().add(tab);
			} else {
				tabPane2.getTabs().add(tab);
			}
		}
		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private TabPane createTabPane() {
		final TabPane tabPane = new TabPane();
		tabPane.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				final Dragboard dragboard = event.getDragboard();
				if (dragboard.hasString()
						&& TAB_DRAG_KEY.equals(dragboard.getString())
						&& draggingTab.get() != null
						&& draggingTab.get().getTabPane() != tabPane) {
					event.acceptTransferModes(TransferMode.MOVE);
					event.consume();
				}
			}
		});
		tabPane.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				final Dragboard dragboard = event.getDragboard();
				if (dragboard.hasString()
						&& TAB_DRAG_KEY.equals(dragboard.getString())
						&& draggingTab.get() != null
						&& draggingTab.get().getTabPane() != tabPane) {
					final Tab tab = draggingTab.get();
					tab.getTabPane().getTabs().remove(tab);
					tabPane.getTabs().add(tab);
					tabPane.getSelectionModel().select(tab);
					event.setDropCompleted(true);
					draggingTab.set(null);
					event.consume();
				}
			}
		});
		return tabPane;
	}

	private Tab createTab(String text) {
		final Tab tab = new Tab();
		final Label label = new Label(text);
		tab.setGraphic(label);
		label.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard dragboard = label.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent clipboardContent = new ClipboardContent();
				clipboardContent.putString(TAB_DRAG_KEY);
				dragboard.setContent(clipboardContent);
				draggingTab.set(tab);
				event.consume();
			}
		});
		return tab;
	}
}
