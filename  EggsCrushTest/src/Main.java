import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
         System.out.println("answer is " + Calculate(BigInteger.valueOf( 4477), BigInteger.valueOf(10000))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec

    }


    static BigInteger binomialCoeff(BigInteger k, BigInteger n) // is equal to n choose k
    {
        if (k.compareTo(n) == 1) {
            System.out.println("n can be smaller then k");
            return null;
        }

        BigInteger res = BigInteger.ONE;

        // Since C(n, k) = C(n, n-k)
        if (k.multiply(BigInteger.valueOf(2)).subtract(n).compareTo(BigInteger.ZERO) == 1)
            k = n.subtract(k);

        // Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
        for (BigInteger i = BigInteger.ZERO; (i.subtract(k)).compareTo(BigInteger.ZERO) == -1; i=i.add(BigInteger.ONE)) {
            res = res.multiply(n.subtract(i));
            res = res.divide(i.add(BigInteger.ONE));
        }

        return res;
    }


    private static BigInteger Calculate(BigInteger n, BigInteger m) {
        if (m.intValue() == 1 || n.intValue() == 1) {
            return m;
        }
        if (n.intValue() == 2) {
            return ((m.add(BigInteger.valueOf(1))).multiply(m)).divide(BigInteger.valueOf(2));
        }

        if (n.compareTo(m) == 1) {
            return powerOfTwo(m).subtract(BigInteger.valueOf(1));
        }

        BigInteger one = BigInteger.ONE;
        BigInteger ans = BigInteger.ONE;


        BigInteger lastBinomial = binomialCoeff(BigInteger.ONE, m);
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) != 1; i = i.add(BigInteger.ONE)) {
            ans = ans.add(lastBinomial);
            lastBinomial = lastBinomial.multiply(m.subtract(i));
            lastBinomial = lastBinomial.divide(i.add(BigInteger.ONE));

        }
        return ans.subtract(one);
    }


    private static BigInteger powerOfTwo(BigInteger x) {
        BigInteger ans = BigInteger.valueOf(1);
        for (BigInteger i = BigInteger.ZERO; i.compareTo(x) == -1; i = i.add(BigInteger.ONE)) {
            ans = ans.multiply(BigInteger.valueOf(2));
        }
        return ans;
    }


}
