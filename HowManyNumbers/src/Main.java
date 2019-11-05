import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Long> answerList = new ArrayList<>();
        long minValue = findMin(45, 9);
        long numberOfValidNumber = howManyNumbersExist(45, 9, 1);
        if (numberOfValidNumber == 0) {
            System.out.println(Arrays.toString((new ArrayList<Long>()).toArray()));
            return;
        }
        answerList.add(numberOfValidNumber);
        answerList.add(minValue);
        answerList.add(findMax(45, 9));
        System.out.println(Arrays.toString(answerList.toArray()));
        System.out.println(findMin(45, 9));


    }


    public static long sumOfDigits(long num) {
        long sum = 0;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        return sum;
    }

    public static boolean isDigitsInIncreasingOrder(long num) {
        String numInString = String.valueOf(num);
        for (int i = 0; i < numInString.length() - 1; i++) {
            if (Character.getNumericValue(numInString.charAt(i)) > Character.getNumericValue(numInString.charAt(i + 1))) {
                return false;
            }
        }

        return true;
    }

    public static long findMax(int sumOfDigits, int numOfDigits) {
        if (numOfDigits == 1) {
            return sumOfDigits;
        }
        int maxDigit = 1;
        long localSum = maxDigit + (maxDigit) * (numOfDigits - 1);
        while (localSum < sumOfDigits) {
            maxDigit++;
            localSum = maxDigit + (maxDigit) * (numOfDigits - 1);
        }
        if (localSum > sumOfDigits) {
            maxDigit--;
        }
        return maxDigit * (long) Math.pow(10, numOfDigits - 1) + findMax(sumOfDigits - maxDigit, numOfDigits - 1);
    }

    public static long findMin(int sumOfDigits, int numOfDigits) {
        if (numOfDigits == 1) {
            return sumOfDigits;
        }
        int minDigit = 1;
        long localSum = minDigit + 9 * (numOfDigits - 1);
        while (localSum < sumOfDigits) {
            minDigit++;
            localSum = minDigit + 9 * (numOfDigits - 1);
        }
        if (localSum < sumOfDigits) {
            minDigit++;
        }
        return minDigit * (long) Math.pow(10, numOfDigits - 1) + findMin(sumOfDigits - minDigit, numOfDigits - 1);
    }


    public static int howManyNumbersExist(int sumOfDigits, int numOfDigits, int previousDigit) {
        if (numOfDigits == 1) {
            return 1;
        }

        int ans = 0;

        for (int i = previousDigit; i < 10; i++) {
            if (i * numOfDigits <= sumOfDigits && i + 9 * (numOfDigits - 1) >= sumOfDigits) {
                ans = ans + howManyNumbersExist(sumOfDigits - i, numOfDigits - 1, i);
            }
        }

        return ans;
    }
}
