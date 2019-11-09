import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


//        for (int i = 2; i < 1000; i++) {
//            //System.out.println("Entering " + i);
//            long res = betterRecurcive2and3and5(i);
//            if (betterRecurcive2and3and5(i) != -1) {
//                if(i%100 ==0){
//
//                    System.out.println(i + " = " + res);
//                    System.out.println();
//                }
//
//            }
//        }
       // System.out.println(betterRecurcive2and3and5(11));
        System.out.println(betterRecurcive2and3(99));
        System.out.println(bestRecurcive2and3(99));

//        for (int i = 2; i < 1000; i++) {
//            System.out.println(betterRecurcive2and3(i));
//        }
    }

    public static int maxPowerOfBase(long number, int base) {
        int ans = 0;
        while (number % base == 0) {
            number = number / base;
            ans++;
        }
        return ans;
    }
//    public static long norRecurcive2and3 ( int n){
//        List<Long> longList = new ArrayList<>();
//        while ()
//    }

    public static long bestRecurcive2and3(int n) {
        if (n <= 3) {
            return 3;
        }
        long prev = bestRecurcive2and3(n - 1);
        int maxPowerOf2 = maxPowerOfBase(prev, 2);
        int maxPowerOf3 = maxPowerOfBase(prev, 3);
        long canBeNext = prev * 2;
        int index = 0;
        double minWithMultiplyBy3AndDivideBy2 = 2;
        double minWithMultiplyBy2AndDivideBy3 = 2;
        double division = 1;
        int counterFirst2 = 0;
        int counterFirst3 = 0;
        int counterSecond2 = 0;
        int counterSecond3 = 0;
        int howMuchDivideBy3 = 0;
        while (index < maxPowerOf2) {
            division = division / 2;
            index++;
            if (division < 1) {
                division = division * 3;
                howMuchDivideBy3++;
            }
            if (division < minWithMultiplyBy3AndDivideBy2) {
                minWithMultiplyBy3AndDivideBy2 = division;
                counterFirst2 = index;
                counterFirst3 = howMuchDivideBy3;
            }
        }


        int howMuchMultiplyBy2 = 0;
        index =0;
        division =1;
        while (index < maxPowerOf3) {
            division = division / 3;
            index++;
            while (division < 1) {
                division = division * 2;
                howMuchMultiplyBy2++;
            }
            if (division < minWithMultiplyBy2AndDivideBy3) {
                minWithMultiplyBy2AndDivideBy3 = division;
                counterSecond3 = index;
                counterSecond2 = howMuchMultiplyBy2;
            }
        }
        if (minWithMultiplyBy2AndDivideBy3 < minWithMultiplyBy3AndDivideBy2) {
            return prev / (long) Math.pow(3, counterSecond3) * (long) Math.pow(2, counterSecond2);

        }
        return prev / (long) Math.pow(2, counterFirst2) * (long) Math.pow(3, counterFirst3);

    }

    public static long betterRecurcive2and3(int n) {
        if (n <= 3) {
            return 3;
        }
        long prev = betterRecurcive2and3(n - 1);
        int maxPowerOf2 = maxPowerOfBase(prev, 2);
        int maxPowerOf3 = maxPowerOfBase(prev, 3);

        long next = prev * 3;
        for (int i = 0; i <= 3*(maxPowerOf2 + maxPowerOf3 ); i++) {
            for (int j = 0; j <= 3*(maxPowerOf2 + maxPowerOf3 ); j++) {
                //if(i-maxPowerOf2+(Math.log(j-maxPowerOf3))/Math.log(3)>0){


                long canBeNext = (long) (Math.pow(2, i) * Math.pow(3, j));
                if (canBeNext > prev && canBeNext < next) {
                    next = canBeNext;
                }
                // }
            }
        }
        return next;
    }


    public static long betterRecurcive2and3and5(int n) {
        if (n == 1) {
            return 1;
        }
        long prev = betterRecurcive2and3and5(n - 1);
        int maxPowerOf2 = maxPowerOfBase(prev, 2);
        int maxPowerOf3 = maxPowerOfBase(prev, 3);
        int maxPowerOf5 = maxPowerOfBase(prev, 5);

        long next = prev * 2;
        long canBeNext = next;
        for (int i = 0; i <= 3 * (maxPowerOf2 + maxPowerOf3 + maxPowerOf5); i++) {
            for (int j = 0; j <= 3 * (maxPowerOf2 + maxPowerOf3 + maxPowerOf5); j++) {
                for (int k = 0; k <= 3 * (maxPowerOf2 + maxPowerOf3 + maxPowerOf5); k++) {

                    canBeNext = (long) (Math.pow(2, i) * Math.pow(3, j) * Math.pow(5, k));
                    if (canBeNext > prev && canBeNext < next) {
                        next = canBeNext;
                    }
                    // }
                }
            }
        }
        return next;
    }

    public static boolean isPower(int x, int y) {
        // The only power of 1 is 1 itself
        if (x == 1)
            return (y == 1);

        // Repeatedly compute power of x
        int pow = 1;
        while (pow < y)
            pow = pow * x;

        // Check if power of x becomes y
        return (pow == y);
    }

    public static long recursiveHamming2and3(int n) {
        if (n <= 3) {
            return n;
        }

        long prev = recursiveHamming2and3(n - 1);
        // System.out.println("f - prev = " + prev);

        if (isPower(2, (int) prev)) {
            // System.out.println("prev is power of 2");

            int index = 1;

            while (Math.pow(3, index) < prev) {
                index++;
            }
            index--;
            return (long) Math.pow(3, index) * 2;

        }

        if (isPower(3, (int) prev)) {
            // System.out.println("prev is power of 3");

            int index = 1;

            while (Math.pow(3, index) < prev) {
                index++;
            }
            index--;
            return (long) Math.pow(3, index) * 4;

        }
        if (prev % 3 == 0) {
            // System.out.println("prev is divided by 3");

            return prev / 3 * 4;
        }
        if (prev % 2 == 0) {
            // System.out.println("prev is divided by 2");

            return prev / 2 * 3;
        }
        return -1;
    }


    public static long hamming(int n) {
        if (n == 1) {
            return 1;
        }
        return Long.valueOf((long) (Math.pow(2, n % 4) * Math.pow(3, n % 4) * Math.pow(5, n % 4)));
    }

    public static long recursiveHamming(int n) {
        if (n <= 6) {
            return n;
        }
        long prev = recursiveHamming(n - 1);

        if (prev % 6 == 0) {
            return prev / 6 * 8;
        }
        if (prev % 5 == 0) {
            return prev / 5 * 6;
        }
        if (prev % 4 == 0) {
            return prev / 4 * 5;
        }
        if (prev % 3 == 0) {
            return prev / 3 * 4;
        }
        if (prev % 2 == 0) {
            return prev / 2 * 3;
        }
        return prev * 2;
    }
}


// Java program to check given
// number y is power of x

//    public static boolean isPower(int x, int y) {
//        // logarithm function to
//        // calculate value
//        int res1 = (int) Math.log(y) /
//                (int) Math.log(x);
//
//        // Note : this is double
//        double res2 = Math.log(y) /
//                Math.log(x);
//
//        // compare to the result1 or
//        // result2 both are equal
//        return (res1 == res2);
//    }


