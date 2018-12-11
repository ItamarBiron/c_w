import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("better is " + betterNumOfDigitBefor(Long.valueOf("14")));
        //System.out.println("numOfDigitBefor is " + numOfDigitBefor(Long.valueOf("99")));
//       System.out.println("isThisStringIsASeriesOfNdigitNumber  " + isThisStringIsASeriesOfNdigitNumber("106011111", 8));
//        System.out.println("isASeries  " + Arrays.toString(isASeries("110")));
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
        System.out.println(doAll("3999589058124"));
        System.out.println(cutAndCheck("3999589058124"));
        System.out.println(findMinOfCuted("44"));
        // System.out.println(numberFromNumberOfDigits("44"));


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

    public static long findMinOfCuted(String originalString) {


        int index = 0;
        String first;
        char second;
        String str =originalString;
        boolean wasFirst = true;
        if (originalString.length()>2){
            while (index < str.length() - 1) {
                first = String.valueOf(Long.valueOf(str.substring(0, str.length() - index)) + 1);
                second = str.charAt(str.length() - 1);
                if (first.charAt(0) == second) {
                    str = str.substring(0,str.length()-1);
                    wasFirst = false;
                    break;
                }
                index++;
            }
            index = 0;

        }



        int len = str.length();
        long min = Long.valueOf("1" + str);
        long mayBeMin = min;
        long ultimateMin = min;
        int minIndex = len - 1;
        int addDigit = 0;
        int which = 0;
        String minimalString = String.valueOf(min);

        String shiftedByIndexString;
        //589058123999
        while (index < len) {
            shiftedByIndexString = shiftFromIndex(str, index, wasFirst);
            String valueOfCutAndCheck =String.valueOf(cutAndCheck(shiftedByIndexString));
            boolean condition = isTheFirstStringIsSeriesOfTheSecondString(originalString,valueOfCutAndCheck );
            if (cutAndCheck(shiftedByIndexString) < (Long.valueOf(shiftedByIndexString)) &&
                    shiftedByIndexString.length() == len &&condition) {
                mayBeMin = cutAndCheck(shiftedByIndexString);
                which = 0;
            } else {
                mayBeMin = Long.valueOf(shiftedByIndexString);
                which = 1;
            }
            //mayBeMin = Math.min(cutAndCheck(shiftedByIndexString), betterNumOfDigitBefor(Long.valueOf(shiftedByIndexString)));
            if (mayBeMin < min && mayBeMin > 0 && isTheFirstStringIsSeriesOfTheSecondString(originalString, String.valueOf(mayBeMin)) &&
                    shiftedByIndexString.charAt(0) != '0') {
                min = mayBeMin;
                minimalString = String.valueOf(mayBeMin);
            }
//
//            if (betterNumOfDigitBefor(Long.valueOf(shiftedByIndexString)) < betterNumOfDigitBefor(min) && shiftedByIndexString.charAt(0) != '0' &&
//                    isTheFirstStringIsSeriesOfTheSecondString(str,shiftedByIndexString)) {
//                min = Long.valueOf(shiftedByIndexString);
//                minimalString =shiftedByIndexString;
//                minIndex = index;
//                addDigit =0;
//            }
//            if (mayBeMin < min && shiftedByIndexString.charAt(0) != '0' && shiftedByIndexString.length() == len &&
//            isTheFirstStringIsSeriesOfTheSecondString(str,shiftedByIndexString)) {
//                min = mayBeMin;
//                minimalString =shiftedByIndexString;
//                minIndex = index;
//
//            }
            index++;
        }

        if (doAll(minimalString) < Long.valueOf(minimalString) &&
                isTheFirstStringIsSeriesOfTheSecondString(originalString, String.valueOf(doAll(minimalString)))) {
            minimalString = String.valueOf(doAll(minimalString));

        }
        String minimalStringMinosOne = minimalString;
        if (Long.valueOf(minimalString) - 1 > 0) {
            minimalStringMinosOne = String.valueOf(Long.valueOf(minimalString) - 1);
        }

        int addLen = 0;

        if (isTheFirstStringIsSeriesOfTheSecondString(originalString, minimalStringMinosOne)) {
            minimalString = minimalStringMinosOne;
            addLen = minimalString.length();
        }

        String allSeries = minimalString;

        int indexOf = allSeries.indexOf(str);
        int counter = 1;

        indexOf = findFirstIndexThatMakeSeries(originalString, indexOf, minimalString);

        if (indexOf == -1) {
            return betterNumOfDigitBefor(doAll(str));
        }


        return betterNumOfDigitBefor(Long.valueOf(minimalString)) + indexOf + addDigit;
//        return min +str.length()-minIndex;

    }

    public static long cutAndCheck(String originalString) {

        int addDigit = 0;
        boolean wasFirst = true;

        String str = originalString;

        if (Long.valueOf(originalString) == 0) {
            return betterNumOfDigitBefor(Long.valueOf("1" + originalString)) + 1;
        }

        if (originalString.charAt(0) == '0') {
            str = shift(originalString, wasFirst);
            wasFirst = false;

//            str = maxDigit(str) + str;
            // addDigit =1;
        }

        int index = 1;
        long min = Long.valueOf(str);
        String minimalString = str;
        String subString = str;//String.valueOf(Long.valueOf(str.substring(index, str.length()))-1);
        String subStringMinusOne;
        String subStringShiftedMinusOne;
        String subStringShifted;

        index++;
        long subStringLocation;
        long minimalStringDoAllAnswer;
        long shiftedStringLocation;

        long subStringMinusOneDoAllAnswer;
        long subStringShiftedMinusOneDoAllAnswer;
        int firstIndex = 0;
        int secondIndex = 0;


        while (firstIndex < str.length() / 2) {


            subStringLocation = doAll(subString);
            minimalStringDoAllAnswer = doAll(minimalString);
            subStringShifted = shift(subString, wasFirst);
            if (subString.length() < str.length()) {
                wasFirst = false;

            }
            shiftedStringLocation = doAll(subStringShifted);

            if (minimalStringDoAllAnswer < Long.valueOf(minimalString) &&
                    isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(minimalStringDoAllAnswer))) {
                minimalString = String.valueOf(minimalStringDoAllAnswer);
            }

            // if (subStringLocation < Long.valueOf(minimalString)) {

//                if (Long.valueOf(subString) - 1 >= 0) {
//
//                    subStringMinusOne = String.valueOf(Long.valueOf(subString) - 1);
//
//                    subStringMinusOneDoAllAnswer = doAll(subStringMinusOne);
//
//                    if (isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(subStringMinusOneDoAllAnswer)) && Long.valueOf(subStringMinusOneDoAllAnswer) < Long.valueOf(minimalString)) {
//                        minimalString = subStringMinusOne;
//
//                    }
//
//                }

            if (isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(subStringLocation)) && subStringLocation < Long.valueOf(minimalString)) {
                minimalString = String.valueOf(subStringLocation);

            }
            //subString =String.valueOf(Long.valueOf(str.substring(index, str.length()))-1);

            //}

            // if (shiftedStringLocation < minimalStringDoAllAnswer) {

