import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        System.out.println("better is " + betterNumOfDigitBefor(Long.valueOf("58")));
        System.out.println("connected is " + betterConncting("45", "xx"));

//        System.out.println("min is " + minOptionOfSplit("x23124125"));
        System.out.println(goodFindPosition("1234567891"));
//        System.out.println(splitToHalfAndConnect("123456798"));
    }

    public static String betterConncting(String firstNum, String secondNum) {
        int firstNumIndex = firstNum.length() - 1;
        int secondNumIndex = secondNum.length() - 1;
        int firstNumLen = firstNum.length();
        int secondNumLen = secondNum.length();
        int totalLen = firstNumLen + secondNumLen;
        int val;
        String stringOfZeros = String.format("%1$" + totalLen + "s", "").replace(' ', '0');


        if (firstNum.charAt(0) == '0' || secondNum.charAt(0) == '0') {
            return "00";
        }

        //////////////
        if (firstNumLen == 1 && secondNumLen == 1) {
            if (firstNum.equals("x") && secondNum.equals("x") || secondNum.charAt(0) == '1') {
                return "00";
            }
            if (!firstNum.equals("x")) {
                return firstNum + String.valueOf(Long.valueOf(firstNum) + 1);
            }
            return String.valueOf(Long.valueOf(secondNum) - 1) + secondNum;
        }


        ////////////


        // check if the num of digit didint fit
        if (secondNumLen > firstNumLen + 1 || firstNumLen > secondNumLen) {
            return stringOfZeros;
        }
        // hundle case of changin num of digit like 99->100 or 9999->1000 ----------------------
        if (secondNumLen == firstNumLen + 1) {
            if (!isTheyFit(firstNum, String.format("%1$" + firstNumLen + "s", "").replace(' ', '9'))) {
                return stringOfZeros;
            }
            if (!isTheyFit(secondNum, "1" + String.format("%1$" + firstNumLen + "s", "").replace(' ', '0'))) {
                return stringOfZeros;
            }
            return String.format("%1$" + firstNumLen + "s", "").replace(' ', '9') +
                    "1" + String.format("%1$" + firstNumLen + "s", "").replace(' ', '0');
        }
//----------------------------------------------------------------------------
        while (firstNumIndex >= 0) {
            if (firstNum.charAt(firstNumIndex) == 'x' && secondNum.charAt(secondNumIndex) == 'x') {
                return stringOfZeros;
            }

            if (firstNumIndex == firstNumLen - 1) {
                if (firstNum.charAt(firstNumIndex) == 'x') {
                    if (secondNum.charAt(secondNumIndex) == '0') {
                        firstNum = firstNum.substring(0, firstNumIndex) + "9" +
                                firstNum.substring(firstNumIndex + 1, firstNumLen);
                    } else {
                        val = Character.getNumericValue(secondNum.charAt(secondNumIndex)) - 1;
                        firstNum = firstNum.substring(0, firstNumIndex) + String.valueOf(val) +
                                firstNum.substring(firstNumIndex + 1, firstNumLen);

                    }
                } else {
                    if (firstNum.charAt(firstNumIndex) == '9') {
                        secondNum = secondNum.substring(0, secondNumIndex) + "0" +
                                secondNum.substring(secondNumIndex + 1, secondNumLen);
                    } else {
                        val = Character.getNumericValue(firstNum.charAt(firstNumIndex)) + 1;
                        secondNum = secondNum.substring(0, secondNumIndex) + String.valueOf(val) +
                                secondNum.substring(secondNumIndex + 1, secondNumLen);

                    }

                }

            } else {
                if (firstNum.charAt(firstNumIndex) == 'x') {
                    if (secondNum.charAt(secondNumIndex) == '0' &&
                            Math.pow(10, (double) (firstNumLen - firstNumIndex - 1)) - Long.valueOf(firstNum.substring(firstNumIndex + 1, firstNumLen)) == 1 ) {//maybe need a change !
                        firstNum = firstNum.substring(0, firstNumIndex) + "9" +
                                firstNum.substring(firstNumIndex + 1, firstNumLen);
                    } else {
                        if (Math.pow(10, (double) (firstNumLen - firstNumIndex - 1)) - Long.valueOf(firstNum.substring(firstNumIndex + 1, firstNumLen)) == 1) {
                            val = Character.getNumericValue(secondNum.charAt(secondNumIndex)) - 1;
                            firstNum = firstNum.substring(0, firstNumIndex) + String.valueOf(val) +
                                    firstNum.substring(firstNumIndex + 1, firstNumLen);
                        } else {
                            firstNum = firstNum.substring(0, firstNumIndex) + secondNum.charAt(secondNumIndex) +
                                    firstNum.substring(firstNumIndex + 1, firstNumLen);

                        }

                    }
                } else {
                    if (firstNum.charAt(firstNumIndex) == '9') {
                        if (secondNum.charAt(secondNumIndex + 1) == '0') {
                            secondNum = secondNum.substring(0, secondNumIndex) + "0" +
                                    secondNum.substring(secondNumIndex + 1, secondNumLen);
                        } else {
                            secondNum = secondNum.substring(0, secondNumIndex) + firstNum.charAt(firstNumIndex) +
                                    secondNum.substring(secondNumIndex + 1, secondNumLen);
                        }
                    } else {
                        if (Math.pow(10, (double) (firstNumLen - firstNumIndex - 1)) - Long.valueOf(firstNum.substring(firstNumIndex + 1, firstNumLen)) == 1) {
                            val = Character.getNumericValue(firstNum.charAt(firstNumIndex)) + 1;
                            secondNum = secondNum.substring(0, secondNumIndex) + String.valueOf(val) +
                                    secondNum.substring(secondNumIndex + 1, secondNumLen);
                        } else {
                            secondNum = secondNum.substring(0, secondNumIndex) + firstNum.charAt(firstNumIndex) +
                                    secondNum.substring(secondNumIndex + 1, secondNumLen);

                        }
                    }

                }


            }


            firstNumIndex--;
            secondNumIndex--;
        }
        return firstNum + secondNum;

    }


    public static String splitToHalfAndConnect(String str) {
        if (str.length() == 1) {
            return "0";
        }
        int len = str.length();
        return betterConncting(str.substring(0, len / 2), str.substring(len / 2, len));
    }

    public static String minOptionOfSplit(String str) {
        String min = String.format("%1$" + 18 + "s", "").replace(' ', '9');
        String mayBeMin;
        String subString;
        int len;
        int index = 1;
        int lastIndex = 1;


        while (index <=str.length()) {
            subString = str.substring(0, index);
            mayBeMin = splitToHalfAndConnect(subString);
            if (mayBeMin.length()<19&&Long.valueOf(mayBeMin) > 0 && Long.valueOf(mayBeMin) < Long.valueOf(min) && isASeries(mayBeMin)[0] == 1) {

                if (isTheFirstStringIsSeriesOfTheSecondString(mayBeMin + str.substring(mayBeMin.length(), str.length()), mayBeMin.substring(0, isASeries(mayBeMin)[1]))) {
                    min = mayBeMin;
                }

            }
            index++;


        }
        return min;
    }

    public static boolean isTheyFit(String firstNum, String secondNum) {
        int counter = 0;
        char firstNumChar;
        char secondNumChar;
        if (firstNum.length() != secondNum.length()) {
            return false;
        }
        while (counter < firstNum.length()) {
            firstNumChar = firstNum.charAt(counter);
            secondNumChar = secondNum.charAt(counter);
            if (firstNumChar != 'x' && secondNumChar != 'x' && firstNumChar != secondNumChar) {
                return false;

            }
            counter++;
        }
        return true;
    }

    public static String hundleChangingnumOfDigit(String firstNum, String secondNum) {
        int firstNumLen = firstNum.length();
        int secondNumLen = secondNum.length();
        int totalLen = firstNumLen + secondNumLen;
        int addOne = 0;
        int minusOne = 0;


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
        return "ok";

    }


    public static String connectTowStrings(String firstNum, String secondNum) {
//        System.out.println(firstNum + " " + secondNum);
        int firstNumLen = firstNum.length();
        int secondNumLen = secondNum.length();
        int totalLen = firstNumLen + secondNumLen;
        int addOne = 0;
        int minusOne = 0;

        String hundleAns;

        hundleAns = hundleChangingnumOfDigit(firstNum, secondNum);
        if (!hundleAns.equals("ok")) {
            return hundleAns;
        }

//        if (!isTheyFit(firstNum,String.valueOf(Long.valueOf(firstNum)+1))&&
//                !isTheyFit(String.valueOf(Long.valueOf(secondNum)-1),secondNum)){
//            return String.format("%1$" + totalLen + "s", "").replace(' ', '0');
//
//        }


        if (firstNum.chars().filter(ch -> ch == '0').count() == firstNumLen ||
                secondNum.chars().filter(ch -> ch == '0').count() == secondNumLen) {
            return String.format("%1$" + totalLen + "s", "").replace(' ', '0');
        }


        if (firstNum.chars().filter(ch -> ch == 'x').count() == firstNumLen &&
                secondNum.chars().filter(ch -> ch == 'x').count() == secondNumLen) {
            return String.format("%1$" + totalLen + "s", "").replace(' ', '0');
        }

        if (firstNum.indexOf("x") == -1) {
            if (isTheyFit(String.valueOf(Long.valueOf(firstNum) + 1), secondNum)) {
                return firstNum + String.valueOf(Long.valueOf(firstNum) + 1).substring(0, secondNumLen);
            } else {
                return String.format("%1$" + totalLen + "s", "").replace(' ', '0');

            }
        }


        if (secondNum.indexOf("x") == -1) {
            if (isTheyFit(String.valueOf(Long.valueOf(secondNum) - 1), firstNum)) {

                return String.valueOf(Long.valueOf(secondNum) - 1).substring(0, firstNumLen) + secondNum;
            } else {
                return String.format("%1$" + totalLen + "s", "").replace(' ', '0');

            }
        }

        for (
                int i = firstNumLen - 1;
                i >= 0; i--) {
            if (firstNum.charAt(i) == 'x' && secondNum.charAt(i) == 'x') {
                firstNum = firstNum.substring(0, i) + "1" + firstNum.substring(i, firstNum.length());
                secondNum = secondNum.substring(0, i) + "1" + secondNum.substring(i, firstNum.length());

            } else {
                if (i == firstNumLen - 1) {
                    if (firstNum.charAt(firstNumLen - 1) == '9') {
                        addOne = 1;

                        if (secondNum.charAt(firstNumLen - 1) == 'x') {
                            secondNum = secondNum.substring(0, i) + "0" + secondNum.substring(i + 1, firstNum.length());

                        }


                    } else {
                        addOne = 0;
                    }


                    if (firstNum.charAt(firstNumLen - 1) == 'x') {
                        if (secondNum.charAt(firstNumLen - 1) == '0') {
                            firstNum = firstNum.substring(0, i) + "9" + firstNum.substring(i + 1, firstNum.length());
                            minusOne = 1;

                        } else {
                            firstNum = firstNum.substring(0, i) + "" + (Character.getNumericValue(secondNum.charAt(firstNumLen - 1)) - 1) + firstNum.substring(i + 1, firstNum.length());
                            minusOne = 0;
                        }

                    }
                    if (secondNum.charAt(firstNumLen - 1) == 'x') {
                        secondNum = secondNum.substring(0, i) + "" + (Character.getNumericValue(firstNum.charAt(firstNumLen - 1)) + 1) + secondNum.substring(i + 1, firstNum.length());

                    }
                } else {
                    if (firstNum.charAt(i) == 'x') {//&&(Character.getNumericValue(secondNum.charAt(i))-minusOne)>-1) {
                        firstNum = firstNum.substring(0, i) + "" + (Character.getNumericValue(secondNum.charAt(i)) - minusOne) + firstNum.substring(i + 1, firstNum.length());
                        minusOne = 0;

                    }

                    if (secondNum.charAt(i) == 'x') {
                        secondNum = secondNum.substring(0, i) + "" + (Character.getNumericValue(firstNum.charAt(i)) + addOne) + secondNum.substring(i + 1, firstNum.length());
                        addOne = 0;
                    }

                }

            }

        }
        return firstNum + secondNum;
    }

    public static String findNLenSeries(String str, int n) {
//        System.out.println(str);
        String firstNum;
        String secondNum;
        String firstNumExtended;
        String secondNumExtended;
        String currentStr;
        String connectedStr;
        String min = str;
        long regularConnecting;
        long extendedConnecting;
        if (str.charAt(0) == '0') {
            min = "1" + str;
        }
        String ans = str;
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
//                    System.out.println("isssssss   " + currentStr);


                } else {
                    currentStr = String.format("%1$" + counter + "s", "").replace(' ', 'x') +
                            str + String.format("%1$" + (n - counter) + "s", "").replace(' ', 'x');

                }
            }
