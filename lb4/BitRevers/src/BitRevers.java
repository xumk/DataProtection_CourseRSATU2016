/**
 * Created by Алексей on 25.03.2016.
 */
public class BitRevers {
    private static char[] openAlphabet = new char[32];
    private static char[] exitAlphabet = new char[32];

    public BitRevers() {
        openAlphabet[0] = '\u0020';
        for (int i = 0; i < 9; i++) {
            openAlphabet[i + 1] = (char) ('а' + i);
        }
        for (int i = 10; i < 32; ++i) {
            openAlphabet[i] = (char) ('а' + i);
        }
    }

    public void calcExitAlphabet(String  key) {
        int[] arrayKey = findePosition(key);
        for (int i = 0; i < 32; i++) {
            StringBuilder stI = new StringBuilder(Integer.toBinaryString(i));
            for (int j = stI.length(); j < 5; j++) {
                stI.insert(0,"0");
            }
            StringBuilder charAlph = new StringBuilder();
            char[] mass = stI.toString().toCharArray();
            for (int anArrayKey : arrayKey) {
                charAlph.append(mass[anArrayKey]);
            }
            int exitI = Integer.parseInt(charAlph.toString(), 2);
            exitAlphabet[i] = openAlphabet[exitI];
        }
    }

    private int[] findePosition(String arrayKey) {
        int[] result = new int[arrayKey.length()];
        result[0] = arrayKey.indexOf("1");
        result[1] = arrayKey.indexOf("2");
        result[2] = arrayKey.indexOf("3");
        result[3] = arrayKey.indexOf("4");
        result[4] = arrayKey.indexOf("5");
        return result;
    }

    public String codText(String originalText) {
        char[] text = originalText.toCharArray();
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            result[i] = exitAlphabet[contains(openAlphabet, text[i])];
        }
        return String.valueOf(result);
    }

    private int contains(char[] chars, char symbol) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == symbol) {
                return i;
            }
        }
        return -1;
    }

    public String decodText(String originalText) {
        char[] text = originalText.toCharArray();
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            result[i] = openAlphabet[contains(exitAlphabet, text[i])];
        }
        return String.valueOf(result);
    }
}
