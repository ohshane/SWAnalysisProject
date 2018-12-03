package GUI;

import javax.swing.*;
import java.awt.*;

public class InitialModalDialog extends JDialog {
    private JTextField libraryName;
    private JButton okButton;

    public InitialModalDialog(JFrame frame) {
        super(frame, true);
        this.setLayout(new FlowLayout());

        this.add(new JLabel("Initialize your library name here"));

        this.setSize(300, 100);
        this.setResizable(false);

        libraryName = new JTextField(20);
        this.add(libraryName);

        okButton = new JButton("OK");
        okButton.addActionListener(e -> setVisible(false));

        this.add(okButton);
        setVisible(true);
    }

    public String getInput() {
        return libraryName.getText().length() == 0 ? null : libraryName.getText();
    }
}
