import java.math.BigInteger;

public class Decryption {
    private int p;
    private int g;
    private long openKey;

    public Decryption(int p, int g, long openKey) {
        this.p = p;
        this.g = g;
        this.openKey = openKey;
    }

    public boolean decode(int hash, int r, int s) {
        BigInteger openKey = new BigInteger(String.valueOf(this.openKey));
        BigInteger bigR = new BigInteger(String.valueOf(r));
        openKey = openKey.pow(r);
        bigR = bigR.pow(s);
        BigInteger multiply = openKey.multiply(bigR);
        BigInteger p = new BigInteger(String.valueOf(this.p));

        multiply = multiply.mod(p);

        BigInteger bigG = new BigInteger(String.valueOf(g)).pow(hash);
        bigG = bigG.mod(p);
        if (multiply.equals(bigG)) {
            return true;
        } else {
            return false;
        }
    }
}
