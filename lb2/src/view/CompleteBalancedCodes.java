package view;

import controllers.listener.ExitListener;
import controllers.listener.MyKeyAdapter;
import controllers.listener.calculations.CalculationHelper;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Алексей on 26.02.2016.
 * Отоброжение формы
 */
public class CompleteBalancedCodes {
    private JPanel panel1;
    private JTextField textField1;
    private JButton inButton;
    private JTextField textField5;
    private JTextField textField6;
    private JButton decodeButton;
    private JButton encodeButton;
    private JComboBox comboBox2;
    private JTextField textField7;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JButton exitButton;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField12;

    private static JFrame frame; // для настройки формы
    private CalculationHelper helper; // для вычислений

    public CompleteBalancedCodes() {
        textField1.addKeyListener(new MyKeyAdapter());
        textField6.addKeyListener(new MyKeyAdapter());
        inButton.addActionListener(e -> {
            if (textField1.getText() == null || textField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Вы, ничего не ввели.\nВведите последовательность заново" +
                        "и нажмите кнопку 'Ввод' еще раз");
            } else {
                String originalSequence = textField1.getText();
                helper = new CalculationHelper(originalSequence);
                helper.calculateInitialData(comboBox1);
                textField2.setText(String.valueOf(helper.getN()));
                textField3.setText(String.valueOf(helper.getM()));
                textField4.setText(String.valueOf(helper.getR()));
                textField1.setEditable(false);
            }
        });
        encodeButton.addActionListener(e -> {
            if (helper == null || (textField1.getText() == null || textField1.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Вы, ничего не ввели.\nВведите последовательность заново\n" +
                        "и нажмите кнопку 'Ввод', а затем кнопку 'Кодировать'");
            } else {
                String codedSequences = helper.addToNumberControlSequences();
                textField5.setText(codedSequences);
                textField6.setText(codedSequences);
            }
        });
        decodeButton.addActionListener(e -> {
            textField9.setText(null);
            textField7.setText(null);
            textField11.setText(null);
            if (helper == null || (textField6.getText() == null || textField6.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Вы, ничего не ввели."
                        + "\nВведите последовательность заново в поле 'Информационная последовательность'"
                        + "и нажмите кнопку 'Ввод', затем 'Кодировать' и только после 'Декодировать'");
            } else {
                textField11.setText(helper.decodedSequences(textField6.getText(), comboBox2));
                textField7.setText(String.valueOf(helper.getM()));
                textField9.setText(helper.getRWithStar());
                textField12.setText(helper.getJ() != -1 ? Integer.toString(helper.getJ()) : "");
                textField10.setText(helper.getFoldError() != 0 ?
                        helper.getFoldError().toString() : "");
            }
        });
        exitButton.addActionListener(new ExitListener(frame));
    }

    /**
     * Создание и настройка формы
     * а так же задаем панель для
     * вывода всех компонент
     */
    public static void settingAndCreateFrame() {
        frame = new JFrame("Равновесный код");
        frame.setContentPane(new CompleteBalancedCodes().panel1);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
