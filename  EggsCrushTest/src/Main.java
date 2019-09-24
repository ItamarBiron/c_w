import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static BigInteger prime = BigInteger.valueOf(998244353);

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


    private static BigInteger Calculate(BigInteger n, BigInteger m) {

        if (m.intValue() == 1 || n.intValue() == 1) {
            return m;
        }
        if (n.intValue() == 2) {
            return ((m.add(BigInteger.valueOf(1))).multiply(m)).divide(BigInteger.valueOf(2));
        }

        if (n.compareTo(m) == 1) {
            return BigInteger.valueOf(2).modPow(m, prime).subtract(BigInteger.valueOf(1));
        }

        BigInteger one = BigInteger.ONE;
        BigInteger ans = BigInteger.ONE;


        BigInteger lastBinomial = m;
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) != 1; i = i.add(BigInteger.ONE)) {
            ans = ans.add(lastBinomial).mod(prime);
            lastBinomial = lastBinomial.multiply(m.subtract(i)).mod(prime);
            lastBinomial = lastBinomial.multiply(i.add(BigInteger.ONE).modInverse(prime)).mod(prime);


        }
        return ans.subtract(one);
    }
}
