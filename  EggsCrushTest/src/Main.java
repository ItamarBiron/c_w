import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(beterCalculate(4477, 10000)); // need to pass that in less then 1 sec
        System.out.println("twice is " +twiceBetterCalculate(4477, 10000)); // need to pass that in less then 1 sec
//        System.out.println(beterCalculate(4, 17)); // need to pass that in less then 1 sec
//        System.out.println("twice is " +twiceBetterCalculate(4, 17)); // need to pass that in less then 1 sec

    }


    public static BigInteger height(BigInteger n, BigInteger m) {
        BigInteger one = BigInteger.valueOf(1);
        BigInteger two = BigInteger.valueOf(2);

        BigInteger addition = m.add(one);

        BigInteger multiplay = m.multiply(addition);
        return multiplay.divide(two); // TODO replace with your solution
    }

    public static int tryRecursion(int n, int m) {
        int ans = 0;
        if (m == 1 || n == 1) {
            return m;
        }
        if (n == 2) {
            return (int) (((m + 1) * m) / 2.0);
        }

        for (int i = 1; i <= m - 1; i++) {
            ans = ans + tryRecursion(n - 1, i);
        }

        return ans + m;
    }


    public static int betterRecursion(int n, int m) {
        int ans = 0;
        if (m == 1 || n == 1) {
            return m;
        }
        if (n == 2) {
            return (int) (((m + 1) * m) / 2.0);
        }

        return betterRecursion(n, m - 1) + betterRecursion(n - 1, m - 1) + 1;
    }

    public static BigInteger muchBetterRecursion(int n, int m) {
        int ans = 0;
        if (m == 1 || n == 1) {
            return BigInteger.valueOf(m);
        }
        if (n == 2) {
            return BigInteger.valueOf(m).add(BigInteger.valueOf(1)).multiply(BigInteger.valueOf(m)).divide
                    (BigInteger.valueOf(2));
        }

        if (m < n) {
            return powerOfTwo(m).subtract(BigInteger.valueOf(1));
        }

        return muchBetterRecursion(n, m - 1).add(muchBetterRecursion(n - 1, m - 1).add(BigInteger.valueOf(1)));
    }

    private static BigInteger calculateFactorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    private static BigInteger calculateBinom(int n, int m) { // is equal to m choose n
        BigInteger factorial = BigInteger.ONE;
        for (int i = m - n + 1; i <= m; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial.divide(calculateFactorial(n));
    }

    static BigInteger binomialCoeff(int k, int n) // is equal to n choose k
    {
        if (k > n) {
            System.out.println("n can be smaller then k");
            return null;
        }

        BigInteger res = BigInteger.ONE;

        // Since C(n, k) = C(n, n-k)
        if (k > n - k)
            k = n - k;

        // Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
        for (int i = 0; i < k; ++i) {
            res = res.multiply(BigInteger.valueOf(n - i));
            res = res.divide(BigInteger.valueOf(i + 1));
        }

        return res;
    }

    static BigInteger BetterBinomialCoeff(int k, int n) // is equal to n choose k
    {
        if (k > n) {
            System.out.println("n can be smaller then k");
            return null;
        }

        BigInteger res = BigInteger.ONE;

        // Since C(n, k) = C(n, n-k)
        if (k > n - k)
            k = n - k;

        // Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
        for (int i = 0; i < k; ++i) {
            res = res.divide(BigInteger.valueOf(i + 1)).multiply((BigInteger.valueOf(n - i)));
        }

        return res;
    }


    private static BigInteger beterCalculate(int n, int m) {
        if (m == 1 || n == 1) {
            return BigInteger.valueOf(m);
        }
        if (n == 2) {
            return BigInteger.valueOf((int) (((m + 1) * m) / 2.0));
        }


        BigInteger one = BigInteger.ONE;
        BigInteger ans = BigInteger.ONE;

        for (int i = 1; i <= Math.min(n, m); i++) {
            ans = ans.add(binomialCoeff(i, m));
            if (i % 1000 == 0) {
                System.out.println(i);
            }
        }
        return ans.subtract(one);
    }

    private static BigInteger twiceBetterCalculate(int n, int m) {
        if (m == 1 || n == 1) {
            return BigInteger.valueOf(m);
        }
        if (n == 2) {
            return BigInteger.valueOf((int) (((m + 1) * m) / 2.0));
        }


        BigInteger one = BigInteger.ONE;
        BigInteger ans = BigInteger.ONE;

        BigInteger lastBinomial = BigInteger.ONE;
        lastBinomial = binomialCoeff(1, m);
        for (int i = 1; i <= Math.min(n, m); i++) {
            ans = ans.add(lastBinomial);
            lastBinomial = lastBinomial.multiply(BigInteger.valueOf(m - i ));
            lastBinomial = lastBinomial.divide(BigInteger.valueOf(i+1));
            if (i % 1000 == 0) {
                System.out.println(i);
            }
        }
        return ans.subtract(one);
    }

    private static BigInteger finalCalculate(int n, int m) {
        if (m == 1 || n == 1) {
            return BigInteger.valueOf(m);
        }
        if (n == 2) {
            return BigInteger.valueOf((int) (((m + 1) * m) / 2.0));
        }

        if (n > m) {
            return powerOfTwo(m).subtract(BigInteger.valueOf(1));
        }

        BigInteger one = BigInteger.ONE;
        BigInteger ans = BigInteger.ONE;

        for (int i = n + 1; i <= m; i++) {
            ans = ans.add(binomialCoeff(i, m));
            if (i % 1000 == 0) {
                System.out.println(i);
            }
        }
        return powerOfTwo(m).subtract(ans);
    }

    private static BigInteger mayBeBetterCalculate(int n, int m) {
        BigInteger ans = BigInteger.valueOf(1);
        BigInteger firstMuli = BigInteger.valueOf(m - n + 1).multiply(binomialCoeff(n + 1, m));

        BigInteger secondMulti = BigInteger.valueOf(m - 1).multiply(binomialCoeff(1, m));
        ans = firstMuli.add(secondMulti);
        ans = ans.divide(BigInteger.valueOf(m + 1));
        return ans;
    }

    private static BigInteger[] returnFactorialArray(int n) {
        BigInteger[] array = new BigInteger[n];
        BigInteger lastCalc = BigInteger.ONE;
        array[0] = lastCalc;
        for (int i = 1; i < n; i++) {
            array[i] = array[i - 1].multiply(BigInteger.valueOf(i));
        }
        return array;
    }

    private static BigInteger powerOfTwo(int x) {
        BigInteger ans = BigInteger.valueOf(1);
        for (int i = 0; i < x; i++) {
            ans = ans.multiply(BigInteger.valueOf(2));
        }
        return ans;
    }


}
