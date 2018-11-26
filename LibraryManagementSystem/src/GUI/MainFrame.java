package GUI;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        this.setTitle(null);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setContentPane(new FirstSettingPanel());
    }
}