//            if (counter < n / 2) {
////                firstNum = currentStr.substring(currentStr.length() - n - n, currentStr.length() - n);
////                secondNum = currentStr.substring(currentStr.length() - n, currentStr.length());
//
//                connectedStr = minOptionOfSplit(firstNum + secondNum);
//
//
//            } else {
//                firstNum = currentStr.substring(0, n);
//                secondNum = currentStr.substring(n, 2 * n);
//
//                connectedStr = minOptionOfSplit(firstNum + secondNum);
//
//            }
                            connectedStr = minOptionOfSplit(currentStr);

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
        String minSeriesStartNum = findNLenSeries(str, str.length());
        int counter = 1;

        while (counter <= str.length()) {
            if (Long.valueOf(findNLenSeries(str, counter))>0&&Long.valueOf(findNLenSeries(str, counter)) < Long.valueOf(minSeriesStartNum)) {
                minSeriesStartNum = findNLenSeries(str, counter);
            }
            counter++;
        }
        counter = 1;

        String allSeries = minSeriesStartNum;

        while (allSeries.indexOf(str) == -1) {
            allSeries = allSeries + String.valueOf(Long.valueOf(minSeriesStartNum) + counter);
            counter++;

        }
        return betterNumOfDigitBefor(Long.valueOf(minSeriesStartNum)) + allSeries.indexOf(str);
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


}
