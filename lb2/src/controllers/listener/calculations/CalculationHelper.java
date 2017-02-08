package controllers.listener.calculations;

import javax.swing.*;

/**
 * Created by Алексей on 26.02.2016.
 * Класс для вычислений
 */
public class CalculationHelper {
    private char[] sequence;
    private int m;
    private Integer n;
    private Integer l;
    private Integer j;
    private Integer foldError;
    private String r;
    private String rWithStar;

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getRWithStar() {
        return rWithStar;
    }

    public void setRWithStar(String rWithStar) {
        this.rWithStar = rWithStar;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Integer getFoldError() {
        return foldError;
    }

    public void setFoldError(Integer foldError) {
        this.foldError = foldError;
    }

    public CalculationHelper(String sequence) {
        this.sequence = sequence.toCharArray();
    }

    public void calculateInitialData(JComboBox comboBox) {
        n = sequence.length;
        l = (int) lg(n);
        int[] r = countTheNumberItemsAndFillComboBox(sequence, comboBox);
        this.m = r[1];
        this.r = countValueR(r[0]);
    }

    private String countValueR(int r) {
        StringBuilder intermediateValueR = new StringBuilder(Integer.toString(Math.floorMod(r, n), 2));
        int length = intermediateValueR.length();
        for (int j = 0; j < l - length; j++) {
            intermediateValueR.insert(0, "0");
        }
        return intermediateValueR.toString();
    }

    public String addToNumberControlSequences() {
        return String.valueOf(sequence) + r;
    }

    private int[] countTheNumberItemsAndFillComboBox(char[] sequence, JComboBox comboBox) {
        int m = 0, i = 0, r = 0;
        int[] result = new int[2];
        for (char a : sequence) {
            if (a == '1') {
                ++m;
                r += i;
                comboBox.addItem("алфа_" + m + " = " + i);
            }
            ++i;
        }
        result[1] = m;
        result[0] = r;
        return result;
    }

    private static double lg(double x) {
        return Math.log(x) / Math.log(2.0);
}

    public String decodedSequences(String text, JComboBox comboBox) {
        String sequence = text.substring(0, text.length() - l);
        String rString = text.substring(text.length() - l, text.length());
        int[] r = countTheNumberItemsAndFillComboBox(sequence.toCharArray(), comboBox);
        int m = r[1];
        rWithStar = countValueR(r[0]);
        int indexError = checkErrorSequence(m, rString);
        StringBuffer result = new StringBuffer(sequence);
        int countDifferent = 0;
        int differentI = 0;
        if (indexError != -1) {
            char symbol = result.charAt(indexError) == '0' ? '1' : '0';
            result.setCharAt(indexError, symbol);
            int rank = 0;
            for (Character ch : result.toString().toCharArray()) {
                if (ch == '1') {
                    ++rank;
                }
            }
            if (rank == this.m) {
                foldError = 1;
            } else {
                foldError = 2;
                j = -1;
                return "Не семетричная ошибка второй кратности. Повторите передачу сообщения.";
            }
            j = indexError;
            return result.toString();
        }
        for (int i = 0; i < rString.length(); i++) {
            if (rString.charAt(i) != rWithStar.charAt(i)) {
                ++countDifferent;
                differentI = i;
            }
        }
        if (countDifferent == 2) {
            foldError = 2;
            j = -1;
            return "Симметричная ошибка второй кратности. Повторите передачу сообщения";
        }
        if (countDifferent == 1) {
            j = differentI + n;
            foldError = 1;
            return result.toString();
        }
        j = -1;
        foldError = 0;
        return result.toString();
    }

    private int checkErrorSequence(int m, String rString) {
        if (m == this.m && rString.equals(rWithStar)) {
            return -1;
        }
        if (m == this.m && !rString.equals(rWithStar)) {
            return -1;
        }
        if (this.m < m) {
            return Math.floorMod((Math.floorMod(Integer.valueOf(rWithStar, 2), n) - Integer.valueOf(rString, 2)), n);
        } else {
            return Math.floorMod((Integer.valueOf(rString, 2) - Math.floorMod(Integer.valueOf(rWithStar, 2), n)), n);
        }
    }
}
