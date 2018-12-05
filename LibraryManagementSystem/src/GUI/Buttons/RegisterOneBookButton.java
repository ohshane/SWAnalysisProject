package GUI.Buttons;


import GUI.HintTextField;
import Logic.Book;
import Logic.DB;
import Logic.Library;

import javax.swing.*;
import java.awt.*;


public class RegisterOneBookButton extends JButton {
    private JFrame frame;
    private Library lib;

    public RegisterOneBookButton(JFrame frame, Library lib) {
        this.frame = frame;
        this.lib = lib;
        
        this.setText("Register one book");
        this.addActionListener(e -> {
            ThisDialog thisDialog = new ThisDialog();

            if (!thisDialog.getTitleField().getText().equals("") && !thisDialog.getAuthorField().getText().equals("")){
                lib.registerOneBook(thisDialog.getTitleField().getText(), thisDialog.getAuthorField().getText());
            }

        });
    }

    private class ThisDialog extends JDialog {
        private JTextField title;
        private JTextField author;
        private JButton okButton;

        private ThisDialog() {
            super(frame, true);
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Register new book here"));
            this.setSize(250, 200);
            this.setResizable(false);

            title = new HintTextField("Title", 20);
            this.add(title);

            author = new HintTextField("Author", 20);
            this.add(author);

            okButton = new JButton("OK");
            okButton.addActionListener(e -> setVisible(false));
            this.add(okButton);
            setVisible(true);
        }

        public JTextField getTitleField() {
            return title;
        }

        public JTextField getAuthorField() {
            return author;
        }
    }

}
