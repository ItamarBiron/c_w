import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(height(BigInteger.valueOf(2), BigInteger.valueOf(14)));
        System.out.println(tryRecursion(4, 20));
        System.out.println(betterRecursion(6,80));
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
            ans = ans +tryRecursion(n-1,i);
        }

        return ans+m;
    }



    public static int betterRecursion(int n, int m) {
        int ans = 0;
        if (m == 1 || n == 1) {
            return m;
        }
        if (n == 2) {
            return (int) (((m + 1) * m) / 2.0);
        }



        return betterRecursion(n,m-1)+betterRecursion(n-1,m) +1;
    }
}
