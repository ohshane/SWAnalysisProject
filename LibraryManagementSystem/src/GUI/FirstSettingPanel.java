package GUI;

import Logic.Library;

import javax.swing.*;
import java.awt.*;

public class FirstSettingPanel extends JPanel {

    private Library library;

    public FirstSettingPanel() {
        this.setLayout(new FlowLayout());

        this.add(new JLabel("Set library name here"));

        JTextField libraryNameTF = new JTextField("선문대학교 중앙도서관");
        libraryNameTF.setColumns(20);
        this.add(libraryNameTF);

        JButton libraryNameB = new JButton("Submit");
        this.add(libraryNameB);
        libraryNameB.addActionListener(e -> {
            library = new Library(libraryNameTF.getText());
            this.add(new JLabel("Successfully initialized : " + libraryNameTF.getText()));
            libraryNameB.setVisible(false);
        });

    }
}
