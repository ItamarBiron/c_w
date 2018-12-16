import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        System.out.println("better is " + betterNumOfDigitBefor(Long.valueOf("454")));
        //System.out.println("numOfDigitBefor is " + numOfDigitBefor(Long.valueOf("99")));
        System.out.println("isThisStringIsASeriesOfNdigitNumber  " + isThisStringIsASeriesOfNdigitNumber("106011111", 8));
        System.out.println("isASeries  " + Arrays.toString(isASeries("9101")));
//        System.out.println(cutSeries("123456789101113"));
//        System.out.println(checkFromRight("676"));
//        System.out.println(checkFromLeft("99"));
//        System.out.println(checkFromLeftForShifted("67"));
//        System.out.println("shiftFromIndex " + shiftFromIndex("1234",2));
//        System.out.println(Arrays.toString(addFromLeft("3999589058124",99)));
//        System.out.println(shift("43255"));
        //System.out.println(doAll("05467480"));
//        System.out.println(doAll("33660"));//6957586376885
//        System.out.println(doAll("234567"));
//        System.out.println(isTheFirstStringIsSeriesOfTheSecondString("520844485", "44485208"));
//        System.out.println(doAll("3999589058124"));
//        System.out.println(cutAndCheck("091"));
//        System.out.println(findMinOfCuted("091"));
//        System.out.println(numberFromNumberOfDigits("109"));
//        System.out.println(minSeriesOfLenN("1234123", 4));
//        System.out.println("minSeries " + minSeries("12345123"));
        String val = "213";
        long minNum = check(val)[0];
        long numToDigit = check(val)[1];
        System.out.println(minNum + " " + numToDigit);

        System.out.println(betterNumOfDigitBefor(Long.valueOf(minNum)) + numToDigit);
//        System.out.println(findNLenSeries("123",3));
        System.out.println("try connect " + connectTowStrings("85", "xx"));
        String num = "454";
        String ans = findNLenSeries(num, 2);
        System.out.println("findNLenSeries " + ans);
        System.out.println(betterNumOfDigitBefor(Long.valueOf(ans)));
        System.out.println(goodFindPosition("457305821867254"));
