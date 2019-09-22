import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("answer is " + Calculate(BigInteger.valueOf(4477), BigInteger.valueOf(10000))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000))
                .mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec

        System.out.println(recursive(BigInteger.valueOf(7), BigInteger.valueOf(20)));
//        System.out.println("power is " + power(BigInteger.valueOf(2000), BigInteger.valueOf(300000000), BigInteger.valueOf(177)));
//        System.out.println("other power is " +betterPower(BigInteger.valueOf(2000), BigInteger.valueOf(300000000), BigInteger.valueOf(177)));

//        System.out.println(BasicRecursive(BigInteger.valueOf(550), BigInteger.valueOf(560)).
//                mod(BigInteger.valueOf(998244353)));
        System.out.println("finished");
    }

    private static BigInteger BasicRecursive(BigInteger n, BigInteger m) {
        int ans = 0;

        if (m == BigInteger.ZERO || n == BigInteger.ZERO) {
            return BigInteger.ZERO;
        }

        if (m == BigInteger.ONE || n == BigInteger.ONE) {
            return BigInteger.ONE;
        }

        if (m.compareTo(n) == -1) {
            return powerOfTwo(m).subtract(BigInteger.valueOf(1));
        }

        if (n == BigInteger.valueOf(2)) {
            return m.add(BigInteger.ONE).multiply(m).divide(BigInteger.valueOf(2));
        }


        BigInteger firstVal = BasicRecursive(n, m.subtract(BigInteger.ONE));
        BigInteger secondVal = BasicRecursive(n.subtract(BigInteger.ONE), m.subtract(BigInteger.ONE));
        return firstVal.add(secondVal).add(BigInteger.ONE);
    }

    private static BigInteger recursive(BigInteger n, BigInteger m) {
        int ans = 0;

        if (m == BigInteger.ZERO || n == BigInteger.ZERO) {
            return BigInteger.ZERO;
        }

        if (m == BigInteger.ONE || n == BigInteger.ONE) {
            return BigInteger.ONE;
        }
        if (n == BigInteger.valueOf(2)) {
            return m.add(BigInteger.ONE).multiply(m).divide(BigInteger.valueOf(2));
        }

        if (m.compareTo(n) == -1) {
            return powerOfTwo(m).subtract(BigInteger.valueOf(1));
        }

        BigInteger firstVal = recursive(n, m.subtract(BigInteger.valueOf(2)));
        BigInteger secondVal = recursive(n.subtract(BigInteger.ONE), m.subtract(BigInteger.valueOf(2)))
                .multiply(BigInteger.valueOf(2));
        BigInteger thirdVal = recursive(n.subtract(BigInteger.valueOf(2)), m.subtract(BigInteger.valueOf(2)));
        return firstVal.add(secondVal).add(thirdVal);
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


        BigInteger lastBinomial = m;
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) != 1; i = i.add(BigInteger.ONE)) {
            ans = ans.add(lastBinomial).mod(BigInteger.valueOf(998244353));
            lastBinomial = lastBinomial.multiply(m.subtract(i)).mod(BigInteger.valueOf(998244353));
            lastBinomial = lastBinomial.multiply(power(i.add(BigInteger.ONE), BigInteger.valueOf(998244353)
                    .subtract(BigInteger.valueOf(2)), BigInteger.valueOf(998244353))).mod(BigInteger.valueOf(998244353));


        }
        return ans.subtract(one);
    }

    // To compute x^y under modulo m
    static BigInteger power(BigInteger x, BigInteger y, BigInteger m) {
        if (y.compareTo(BigInteger.ZERO) == 0)
            return BigInteger.ONE;

        BigInteger p = power(x, y.divide(BigInteger.valueOf(2)), m).mod(m);
        p = p.multiply(p).mod(m);

        if (y.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0)
            return p;
        else
            return (x.multiply(p)).mod(m);
    }


    static BigInteger betterPower(BigInteger x, BigInteger y, BigInteger m) {
        BigInteger ans = BigInteger.ONE;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(y) == -1; i = i.add(BigInteger.ONE)) {
            ans = ans.multiply(x).mod(m);
        }
        return ans;
    }


    private static BigInteger powerOfTwo(BigInteger x) {
        BigInteger ans = BigInteger.valueOf(1);
        for (BigInteger i = BigInteger.ZERO; i.compareTo(x) == -1; i = i.add(BigInteger.ONE)) {
            ans = ans.multiply(BigInteger.valueOf(2));
        }
        return ans;
    }


}
