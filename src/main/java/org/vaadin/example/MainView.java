package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Workflow");

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
            if (tabSheet.getSelectedIndex() > 0) {
                tabSheet.setSelectedIndex(tabSheet.getSelectedIndex() - 1);
            }
            buttonsVisibility(tabSheet, prevButton, nextButton);
        });
        nextButton.addClickListener(event -> {
            if (tabSheet.getSelectedIndex() < 2) {
                tabSheet.setSelectedIndex(tabSheet.getSelectedIndex() + 1);
            }
            buttonsVisibility(tabSheet, prevButton, nextButton);
        });
        Button close = new Button("Close");
        close.addClickListener(event -> {
            dialog.close();
        });

        HorizontalLayout buttonsLayout = new HorizontalLayout(prevButton, nextButton, close);
        buttonsLayout.setHeight("50px");

        VerticalLayout contentLayout = new VerticalLayout(tabSheet, buttonsLayout);
        dialog.add(contentLayout);

        Button showButton = new Button("show dialog");
        showButton.addClickListener(event -> {
            tabSheet.setSelectedIndex(0);
            buttonsVisibility(tabSheet, prevButton, nextButton);

            dialog.open();
        });

        add(showButton);
    }

    private void buttonsVisibility(TabSheet tabSheet, Button prevButton, Button nextButton) {
        prevButton.setEnabled(tabSheet.getSelectedIndex() > 0);
        nextButton.setEnabled(tabSheet.getSelectedIndex() < 2);
    }
}
