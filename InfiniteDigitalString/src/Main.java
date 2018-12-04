import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("better is " + betterNumOfDigitBefor(Long.valueOf("10")));
        //System.out.println("numOfDigitBefor is " + numOfDigitBefor(Long.valueOf("99")));
        //System.out.println("isThisStringIsASeriesOfNdigitNumber  " + isThisStringIsASeriesOfNdigitNumber("9102", 4));
        System.out.println("isASeries  " + Arrays.toString(isASeries("99100")));
//        System.out.println(cutSeries("123456789101113"));
        System.out.println(checkFromRight("454"));
        System.out.println(checkFromLeft("455"));

    }

    public static String createStrringUntilNum(Long num) {
        String ans = "";
        for (int i = 1; i <= num; i++) {
            ans = ans + String.valueOf(i);
        }
        return ans;
    }

    public static Long numOfDigit(Long num) {
        Long count = (long) 0;
        while (num > 0) {
            count++;
            num = num / 10;
        }
        return count;
    }


    public static Long betterNumOfDigitBefor(Long num) {
        Long numberOfDigit = numOfDigit(num);
        if (num <= 10) {
            return num - 1;
        }
        Long ans = (num - (long) Math.pow(10, (double) (numberOfDigit - 1))) * numberOfDigit + 1;
        num = (long) Math.pow(10, (double) (numberOfDigit));
        numberOfDigit--;
        while (numberOfDigit > 1) {
            ans = ans + ((long) Math.pow(10, (double) (numberOfDigit)) - (long) Math.pow(10, (double) (numberOfDigit - 1))) * numberOfDigit + 1;
            numberOfDigit--;
        }
        ans = ans - numOfDigit(num) + 2; // need to substruct
        ans = ans + 9;
        return ans;
    }

    public static Long numOfDigitBefor(Long num) {
        Long count = (long) 1;
        Long ans = (long) 0;
        while (count < num) {
            ans = ans + numOfDigit(count);
            count++;
        }
        return ans;
    }


    public static boolean isThisStringIsASeriesOfNdigitNumber(String str, int n) {
        int index = 0;
        String expectedAns = str.substring(0, n);
        if (n > str.length() / 2 + 1 || str.charAt(0) == '0') {
            return false;
        }
        long num = Long.valueOf(str.substring(0, n)) + 1;

        while (expectedAns.length() < str.length() && expectedAns.equals(str.substring(0, expectedAns.length()))) {
            expectedAns = expectedAns + String.valueOf(num);
            num++;
        }
        if (str.equals(expectedAns)) {
            return true;
        } else {
            while (index < str.length()) {
                if (str.charAt(index) != expectedAns.charAt(index)) {
                    return false;
                }
                index++;
            }

        }
        return false;

    }

    public static int[] isASeries(String str) {
        int count = 1;
        long len = str.length();
        int counter = 1;
        int index;
        int[] answer = new int[2];
        answer[0] = 0; // contain if there is a series of close numbers
        answer[1] = -1; // contain the num of digitis of the first number

        while (counter <= len / 2) {
            if (isThisStringIsASeriesOfNdigitNumber(str, counter)) {
                answer[0] = 1;
                answer[1] = counter;

            }

            counter++;

        }
        return answer;
    }


    public static String reverseString(String str) {
        int len = str.length();
        char[] answer = new char[len];

        for (int i = 0; i < len; i++) {
            answer[i] = str.charAt(len - i - 1);
        }

        return String.valueOf(answer);

    }

    /**
     *
     * check what is the minimal number that is start of a series that contain str if we start check from right side of the first digit
     *
     */
    public static long checkFromRight(String str) {
        String subString = "";
        String subStringInWhile = "";
        int index = 1;
        long min = Long.valueOf(str);
        long val;
        long i ;
        while (subString.length() < str.length()) {
            subString = str.substring(0, index);
            if ( subString.length()==str.length()){
                break;
            }
            int wasContain = subString.indexOf(str);
            subStringInWhile = subString;
            val = Long.valueOf(subString);
            i=1;
            while (subStringInWhile.length() <= str.length() && wasContain == -1) {
                subStringInWhile = subStringInWhile + String.valueOf(val+ i);
                 wasContain = subStringInWhile.indexOf(str);
                 i++;

            }
            if( wasContain !=-1) {
                int lenOfNumber = isASeries(subStringInWhile)[1];
                String firsNum = subStringInWhile.substring(0, lenOfNumber);
                if (Long.valueOf(firsNum) < min && wasContain != -1) {
                    min = Long.valueOf(firsNum);
                }
            }

            index++;
        }
        return min;
    }


    /**
     *
     * check what is the minimal number that is start of a series that contain str if we start check from left side of the first digit
     *
     */
    public static long checkFromLeft(String str) {
        String subString = "";
        String subStringInWhile = "";
        int index = 1;
        long min = Long.valueOf(str);
        long val;
        long i ;
        while (subString.length() < str.length()) {
            subString = str.substring(str.length()-index, str.length());
            if ( subString.length()==str.length()){
                break;
            }
            int wasContain = subString.indexOf(str);
            subStringInWhile = subString;
            val = Long.valueOf(subString);
            i=1;
            while (subStringInWhile.length() <= str.length() && wasContain == -1) {
                subStringInWhile = String.valueOf(val- i)+subStringInWhile ;
                wasContain = subStringInWhile.indexOf(str);
                i++;

            }
            if( wasContain !=-1) {
                int lenOfNumber = isASeries(subStringInWhile)[1];
                String firsNum = subStringInWhile.substring(0, lenOfNumber);
                if (Long.valueOf(firsNum) < min && wasContain != -1) {
                    min = Long.valueOf(firsNum);
                }
            }

            index++;
        }
        return min;
    }

    public static Long doAll(String str) {
        int[] answer = new int[2];
        String subString;
        answer = isASeries(str);
        if (answer[0] == 1) {
            return numOfDigitBefor(Long.valueOf(str.substring(0, answer[1])));
        }
        return (long) 1;

    }


}
