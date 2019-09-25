import java.math.BigInteger;
import java.util.stream.IntStream;

public class Main {
    static int index = 0;
    static int lastPrime = 1;


    public static void main(String[] args) {
        System.out.println("Hello World!");
        // Creating an IntStream
        IntStream stream = stream();

        // Displaying the sequential ordered stream
        stream.forEach(
                value-> {
                    if(String.valueOf(value).length()%6==0){
                        System.out.println(value);
                    }
                });
    }

    public static IntStream stream() {
        return IntStream.iterate(2, i -> getPrime());

    }

    public static int getPrime() {
        int maybyPrime = lastPrime + 2;
        while (true) {
            if (simpleIsPrime(maybyPrime)) {
                lastPrime = maybyPrime;
                return maybyPrime;
            }
            maybyPrime += 2;
        }
    }

    static boolean simpleIsPrime(int n) {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }


    // If n is prime, then always returns true,
    // If n is composite than returns false with
    // high probability Higher value of k increases
    //  probability of correct result.
    static boolean isPrime(int n, int k) {
        // Corner cases
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;

        // Try k times
        while (k > 0) {
            // Pick a random number in [2..n-2]
            // Above corner cases make sure that n > 4
            int a = 2 + (int) (Math.random() % (n - 4));

            // Fermat's little theorem
            if (BigInteger.valueOf(a).modPow(BigInteger.valueOf(n - 1), BigInteger.valueOf(n)).intValue() != 1)
                return false;

            k--;
        }

        return true;
    }
}
