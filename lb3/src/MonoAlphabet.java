import static java.util.Arrays.binarySearch;

/**
 * Created by Алексей on 25.03.2016.
 */
public class MonoAlphabet {
    private static char[] openAlphabet = new char[32];
    private static char[] exitAlphabet = new char[32];

    public MonoAlphabet() {
        openAlphabet[0] = '\u0020';
        for (int i = 0; i < 9; i++) {
            openAlphabet[i + 1] = (char) ('а' + i);
        }
        for (int i = 10; i < 32; ++i) {
            openAlphabet[i] = (char) ('а' + i);
        }
    }

    public void calcExitAlphabet(int key) {
        for (int i = 0; i < 32; i++) {
            int codeChar = Math.floorMod(i + key, 32);
            exitAlphabet[i] = openAlphabet[codeChar];
        }
    }

    public String getCodeText(String originalText) {
        char[] text = originalText.toCharArray();
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            int findChar = binarySearch(openAlphabet, text[i]);
            result[i] = (findChar < 0) ? ' ' : exitAlphabet[findChar];
        }
        return String.valueOf(result);
    }

    public String getDecodeText(String originalText) {
        char[] text = originalText.toCharArray();
        char[] result = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            int findChar = binarySearch(openAlphabet, text[i]);
            result[i] = (findChar < 0) ? ' ' : openAlphabet[findChar];
        }
        return String.valueOf(result);
    }
}