//                if (Long.valueOf(subStringShifted) - 1 >= 0) {
//                    subStringShiftedMinusOne = String.valueOf(Long.valueOf(subStringShifted) - 1);
//                    subStringShiftedMinusOneDoAllAnswer = doAll(subStringShiftedMinusOne);
//
//                    if (isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(subStringShiftedMinusOneDoAllAnswer)) &&
//                            subStringShiftedMinusOneDoAllAnswer < Long.valueOf(minimalString)) {
//                        minimalString = subStringShiftedMinusOne;
//
//                    }
//                }

            if (isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(shiftedStringLocation)) && shiftedStringLocation < Long.valueOf(minimalString)) {
                minimalString = String.valueOf(shiftedStringLocation);

            }

            if (isTheFirstStringIsSeriesOfTheSecondString(str, subStringShifted) && Long.valueOf(subStringShifted) < Long.valueOf(minimalString)) {
                minimalString = subStringShifted;

            }
            //subString =String.valueOf(Long.valueOf(str.substring(index, str.length()))-1);

            // }

            subString = str.substring(firstIndex, str.length() - secondIndex);
            secondIndex++;
            if (secondIndex >= str.length() / 2 + 1) {
                firstIndex++;
                secondIndex = 0;
            }
            // index++;
        }

//        if (minimalString.charAt(0)=='0'){
//            if ( doAll(minimalString)<Long.valueOf("1" +minimalString) &&isTheFirstStringIsSeriesOfTheSecondString(str,String.valueOf(doAll(minimalString)))){
//                minimalString= String.valueOf(doAll(minimalString));
//            }
//        }

        if (doAll(minimalString) < Long.valueOf(minimalString) && isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(doAll(minimalString)))) {
            minimalString = String.valueOf(doAll(minimalString));
        }

        String allSeries = minimalString;
        int indexOf = allSeries.indexOf(originalString);
        int counter = 1;

        while (indexOf == -1 && allSeries.length() < 3 * originalString.length()) {
            allSeries = allSeries + String.valueOf(Long.valueOf(minimalString) + counter);
            indexOf = allSeries.indexOf(originalString);
            counter++;


        }

        if (indexOf == -1) {
            return (doAll(originalString));
        }

        return (Long.valueOf(minimalString)); //+ indexOf + addDigit;//Math.min(betterNumOfDigitBefor(Long.valueOf(minimalString)), doAll(minimalString)) +index;
    }

    public static Long doAll(String str) {
        int[] answer = new int[2];
        String subString;
        String shiftedString;
        long minOfStr;
        long minOfShiftStr;
        long minOfAll = (Long.valueOf(str));
        long addSomeDigit = 0;
        int index = str.length() - 1;
        long counter;
        long minFromAddToLeft;
        long numberOfDigitAdded;
        long[] checkFromLeftShiftedAnswer = new long[2];
        long checkFromLeftShiftedMin;
        long checkFromLeftShiftedExtraDigit;
        long[] addFromLeftAnswer = new long[2];
        boolean didNumberWasCut = false;
        String allSeries;


        answer = isASeries(str);
        if (answer[0] == 1) {
            return (Long.valueOf(str.substring(0, answer[1]))) + addSomeDigit;
        }

        if (str.charAt(0) == '0') {
            while (index > 0 && str.charAt(index) == '0') {
                index--;
            }
            if (str.charAt(index) == '0') {
                return (Long.valueOf("1" + str)) + 1;
            }

            String firstPart = str.substring(index, str.length());
            String secondPart = str.substring(0, index);
            str = firstPart + secondPart;
            addSomeDigit = firstPart.length();
        }


        //shiftedString = shift(str);
        minOfStr = Math.min(checkFromLeft(str), checkFromRight(str));
        minOfShiftStr = minOfStr;
        //minOfShiftStr = Math.min(checkFromLeft(shiftedString), checkFromRight(shiftedString));
//        if (checkFromLeft(shiftedString) < minOfShiftStr && isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(checkFromLeft(shiftedString)))) {
//            minOfShiftStr = checkFromLeft(shiftedString);
//        }

//        if (checkFromRight(shiftedString) < minOfShiftStr && isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(checkFromRight(shiftedString)))) {
//            minOfShiftStr = checkFromRight(shiftedString);
//        }

        minOfAll = Math.min(minOfStr, minOfShiftStr);
        counter = 1;
        addFromLeftAnswer = addFromLeft(str, minOfAll);
        minFromAddToLeft = addFromLeftAnswer[0];
        numberOfDigitAdded = addFromLeftAnswer[1];
        if (minOfShiftStr < minOfStr && isTheFirstStringIsSeriesOfTheSecondString(str, String.valueOf(minFromAddToLeft))) {
            minOfAll = minFromAddToLeft;
        }
        minOfAll = Math.min(minOfAll, minFromAddToLeft);

        //checkFromLeftShiftedAnswer = checkFromLeftForShifted(shiftedString);
        //checkFromLeftShiftedMin = checkFromLeftShiftedAnswer[0];// need to change function

        //checkFromLeftShiftedMin = Math.min(checkFromLeftShiftedMin, addFromLeft(shiftedString, checkFromLeftShiftedMin)[0]);///
        //checkFromLeftShiftedExtraDigit = checkFromLeftShiftedAnswer[1];
        allSeries = String.valueOf(minOfAll);
        int indexOf = allSeries.indexOf(str);


//        while (indexOf == -1) {
//            allSeries = allSeries + String.valueOf(minOfAll + counter);
//            indexOf = allSeries.indexOf(str);
//            counter++;
//            if (allSeries.length() > 2 * str.length()) {
//                if (minOfStr < checkFromLeftShiftedMin) {
//                    if (indexOf != -1) {
//                        return Math.min(minOfStr, checkFromLeftShiftedMin);
//
//                    }
//                    return (Math.min(minOfStr, checkFromLeftShiftedMin));
//                }
//                return (checkFromLeftShiftedMin);
//
//            }
//        }
        return (minOfAll);


    }


}
