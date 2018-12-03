package GUI;

import GUI.Buttons.*;
import Logic.DB;
import Logic.Library;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        this.setTitle(null);
        this.setLayout(new FlowLayout());
        this.setSize(350, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        InitialModalDialog initialModalDialog = new InitialModalDialog(this);
        Library library = new Library(initialModalDialog.getInput());
        DB.createNewDatabase("library.db");
        DB.createNewTable("library.db");
        System.out.printf("Successfully created Library Management System (%s)\n", library.getName());
        this.setTitle(library.getName());

        JButton registerOneBorrowerB = new RegisterOneBorrowerButton(this);
        JButton registerOneBookB = new RegisterOneBookButton(this);
        JButton displayBooksForLoanB = new DisplayBooksForLoanButton(this);
        JButton displayBooksOnLoanB = new DisplayBooksOnLoanButton(this);
        JButton lendOneBookB = new LendOneBookButton(this);
        JButton returnOneBookB = new ReturnOneBookButton(this);

        this.add(registerOneBorrowerB);
        this.add(registerOneBookB);
        this.add(displayBooksForLoanB);
        this.add(displayBooksOnLoanB);
        this.add(lendOneBookB);
        this.add(returnOneBookB);


        this.setVisible(true);
    }
}
