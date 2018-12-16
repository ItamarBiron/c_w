import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        System.out.println("better is " + betterNumOfDigitBefor(Long.valueOf("454")));
        System.out.println("try connect " + connectTowStrings("85", "xx"));
        String num = "454";
        String ans = findNLenSeries(num, 2);
        System.out.println("findNLenSeries " + ans);
        System.out.println(betterNumOfDigitBefor(Long.valueOf(ans)));
        System.out.println(goodFindPosition("555899959741198"));
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
