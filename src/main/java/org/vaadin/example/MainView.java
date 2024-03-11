package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed
     *                bean.
     */
    public MainView(@Autowired GreetService service) {

//        // Use TextField for standard text input
//        TextField textField = new TextField("Your name");
//        textField.addThemeName("bordered");
//
//        // Button click listeners can be defined as lambda expressions
//        Button button = new Button("Say hello",
//                e -> Notification.show(service.greet(textField.getValue())));
//
//        // Theme variants give you predefined extra styles for components.
//        // Example: Primary button has a more prominent look.
//        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//
//        // You can specify keyboard shortcuts for buttons.
//        // Example: Pressing enter in this view clicks the Button.
//        button.addClickShortcut(Key.ENTER);
//
//        // Use custom CSS classes to apply styling. This is defined in
//        // shared-styles.css.
//        addClassName("centered-content");

        Tab tab1 = new Tab("");
//        tab1.add(new Label("components on page 1"));

        Tab tab2 = new Tab("");
//        tab2.add(new Button("components on page 2"));

        Tab tab3 = new Tab("");
//        tab3.add(new TextField("components on page 3"));

        Tabs tabs = new Tabs(tab1, tab2, tab3);
        tabs.setSizeFull();

        Button prevButton = new Button("< Prev");
        Button nextButton = new Button("Next >");

        prevButton.addClickListener(event -> {
            prevButton.setEnabled(tabs.getSelectedIndex() == 2);
            nextButton.setEnabled(tabs.getSelectedIndex() == 0);

            tabs.setSelectedIndex(tabs.getSelectedIndex() - 1);
        });
        nextButton.addClickListener(event -> {
            prevButton.setEnabled(tabs.getSelectedIndex() == 2);
            nextButton.setEnabled(tabs.getSelectedIndex() == 0);

            tabs.setSelectedIndex(tabs.getSelectedIndex() + 1);
        });
        Button close = new Button("Close");

        HorizontalLayout buttons = new HorizontalLayout(prevButton, nextButton, close);
        buttons.setHeight("50px");

        VerticalLayout content = new VerticalLayout(tabs, buttons);

        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("multi-step dialog");
        dialog.add(content);

        Button start = new Button("show multi-step dialog");
        start.addClickListener(event -> {
            dialog.open();
        });
        close.addClickListener(event -> {
            dialog.close();
        });

        add(start);
    }
}
