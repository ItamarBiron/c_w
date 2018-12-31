import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(height(BigInteger.valueOf(2), BigInteger.valueOf(14)));
        System.out.println(tryRecursion(2, 20));
        System.out.println(betterRecursion(3, 11));
//        System.out.println(calculateFactorial(15000));
//        System.out.println(calculateBinom(40000,80000));
        System.out.println(calculateFactorial(80000));
//        BigInteger[] firstArray = returnFactorialArray(30000);
//        System.out.println(beterCalculate(400,800));//.mod(BigInteger.valueOf(998244353)));
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

    private static BigInteger beterCalculate(int n, int m) {
        BigInteger one = BigInteger.ONE;
        BigInteger ans = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {
            ans = ans.add(calculateBinom(i, m));
        }
        return ans.subtract(one);
    }

    private static BigInteger[] returnFactorialArray(int n) {
        BigInteger[] array = new BigInteger[n];
        BigInteger lastCalc = BigInteger.ONE;
        array[0] = lastCalc;
        for (int i = 1; i < n; i++) {
            array[i] = array[i-1].multiply(BigInteger.valueOf(i));
        }
        return array;
    }
}
