package GUI.Buttons;

import Logic.DB;
import Logic.Library;

import javax.swing.*;
import java.awt.*;

public class DisplayBooksOnLoanButton extends JButton {
    private JFrame frame;
    private Library lib;

    public DisplayBooksOnLoanButton(JFrame frame, Library lib) {
        this.frame = frame;
        this.lib = lib;
        this.setText("Display books on loan");
        this.addActionListener(e -> {
            new ThisDialog(lib);
        });
    }

    private class ThisDialog extends JDialog {
        private JButton okButton;

        private ThisDialog(Library lib) {
            super(frame, true);
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Display books on loan"));
            this.setSize(850, 850);
            this.setResizable(true);

            JTextArea display = new JTextArea(30, 50);
            display.setText("catalogueID, title, author, borrowerID, name, loanStartDate, loanEndDate\n" + lib.displayBooksOnLoan());
            display.setLineWrap(true);
            display.setWrapStyleWord(true);
            display.setFont(new Font("Gulim", Font.BOLD, 20));
            display.setEditable(false);
            this.add(display);

            okButton = new JButton("Close");
            okButton.addActionListener(e -> setVisible(false));
            this.add(okButton);
            setVisible(true);
        }

    }

}
