package GUI.Buttons;


import GUI.HintTextField;
import Logic.Borrower;
import Logic.DB;
import Logic.Library;

import javax.swing.*;
import java.awt.*;


public class RegisterOneBorrowerButton extends JButton {
    private JFrame frame;
    private Library lib;

    public RegisterOneBorrowerButton(JFrame frame, Library lib) {
        this.frame = frame;
        this.lib = lib;
        
        this.setText("Register one borrower");
        this.addActionListener(e -> {
            ThisDialog thisDialog = new ThisDialog();

            if (!thisDialog.getNameField().getText().equals("")) {
                lib.registerOneBorrwer(thisDialog.getNameField().getText());
            }

        });
    }

    private class ThisDialog extends JDialog {
        private JTextField name;
        private JButton okButton;

        private ThisDialog() {
            super(frame, true);
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Register new borrower here"));
            this.setSize(250, 200);
            this.setResizable(false);

            name = new HintTextField("Name", 20);
            this.add(name);

            okButton = new JButton("OK");
            okButton.addActionListener(e -> setVisible(false));
            this.add(okButton);
            setVisible(true);
        }

        public JTextField getNameField() {
            return name;
        }
    }

}
