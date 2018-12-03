package GUI.Buttons;

import GUI.HintTextField;
import Logic.DB;
import Logic.Loan;

import javax.swing.*;
import java.awt.*;

public class LendOneBookButton extends JButton {
    private JFrame frame;

    public LendOneBookButton(JFrame frame) {
        this.frame = frame;
        this.setText("Lend one book");
        this.addActionListener(e -> {
            ThisDialog thisDialog = new ThisDialog();

            if (!thisDialog.getCatalogueIDField().getText().equals("") && !thisDialog.getBorrowerIDField().getText().equals("")) {
                try {
                    int catalogueID = Integer.parseInt(thisDialog.getCatalogueIDField().getText());
                    int borrowerID = Integer.parseInt(thisDialog.getBorrowerIDField().getText());
                    DB.lendOneBook(new Loan(catalogueID, borrowerID));

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }

        });
    }

    private class ThisDialog extends JDialog {
        private JTextField catalogueID;
        private JTextField borrowerID;
        private JButton okButton;

        private ThisDialog() {
            super(frame, true);
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Lend one book here"));
            this.setSize(250, 200);
            this.setResizable(false);

            catalogueID = new HintTextField("Catalogue ID", 20);
            this.add(catalogueID);

            borrowerID = new HintTextField("Borrower ID", 20);
            this.add(borrowerID);

            okButton = new JButton("OK");
            okButton.addActionListener(e -> setVisible(false));
            this.add(okButton);
            setVisible(true);
        }

        public JTextField getCatalogueIDField() {
            return catalogueID;
        }

        public JTextField getBorrowerIDField() {
            return borrowerID;
        }
    }

}