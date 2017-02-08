import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Алексей on 21.02.2016.
 */
public class CalculationHelper {
    private List<Character> sequence;
    private List<String> listE;
    private List<String> listS;
    private StringBuilder builder;
    private int m;
    private int k;

    public List<String> getListS() {
        return listS;
    }

    public void setListS(List<String> listS) {
        this.listS = listS;
    }

    public List<String> getListE() {
        return listE;
    }

    public void setListE(List<String> listE) {
        this.listE = listE;
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    public List<Character> getSequence() {
        return sequence;
    }

    public void setSequence(List<Character> sequence) {
        this.sequence = sequence;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    private int n;

    public CalculationHelper(String originalData) {
        sequence = new ArrayList<>();
        listE = new ArrayList<>();
        listS = new ArrayList<>();
        m = 0;
        k = 0;
        n = 0;

        char[] array = originalData.toCharArray();
        for (int i = 0; i < array.length; i++) {
            sequence.add(array[i]);
        }
    }

    public int decodCalc() {
        int n = sequence.size();
        int m = 0;
        builder = new StringBuilder();
        while (Math.pow(2, m) < n - 1) {
            Character e = mod(calcE((int) Math.pow(2, m)));
            builder.append(e);
            listE.add("E_" + (int) Math.pow(2, m) + " = " + e);
            ++m;
        }
        builder = builder.reverse();
        Character bitChet = mod(calcBitChet());
        if (bitChet.equals('0') && builder.indexOf("1") <= -1) {
            return 0;
        }
        if (!bitChet.equals('0') && builder.indexOf("1") > -1) {
            return 1;
        }
        if (!bitChet.equals('0') || builder.indexOf("1") > -1) {
            return 2;
        }
        return 0;
    }

    public List<Character> delBit() {
        List<Character> result = new ArrayList<>();
        int n = sequence.size();
        Set<Integer> set = new HashSet<>();
        int m = 0;
        set.add(0);
        while (Math.pow(2, m) < n - 1) {
            set.add((int) Math.pow(2, m));
            ++m;
        }
        for (int i = 0; i < sequence.size(); i++) {
            if (!set.contains(i)) {
                result.add(sequence.get(i));
            }
        }
        return result;
    }

    private int calcBitChet() {
        int count = 0;
        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) == '1') {
                ++count;
            }
        }
        return count;
    }

    private int calcE(int k) {
        int vnutrCount;
        int vneshVount = k;
        int countEd = 0;
        while (vneshVount < sequence.size()) {
            int j = 0;
            vnutrCount = vneshVount;
            while (j < k && vnutrCount < sequence.size()) {
                Character cur = sequence.get(vnutrCount);
                if (!cur.equals('*') && !cur.equals('0')) {
                    countEd++;
                }
                ++vnutrCount;
                ++j;
            }
            vneshVount = vnutrCount + k;
        }
        return countEd;
    }

    public void calc() {
        int n = sequence.size();
        int m = 0;
        while (Math.pow(2, m) < m + n) {
            sequence.add((int) Math.pow(2, m) - 1, '*');
            ++m;
        }
        System.out.println(sequence.toString());
        this.m = m;
        this.n = m + n;
        this.k = n;
    }

    public void calcSec() {
        sequence.add(0, '_');
        int i = 0;
        while (sequence.contains('*')) {
            int k = sequence.indexOf('*');
            int countEd = calcE(k);
            Character x = mod(countEd);
            listS.add("X_" + k + " = " + x);
            sequence.set(k, x);
        }
        int count = calcBitChet();
        sequence.set(0, mod(count));
        System.out.println(sequence.toString());
    }

    private Character mod(int x) {
        if (x % 2 == 0) {
            return '0';
        } else {
            return '1';
        }
    }
}