//        System.out.println(String.format("%1$" + 1 + "s", "").replace(' ', 'x'));


    }

    public static String connectTowStrings(String firstNum, String secondNum) {
        int firstNumLen = firstNum.length();
        int secondNumLen = secondNum.length();
        int totalLen = firstNumLen + secondNumLen;
        int addOne = 0;


        if (secondNum.length() > firstNum.length() + 1 || firstNumLen > secondNumLen) {
            return String.format("%1$" + totalLen + "s", "").replace(' ', '0'); // return string of zeros
        }


        // hundle the case to diffrent num size like 9 -> 10 and 999-> 1000 ------------------------
        if (secondNumLen == firstNumLen + 1) {
            for (int i = 0; i < firstNumLen; i++) {
                if (firstNum.charAt(i) == 'x') {
                    firstNum = firstNum.substring(0, i) + "9" + firstNum.substring(i + 1, firstNum.length());

                } else {
                    if (firstNum.charAt(i) != '9') {
                        return String.format("%1$" + totalLen + "s", "").replace(' ', '0');
                    }
                }
            }


            if (secondNum.charAt(0) == 'x') {
                secondNum = "1" + secondNum.substring(1, secondNum.length());

            } else {
                if (secondNum.charAt(0) != '1') {
                    return String.format("%1$" + totalLen + "s", "").replace(' ', '0');
                }
            }


            for (int i = 1; i < secondNumLen; i++) {
                if (secondNum.charAt(i) == 'x') {
                    secondNum = secondNum.substring(0, i) + "0" + secondNum.substring(i + 1, secondNum.length());

                } else {
                    if (secondNum.charAt(i) != '0') {
                        return String.format("%1$" + totalLen + "s", "").replace(' ', '0');
                    }
                }
            }
        }

        ////  --------------------------------------------------------------------------------------------


        for (int i = firstNumLen - 1; i >= 0; i--) {
            if (firstNum.charAt(i) == 'x' && secondNum.charAt(i) == 'x') {
                firstNum = firstNum.substring(0, i) + "1" + firstNum.substring(i, firstNum.length());
                secondNum = secondNum.substring(0, i) + "1" + secondNum.substring(i, firstNum.length());

            } else {
                if (i == firstNumLen - 1) {
                    if (firstNum.charAt(firstNumLen - 1) == '9') {

                        if (secondNum.charAt(firstNumLen - 1) == 'x') {
                            secondNum = secondNum.substring(0, i) + "0" + secondNum.substring(i + 1, firstNum.length());

                        }


                    }


                    if (firstNum.charAt(firstNumLen - 1) == 'x') {
                        firstNum = firstNum.substring(0, i) + "" + (Character.getNumericValue(secondNum.charAt(firstNumLen - 1)) - 1) + firstNum.substring(i + 1, firstNum.length());

                    }
                    if (secondNum.charAt(firstNumLen - 1) == 'x') {
                        secondNum = secondNum.substring(0, i) + "" + (Character.getNumericValue(firstNum.charAt(firstNumLen - 1)) + 1) + secondNum.substring(i + 1, firstNum.length());

                    }
                } else {
                    if (firstNum.charAt(i) == 'x') {
                        firstNum = firstNum.substring(0, i) + "" + secondNum.charAt(i) + firstNum.substring(i + 1, firstNum.length());

                    }
                    if (secondNum.charAt(i) == 'x') {
                        secondNum = secondNum.substring(0, i) + "" + firstNum.charAt(i) + secondNum.substring(i + 1, firstNum.length());

                    }

                }

            }

        }
        return firstNum + secondNum;
    }

    public static String findNLenSeries(String str, int n) {
        String firstNum;
        String secondNum;
        String currentStr;
        String connectedStr;
        String min = str;
        if (str.charAt(0) == '0') {
            min = "1" + str;
        }
        String ans = str;
        int numOfNumber = str.length() / n;
        int counter = 0;
        int[] isSeriesAns = new int[2];
        isSeriesAns = isASeries(str);

        if (isSeriesAns[0] == 1) {
            return str.substring(0, isSeriesAns[1]);
        }

        while (counter <= n) {
            if (counter == 0) {
                currentStr = str + String.format("%1$" + n + "s", "").replace(' ', 'x');
            } else {
                if (counter == n) {
                    currentStr = String.format("%1$" + n + "s", "").replace(' ', 'x') + str;

                } else {
                    currentStr = String.format("%1$" + counter + "s", "").replace(' ', 'x') +
                            str + String.format("%1$" + (n - counter) + "s", "").replace(' ', 'x');

                }
            }
            if (counter < n / 2) {
                firstNum = currentStr.substring(currentStr.length() - 1 - n - n + 1, currentStr.length() - n);
                secondNum = currentStr.substring(currentStr.length() - n, currentStr.length());
                connectedStr = connectTowStrings(firstNum, secondNum);

            } else {
                firstNum = currentStr.substring(0, n);
                secondNum = currentStr.substring(n, 2 * n);
                connectedStr = connectTowStrings(firstNum, secondNum);
            }
            isSeriesAns = isASeries(connectedStr);
            if (isSeriesAns[0] == 1 && Long.valueOf(connectedStr.substring(0, isSeriesAns[1])) < Long.valueOf(min) &&
                    isTheFirstStringIsSeriesOfTheSecondString(str, connectedStr.substring(0, isSeriesAns[1]))) {
                min = connectedStr.substring(0, isSeriesAns[1]);
            }
            counter++;
        }

        return min;
    }


    public static long goodFindPosition(String str) {
        String minSeriesStartNum = findNLenSeries(str,str.length());
        int counter =1;

        while ( counter<str.length()){
            if(Long.valueOf(findNLenSeries(str,counter))<Long.valueOf(minSeriesStartNum)){
                minSeriesStartNum = findNLenSeries(str,counter);
            }
            counter++;
        }
        counter =1;

        String allSeries = minSeriesStartNum;

        while (allSeries.indexOf(str)==-1){
            allSeries = allSeries + String.valueOf(Long.valueOf(minSeriesStartNum)+counter);
            counter++;

        }
        return betterNumOfDigitBefor(Long.valueOf(minSeriesStartNum))+allSeries.indexOf(str);
    }

    public static String minSeriesOfLenN(String str, int n) {
        String ans = str.substring(0, n);
        long val = Long.valueOf(ans);
        long count = 1;
        int[] isSeriesAns = isASeries(str);
        if (isSeriesAns[0] != 0) {
            return str.substring(0, isSeriesAns[1]);
        }
        while (ans.indexOf(str) == -1 && ans.length() < 19) {
            ans = ans + String.valueOf(val + count);
            count++;
        }
        if (ans.indexOf(str) != -1) {
            // return ans.substring(ans.indexOf(str), ans.length());
            return ans.substring(0, isASeries(ans)[1]);
        }
        return str;


    }


    public static String minSeries(String str) {
        int counter = 1;
        String minimalString = str;

        while (counter < str.length()) {
            if (Long.valueOf(minSeriesOfLenN(str, counter)) < Long.valueOf(minimalString)) {
                minimalString = minSeriesOfLenN(str, counter);
            }
            counter++;

        }

        return minimalString;
    }


    public static long[] check(String str) {
        int[] isSeriesAns = new int[2];
        isSeriesAns = isASeries(str);
        String stringToCheck = str;
        String currentMinSeries = str;
        String min = minSeries(str);
        long[] ans = new long[2];
        ans[0] = 0;
        ans[1] = 0;
        long addDigits = 0;
        if (isSeriesAns[0] == 1) {
            ans[0] = Long.valueOf(str.substring(0, isSeriesAns[1]));
            ans[1] = addDigits;
            return ans;
        }
        long counter = 1;
//        stringToCheck = String.valueOf(count)+stringToCheck  ;

//        currentMinSeries = minSeries(stringToCheck);
//        while (stringToCheck.indexOf(str) == -1) {
//            stringToCheck = String.valueOf(count)+stringToCheck  ;
//            currentMinSeries = minSeries(stringToCheck);
//            count ++;
//        }

        while (currentMinSeries.length() < 2 * str.length()) {
            if (Long.valueOf(minSeries(currentMinSeries)) < Long.valueOf(min)
                    && isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(min))) {
                min = minSeries(currentMinSeries);
            }
            currentMinSeries = String.valueOf(counter) + str;
            counter++;

        }
        counter = 1;
        String finalString = min;
        while (finalString.indexOf(str) == -1) {
            finalString = finalString + String.valueOf(Long.valueOf(min) + counter);
            counter++;
        }
        if (finalString.indexOf(str) != -1) {
            ans[0] = Long.valueOf(min);
            ans[1] = finalString.indexOf(str);
        }
        return ans;
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


    public static long numberFromNumberOfDigits(String num) {
        long counter = (long) Math.pow(10, (double) (num.length() / 10));
        while (betterNumOfDigitBefor(counter) <= Long.valueOf(num)) {
            counter++;
            if (counter % 1000000 == 0) {
                System.out.println(counter);
            }

        }
        return counter;
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
            while (subStringInWhile.length() <= 16 * 3 && wasContain == -1) {// changed !
                if (val - i == 0) {
                    break;
                }
                subStringInWhile = String.valueOf(Long.valueOf(val - i)) + subStringInWhile;
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
            if (val - i == 0) {
                break;
            }
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

    public static String shiftFromIndex(String str, int index, boolean wasFirst) {
        String first = str.substring(index, str.length());
        String second = str.substring(0, index);
//
        if (wasFirst) {
            if (index > 0 && index < str.length() && first.charAt(first.length() - 1) == second.charAt(0)
                    && isTheFirstStringIsSeriesOfTheSecondString(str, first + second.substring(1, second.length()))) {
                return first + second.substring(1, second.length());

            }

        }


        return first + second;

    }

    public static String shift(String str, boolean wasFirst) {
        int minVal = 10;
        int currentCharVal;
        int index = 0;
        String answer = "";
        String minialString = str;
        String first;
        char second;
        if (str.charAt(0) == '0') {
            minialString = "1" + str;
        }


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
            mayBeMinimalString = shiftFromIndex(str, index, wasFirst);
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
        long min = currentMin;
        long checkFromRightAns = currentMin;
        answer[0] = min;
        answer[1] = 0; // in first we add 0 digit to the number
        long counter = 1;
        int indexUpOfTen = 1;
        String newString = String.valueOf(counter) + str.substring(indexUpOfTen, str.length());

        while (newString.length() < 16 && !isTheFirstStringIsSeriesOfTheSecondString(str, newString)) { //checkFromRightAns <= currentMin) { // TODO : check if we need to change 1.5 to other value

            checkFromRightAns = checkFromRight(newString);
            if (checkFromRightAns < min) {
                min = checkFromRightAns;
                answer[0] = min;
                answer[1] = String.valueOf(counter).length();

            }

            counter++;
            if (counter % 10 == 0) {
                indexUpOfTen++;
            }

            newString = String.valueOf(counter) + str.substring(indexUpOfTen, str.length());
        }
        if (newString.length() < 16) {
            answer[0] = Long.valueOf(newString);
        }
        return answer;
    }

    /**
     * @param str
     * @param subString
     * @return ture if the first string is in series of the second string s
     */
    public static boolean isTheFirstStringIsSeriesOfTheSecondString(String str, String subString) {
        long val = Long.valueOf(subString);
        int index = subString.indexOf(str);
        long counter = 1;
        while (index == -1 && subString.length() < 3 * str.length()) {
            subString = subString + String.valueOf(val + counter);
            index = subString.indexOf(str);
            counter++;
        }

        if (index != -1) {
            return true;
        }

        return false;

    }

    public static String maxDigit(String str) {
        int index = 0;
        String maxDigit = "0";
        while (index < str.length()) {
            if (Character.getNumericValue(str.charAt(index)) > Long.valueOf(maxDigit)) {
                maxDigit = str.charAt(index) + "";
            }
            index++;
        }
        return maxDigit;
    }

    public static int findFirstIndexThatMakeSeries(String str, int indexOf, String minimalString) {
        int counter = 1;
        String allSeries = minimalString;
        while (indexOf == -1 && allSeries.length() < 3 * str.length()) {
            allSeries = allSeries + String.valueOf(Long.valueOf(minimalString) + counter);
            indexOf = allSeries.indexOf(str);
            counter++;


        }
        return indexOf;

    }


}
