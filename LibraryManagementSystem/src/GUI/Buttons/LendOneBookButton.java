package GUI.Buttons;

import GUI.HintTextField;
import Logic.DB;
import Logic.Library;
import Logic.Loan;

import javax.swing.*;
import java.awt.*;

public class LendOneBookButton extends JButton {
	private JFrame frame;
	private Library lib;
	
    public LendOneBookButton(JFrame frame, Library lib) {
        this.frame = frame;
        this.lib = lib;
        
        this.setText("Lend one book");
        this.addActionListener(e -> {
            ThisDialog thisDialog = new ThisDialog();

            if (!thisDialog.getCatalogueIDField().getText().equals("") && !thisDialog.getNameField().getText().equals("")) {
                try {
                    int catalogueID = Integer.parseInt(thisDialog.getCatalogueIDField().getText());
                    String name = thisDialog.getNameField().getText();
                    lib.lendOneBook(catalogueID, name);
                    

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }

        });
    }

    private class ThisDialog extends JDialog {
        private JTextField catalogueID;
        private JTextField name;
        private JButton okButton;

        private ThisDialog() {
            super(frame, true);
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Lend one book here"));
            this.setSize(250, 200);
            this.setResizable(false);

            catalogueID = new HintTextField("Catalogue ID", 20);
            this.add(catalogueID);

            name = new HintTextField("Borrower name", 20);
            this.add(name);

            okButton = new JButton("OK");
            okButton.addActionListener(e -> setVisible(false));
            this.add(okButton);
            setVisible(true);
        }

        public JTextField getCatalogueIDField() {
            return catalogueID;
        }

        public JTextField getNameField() {
            return name;
        }
    }

}