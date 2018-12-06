import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("better is " + betterNumOfDigitBefor(Long.valueOf("45")));
        //System.out.println("numOfDigitBefor is " + numOfDigitBefor(Long.valueOf("99")));
        //System.out.println("isThisStringIsASeriesOfNdigitNumber  " + isThisStringIsASeriesOfNdigitNumber("9102", 4));
//        System.out.println("isASeries  " + Arrays.toString(isASeries("99100")));
//        System.out.println(cutSeries("123456789101113"));
//        System.out.println(checkFromRight("676"));
//        System.out.println(checkFromLeft("67"));
//        System.out.println(checkFromLeftForShifted("67"));
//        System.out.println("shiftFromIndex " + shiftFromIndex("1234",2));
//        System.out.println(Arrays.toString(addFromLeft("91001")));
//        System.out.println(shift("43255"));
        //System.out.println(doAll("05467480"));
//        System.out.println(doAll("33660"));
        System.out.println(doAll("454"));


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
     * check what is the minimal number that is start of a series that contain str if we start check from right side of the first digit
     */
    public static long checkFromRight(String str) {
        String subString = "";
        String subStringInWhile = "";
        int index = 1;
        long min = Long.valueOf(str);
        long val;
        long i;
        while (subString.length() < str.length()) {
            subString = str.substring(0, index);
            if (subString.length() == str.length()) {
                break;
            }
            int wasContain = subString.indexOf(str);
            subStringInWhile = subString;
            val = Long.valueOf(subString);
            i = 1;
            while (subStringInWhile.length() <= str.length() && wasContain == -1) {
                subStringInWhile = subStringInWhile + String.valueOf(val + i);
                wasContain = subStringInWhile.indexOf(str);
                i++;

            }
            if (wasContain != -1) {
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
     * check what is the minimal number that is start of a series that contain str if we start check from left side of the first digit
     */
    public static long checkFromLeft(String str) {
        String subString = "";
        String subStringInWhile = "";
        int index = 1;
        long min = Long.valueOf(str);
        long val;
        long i;
        while (subString.length() < str.length()) {
            subString = str.substring(str.length() - index, str.length());

            while (subString.charAt(0) == '0') {
                index++;
                subString = str.substring(str.length() - index, str.length());

            }
            if (subString.length() == str.length()) {
                break;
            }
            int wasContain = subString.indexOf(str);
            subStringInWhile = subString;
            val = Long.valueOf(subString);
            i = 1;
            while (subStringInWhile.length() <= str.length() && wasContain == -1) {
                subStringInWhile = String.valueOf(val - i) + subStringInWhile;
                wasContain = subStringInWhile.indexOf(str);
                i++;

            }
            if (wasContain != -1) {
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
     * check what is the minimal number that is start of a series that contain str if we start check from left side of the first digit
     */
    public static long[] checkFromLeftForShifted(String str) {
        String subString = str;
        String subStringInWhile = "";
        int index = 1;
        long min = Long.valueOf(str);
        long val = Long.valueOf(str);
        long i = 1;
        long wantedIndex;
        long[] answer = new long[2];
        answer[0] = min;
        answer[1] = 0; // num of digit to add
        while (subString.length() < 2 * str.length()) {
            subString = Long.valueOf(val - i) + subString;
            i++;
            wantedIndex = subString.indexOf(str);
            if (wantedIndex != -1)
                min = Long.valueOf(subString.substring(0, str.length()));
            answer[0] = min;
            answer[1] = wantedIndex + 1;
            break;
        }


        return answer;
    }

    public static String shiftFromIndex(String str, int index) {
        return str.substring(index, str.length()) + str.substring(0, index);
    }

    public static String shift(String str) {
        int minVal = 10;
        int currentCharVal;
        int index = 0;
        String answer = "";
        String minialString = str;
        String mayBeMinimalString = str;
        while (index < str.length()) {
            if (str.charAt(index) != '0' && Character.getNumericValue((str.charAt(index))) < minVal) {
                minVal = Character.getNumericValue(str.charAt(index));
            }
            index++;
        }
        index = 0;
        while (index < str.length()) {
            currentCharVal = Character.getNumericValue(str.charAt(index));
            mayBeMinimalString = shiftFromIndex(str, index);
            if (currentCharVal == minVal && Long.valueOf(mayBeMinimalString) < Long.valueOf(minialString)) {
                minialString = mayBeMinimalString;
            }
            index++;
        }
        return minialString;
    }

    /**
     * @param str
     * @return the minimal value for adding digit in the left side of the string
     */
    public static long[] addFromLeft(String str, long currentMin) {
        long[] answer = new long[2];
        String newString = str;
        long min = currentMin;
        long checkFromRightAns = currentMin;
        answer[0] = min;
        answer[1] = 0; // in first we add 0 digit to the number
        long counter = 1;
        while (newString.length() < 16 && checkFromRightAns <= currentMin) { // TODO : check if we need to change 1.5 to other value
            newString = String.valueOf(counter) + str;
            checkFromRightAns = checkFromRight(newString);
            if (checkFromRightAns < min) {
                min = checkFromRightAns;
                answer[0] = min;
                answer[1] = String.valueOf(counter).length();

            }

            counter++;
        }
        return answer;
    }

    public static Long doAll(String str) {
        int[] answer = new int[2];
        String subString;
        String shiftedString;
        long minOfStr;
        long minOfShiftStr;
        long minOfAll;
        long addSomeDigit = 0;
        int index = str.length() - 1;
        long counter;
        long minFromAddToLeft;
        long numberOfDigitAdded;
        long[] checkFromLeftShiftedAnswer = new long[2];
        long checkFromLeftShiftedMin;
        long checkFromLeftShiftedExtraDigit;
        long[] addFromLeftAnswer = new long[2];
        String allSeries;

        if (str.charAt(0) == '0') {
            while (index > 0 && str.charAt(index) == '0') {
                index--;
            }
            if (str.charAt(index) == '0') {
                return betterNumOfDigitBefor(Long.valueOf("1" + str)) + 1;
            }

            String firstPart = str.substring(index, str.length());
            String secondPart = str.substring(0, index);
            str = firstPart + secondPart;
            addSomeDigit = firstPart.length();
        }

        long powerOfTenLesOne =  (long)Math.pow((double)10, (double)str.length()) - 1;
        char firstChar = str.charAt(0);
        char lastChar = str.charAt(str.length() - 1);
        if ( Long.valueOf(str) !=powerOfTenLesOne&& firstChar ==lastChar ) {
            str = str.substring(0, str.length()-1);
        }

        answer = isASeries(str);
        if (answer[0] == 1) {
            return betterNumOfDigitBefor(Long.valueOf(str.substring(0, answer[1]))) + addSomeDigit;
        }
        shiftedString = shift(str);
        minOfStr = Math.min(checkFromLeft(str), checkFromRight(str));
        minOfShiftStr = Math.min(checkFromLeft(shiftedString), checkFromRight(shiftedString));
        minOfAll = Math.min(minOfStr, minOfShiftStr);
        counter = 1;
        addFromLeftAnswer = addFromLeft(str, minOfAll);
        minFromAddToLeft = addFromLeftAnswer[0];
        numberOfDigitAdded = addFromLeftAnswer[1];
        minOfAll = Math.min(minOfAll, minFromAddToLeft);
        checkFromLeftShiftedAnswer = checkFromLeftForShifted(shiftedString);
        checkFromLeftShiftedMin = checkFromLeftShiftedAnswer[0];// need to change function
        checkFromLeftShiftedExtraDigit = checkFromLeftShiftedAnswer[1];
        allSeries = String.valueOf(minOfAll);
        int indexOf = allSeries.indexOf(str);


        while (indexOf == -1) {
            allSeries = allSeries + String.valueOf(minOfAll + counter);
            indexOf = allSeries.indexOf(str);
            counter++;
            if (allSeries.length() > 2 * str.length()) {
                if (minOfStr < checkFromLeftShiftedMin) {
                    return betterNumOfDigitBefor(Math.min(minOfStr, checkFromLeftShiftedMin)) + addSomeDigit + indexOf;
                }
                return betterNumOfDigitBefor(checkFromLeftShiftedMin) + addSomeDigit + checkFromLeftShiftedExtraDigit;

            }
        }
        return betterNumOfDigitBefor(minOfAll) + addSomeDigit + indexOf;


    }


}
