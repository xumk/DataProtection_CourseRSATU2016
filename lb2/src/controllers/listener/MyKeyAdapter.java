package controllers.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Алексей on 26.02.2016.
 * Для ввода в TextField только чисел
 */
public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent e) {
        char a = e.getKeyChar();
        if (!Character.isDigit(a)) {
            e.consume();
        }
    }
}
