import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {
   static   BigInteger prime = BigInteger.valueOf(998244353);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("answer is " + Calculate(BigInteger.valueOf(4477), BigInteger.valueOf(10000))); // need to pass that in less then 1 sec
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
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec
        System.out.println("answer is " + Calculate(BigInteger.valueOf(40000), BigInteger.valueOf(80000)).mod(BigInteger.valueOf(998244353))); // need to pass that in less then 1 sec

        // System.out.println("power is " + power(BigInteger.valueOf(201), BigInteger.valueOf(5), BigInteger.valueOf(11)));
        System.out.println("other power is " + betterPower(BigInteger.valueOf(201), BigInteger.valueOf(5), BigInteger.valueOf(11)));

//        System.out.println(BasicRecursive(BigInteger.valueOf(550), BigInteger.valueOf(560)).
//                mod(BigInteger.valueOf(998244353)));
        System.out.println("finished");
    }


    private static BigInteger Calculate(BigInteger n, BigInteger m) {

        if (m.intValue() == 1 || n.intValue() == 1) {
            return m;
        }
        if (n.intValue() == 2) {
            return ((m.add(BigInteger.valueOf(1))).multiply(m)).divide(BigInteger.valueOf(2));
        }

        if (n.compareTo(m) == 1) {
            return betterPower(BigInteger.valueOf(2), m, BigInteger.valueOf(998244353)).subtract(BigInteger.valueOf(1));
        }

        BigInteger one = BigInteger.ONE;
        BigInteger ans = BigInteger.ONE;


        BigInteger lastBinomial = m;
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) != 1; i = i.add(BigInteger.ONE)) {
            ans = ans.add(lastBinomial).mod(prime);
            lastBinomial = lastBinomial.multiply(m.subtract(i)).mod(prime);
              lastBinomial = lastBinomial.multiply(i.add(BigInteger.ONE).modPow( prime.subtract(BigInteger.valueOf(2)),prime));

            //  lastBinomial.p
          //  lastBinomial = lastBinomial.multiply(betterPower(i.add(BigInteger.ONE), prime
            //        .subtract(BigInteger.valueOf(2)),prime).mod(prime)).mod(prime);


        }
        return ans.subtract(one);
    }


    static BigInteger betterPower(BigInteger a, BigInteger b, BigInteger m) {

        a = a.mod(m);
        BigInteger res = BigInteger.ONE;
        while (b.compareTo(BigInteger.ZERO) == 1) {
            if (b.mod(BigInteger.valueOf(2)) != BigInteger.ZERO) {
                res = res.multiply(a).mod(m);
            }
            a = a.multiply(a).mod(m);
            b = b.shiftRight(1);

        }

        return res;

    }


}
