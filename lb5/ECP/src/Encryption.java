import java.math.BigInteger;
import java.util.Random;

public class Encryption {
    private long p;
    private long g;
    private long exitKey;
    private long openKey;
    private Random random;
    private String signature;

    public String getSignature() {
        return signature;
    }

    public long getOpenKey() {
        return openKey;
    }

    public long getExitKey() {
        return exitKey;
    }

    public Encryption(int p, int g) {
        this.p = p;
        this.g = g;
        random = new Random();
    }

    public void generationKey() {
        exitKey = (int) (Math.random() * (p - 1)) + 2;
        BigInteger bigG = new BigInteger(String.valueOf(g));
        bigG = bigG.pow((int) exitKey).mod(new BigInteger(String.valueOf(p)));
        openKey =  bigG.longValue();
    }

    public void signHash(int hash) {
        int k;
        do {
            k = (int) (Math.random() * (p - 2)) + 2;
            if (GCD.gcd(k, p - 1) == 1) {
                break;
            }
        } while (true);
        long r = Math.floorMod((long) Math.pow(g, k), p);
        int s = findS(k, r, hash);
        signature = hash + "," + r + "," + s;

    }

    private int findS(int k, long r, int hash) {
        long p = this.p - 1;
        for (int s = 1; s <= p; s++) {
            if (Math.floorMod(k * s, p) == Math.floorMod(hash - exitKey * r, p)) {
                return s;
            }
        }
        return -1;
    }
}
