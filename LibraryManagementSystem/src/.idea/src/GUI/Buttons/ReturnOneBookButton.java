package GUI.Buttons;

import GUI.HintTextField;
import Logic.DB;


import javax.swing.*;
import java.awt.*;

public class ReturnOneBookButton extends JButton {
    private JFrame frame;

    public ReturnOneBookButton(JFrame frame) {
        this.frame = frame;
        this.setText("Return one book");
        this.addActionListener(e -> {
            ThisDialog thisDialog = new ThisDialog();

            if (!thisDialog.getCatalogueIDField().getText().equals("")) {
                try {
                    int catalogueID = Integer.parseInt(thisDialog.getCatalogueIDField().getText());
                    DB.returnOneBook(catalogueID);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }

        });
    }

    private class ThisDialog extends JDialog {
        private JTextField catalogueID;
        private JButton okButton;

        private ThisDialog() {
            super(frame, true);
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Return one book here"));
            this.setSize(250, 200);
            this.setResizable(false);

            catalogueID = new HintTextField("Catalogue ID", 20);
            this.add(catalogueID);

            okButton = new JButton("OK");
            okButton.addActionListener(e -> setVisible(false));
            this.add(okButton);
            setVisible(true);
        }

        public JTextField getCatalogueIDField() {
            return catalogueID;
        }

    }

}