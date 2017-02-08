package controllers.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Алексей on 11.03.2016.
 */
public class ExitListener implements ActionListener {
    private JFrame frame;

    public ExitListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Пока-пока");
        frame.dispose();
    }
}
