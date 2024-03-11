package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        Tab tab1 = new Tab("Step 1");
        tabSheet.add(tab1, new Label("components on page 1"));

        Tab tab2 = new Tab("Step 2");
        tabSheet.add(tab2, new Label("components on page 2"));

        Tab tab3 = new Tab("Step 3");
        tabSheet.add(tab3, new Label("components on page 3"));

        Button prevButton = new Button("< Prev");
        Button nextButton = new Button("Next >");

        prevButton.addClickListener(event -> {
            System.out.println("prev button " + tabSheet.getSelectedIndex());
            if (tabSheet.getSelectedIndex() > 0) {
                tabSheet.setSelectedIndex(tabSheet.getSelectedIndex() - 1);
            }
            prevButton.setEnabled(tabSheet.getSelectedIndex() > 0);
            nextButton.setEnabled(tabSheet.getSelectedIndex() < 2);
        });
        nextButton.addClickListener(event -> {
            System.out.println("next button " + tabSheet.getSelectedIndex());
            if (tabSheet.getSelectedIndex() < 2) {
                tabSheet.setSelectedIndex(tabSheet.getSelectedIndex() + 1);
            }
            prevButton.setEnabled(tabSheet.getSelectedIndex() > 0);
            nextButton.setEnabled(tabSheet.getSelectedIndex() < 2);
        });
        Button close = new Button("Close");

        HorizontalLayout buttons = new HorizontalLayout(prevButton, nextButton, close);
        buttons.setHeight("50px");

        VerticalLayout content = new VerticalLayout(tabSheet, buttons);

        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Workflow");
        dialog.add(content);

        Button start = new Button("show dialog");
        start.addClickListener(event -> {
            tabSheet.setSelectedIndex(0);
            prevButton.setEnabled(false);
            nextButton.setEnabled(true);

            dialog.open();
        });
        close.addClickListener(event -> {
            dialog.close();
        });

        add(start);
    }
}
