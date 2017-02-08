import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.IntBinaryOperator;

import static java.awt.Component.CENTER_ALIGNMENT;
import static java.awt.image.ImageObserver.PROPERTIES;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Алексей on 12.02.2016.
 */
public class Test {
    private JPanel panel;
    private JTextField textField1;
    private JButton inButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton codButton;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JTextField textField6;
    private JButton deCodButton;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JButton outButton;
    private JComboBox comboBox2;
    private JTextField textField7;
    private JLabel lable10;
    private static JFrame frame;
    CalculationHelper helper;

    public Test() {
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)) {
                    e.consume();
                }
            }
        });
        textField6.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)) {
                    e.consume();
                }
            }
        });
        outButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Пока-пока");
            frame.dispose();
        });
        inButton.addActionListener(e -> {
            if (textField1.getText() == null || textField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Вы, ничего не ввели.\nВведите последовательность заново" +
                        "и нажмите кнопку 'Ввод' еще раз");
            } else {
                String originalSequence = textField1.getText();
                helper = new CalculationHelper(originalSequence);
                helper.calc();
                textField4.setText(String.valueOf(helper.getK()));
                textField2.setText(String.valueOf(helper.getM()));
                textField3.setText(String.valueOf(helper.getN()));
                textField1.setEditable(false);
            }
        });
        codButton.addActionListener(e -> {
            if (helper == null ||
                    (textField1.getText() == null || textField1.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Вы, ничего не ввели.\nВведите последовательность заново\n" +
                        "и нажмите кнопку 'Ввод' еще раз");
            } else {
                helper.calcSec();
                comboBox1.addItem("X_0 = " + helper.getSequence().get(0));
                for (String st : helper.getListS()) {
                    comboBox1.addItem(st);
                }
                comboBox1.setSelectedIndex(0);
                textField5.setText(helper.getSequence().toString().replaceAll(",", "").replaceAll(" ", "").replace("[", "").replace("]", ""));
            }
        });
        deCodButton.addActionListener(e -> {
            if (textField6.getText() == null || textField6.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Вы, ничего не ввели.\nВведите последовательность заново" +
                        "и нажмите кнопку 'Декодировать' еще раз");
            } else {
                textField9.setText(null);
                String originalSequence = textField6.getText();
                helper = new CalculationHelper(originalSequence);
                Integer r = helper.decodCalc();
                textField7.setText(helper.getBuilder().toString());
                for (String st : helper.getListE()) {
                    comboBox2.addItem(st);
                }
                comboBox2.setSelectedIndex(0);
                String itog = r > 1 ? ">=2" : "1";
                if (r == 1) {
                    int index = Integer.parseInt(helper.getBuilder().toString(), 2);
                    textField9.setText(String.valueOf(index));
                    if (helper.getSequence().get(index).equals('1')) {
                        helper.getSequence().set(index, '0');
                    } else {
                        helper.getSequence().set(index, '1');
                    }
                    textField10.setText(helper.delBit().toString().replace(",", "").replace(" ", "").replace("[", "").replace("]", ""));
                } else {
                    if (r == 0) {
                        textField10.setText(helper.delBit().toString().replace(",", "").replace(" ", "").replace("[", "").replace("]", ""));
                        itog = "0";
                    } else {
                        textField10.setText("Повторите передачу сообщения");
                    }
                }
                textField8.setText(itog);
            }
        });
    }

    public static void createFrame() {
        frame = new JFrame("Расширенный код Хемминга");
        frame.setContentPane(new Test().panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void listner() {
        outButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Пока-пока");
            frame.dispose();
        });
    }

    public JButton getOutButton() {
        return outButton;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }
}
