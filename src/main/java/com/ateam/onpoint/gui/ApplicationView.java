package com.ateam.onpoint.gui;

import com.ateam.onpoint.gui.components.NavTree;
import com.ateam.onpoint.gui.content.IContent;
import com.ateam.onpoint.gui.content.ScheduleView;
import com.ateam.onpoint.gui.content.TaskView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Parent;

/**
 * ApplicationView contains the panel where all the application's functionality will dynamically be made available through the sidebar.
 */
public class ApplicationView {
    private IContent selectedContent;
    private final NavTree.NavItem navTreeRoot;

    public NavTree.NavItem getNavTreeRoot() {
        return navTreeRoot;
    }

    /**
     * construct the content view of the application
     */
    public ApplicationView() {
        navTreeRoot = NavTree.NavItem.makeRoot();
        navTreeRoot.getChildren().setAll(
                NavTree.NavItem.makeContent("Tasks", TaskView.class),
                NavTree.NavItem.makeContent("Schedule", ScheduleView.class)
        );
    }

    public void selectContent(IContent content) {
        selectedContent = content;
    }
}