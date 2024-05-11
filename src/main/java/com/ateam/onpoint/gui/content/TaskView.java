package com.ateam.onpoint.gui.content;

import com.ateam.onpoint.core.TaskManager;
import com.ateam.onpoint.gui.components.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * The task view is responsible for the nodes used to display tasks.
 */
public class TaskView implements IContent {
    private static TaskList taskList;

    public TaskView() {
        if (taskList == null) {
            taskList = new TaskList();
            taskList.setCellFactory(p -> new TaskList.TaskCell());
            taskList.setPrefWidth(400);

            TaskManager.getInstance().tasksListAddListener(change -> {
                while (change.next()) {
                    if (change.wasAdded()) {
                        int st = change.getFrom();
                        int end = change.getTo();
                        for (int i = st; i < end; i++) {
                            taskList.getItems().add(i);
                        }
                    }
                }
            });
        }
    }
    /**
     * build and return the content view for the task system
     * @return the parent node for the task system
     */
    @Override
    public Parent getContentView() {
        VBox parent = new VBox();
        parent.setPadding(new Insets(0, 0, 0, 10));

        ContentHeader header = new ContentHeader("Tasks");

        ToolBar toolbar = new ToolBar();
        toolbar.setPrefWidth(400);
        toolbar.setPrefHeight(40);

        Button newButton = new Button();
        Image plusIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/plus_white_32.png")));
        ImageView plusIconView = new ImageView(plusIcon);
        newButton.setGraphic(plusIconView);
        newButton.setPrefSize(24, 24);
        newButton.setPadding(new Insets(1, 1, 1, 1));

        EventHandler<ActionEvent> addTaskButtonEventHandler = e -> {
            System.out.println("added 1");
            TaskManager.getInstance().addTask(new TaskManager.Task());
            taskList.getSelectionModel().selectLast();
            taskList.setAddedNewTask();
        };

        newButton.setOnAction(addTaskButtonEventHandler);

        Button archiveButton = new Button();
        Image archiveIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/archive_white_24.png")));
        ImageView archiveIconView = new ImageView(archiveIcon);
        archiveButton.setGraphic(archiveIconView);
        archiveButton.setPrefSize(24, 24);
        archiveButton.setPadding(new Insets(1, 1, 1 , 1));

        toolbar.getItems().addAll(newButton, archiveButton);

        parent.getChildren().addAll(header, toolbar, taskList);
        return parent;
    }
}
