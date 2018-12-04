import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("better is " + betterNumOfDigitBefor(Long.valueOf("123456789")));
        //System.out.println("numOfDigitBefor is " + numOfDigitBefor(Long.valueOf("99")));
        //System.out.println("isThisStringIsASeriesOfNdigitNumber  " + isThisStringIsASeriesOfNdigitNumber("9102", 4));
        System.out.println("isASeries  " + Arrays.toString(isASeries("101102")));
        System.out.println(reverseString("023023023023023023023023023023023023023023023023023023023"));
        System.out.println(getAnswer("1234567891"));
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

        while (counter <= len/2) {
            if (isThisStringIsASeriesOfNdigitNumber(str, counter)) {
                answer[0] = 1;
                answer[1] = counter;

            }

            counter++;

        }
        return answer;
    }

    public static long contain ( String str ){
        int [] answer = new int [2];
        answer = isASeries(str);

        return 1;

    }

    public static String reverseString(String str) {
        int len = str.length();
        char[] answer = new char[len];

        for (int i = 0; i < len; i++) {
            answer [ i] = str.charAt(len-i-1);
        }

        return String.valueOf(answer);

    }

    public static long getAnswer(String str) {
        int[] indexes = new int[2];
        int counter = 1;
        long len = numOfDigit(Long.valueOf(str));
        long indexBefor = 1;
        long indexAfter = 1;
        long min = Long.valueOf("1" + str);
        String beforStr = "1";
        String afterStr = "1";
        String otherbeforStr = "";
        indexes[0] = -1;
        indexes[1] = -1;
        boolean beforWasMin = false;
        boolean afterWasMin = false;

//        if ( len >3){
//            if (str.charAt(0)==str.charAt((int)len-1)){
//                return betterNumOfDigitBefor(Long.valueOf(str.substring(0,(int)len-1)))+1;
//            }
//        }

        if ((isASeries(str))[0] == 1) {
            if (Long.valueOf(str.substring(0, (isASeries(str))[1])) < min) {
                min = Long.valueOf(str.substring(0, (isASeries(str))[1]));
                return betterNumOfDigitBefor(min);
            }
        }

        while (beforStr.length() < str.length()) {
            beforStr = String.valueOf(indexBefor);
            indexBefor++;
            if ((isASeries(beforStr + str))[0] == 1) {
                if (Long.valueOf((beforStr + str).substring(0, (isASeries(beforStr + str))[1])) < min) {
                    min = Long.valueOf((beforStr + str).substring(0, (isASeries(beforStr + str))[1]));
                    otherbeforStr = beforStr;
                    beforWasMin = true;
                    break;
                }

            }

        }
        indexBefor = 1;
        if (!(beforStr.equals("1") && beforWasMin)) {
            while (afterStr.length() < str.length()) {
                afterStr = reverseString(String.valueOf(indexBefor));
                indexBefor++;
                if ((isASeries(str + afterStr))[0] == 1) {
                    if (Long.valueOf((str + afterStr).substring(0, (isASeries(str + afterStr))[1])) < min) {
                        min = Long.valueOf((str + afterStr).substring(0, (isASeries(str + afterStr))[1]));
                        afterWasMin = true;
                        break;
                    }

                }

            }
        }


        indexBefor = 1;
        indexAfter = 1;
        afterStr = "0";
        beforStr = "1";
        while (beforStr.length() < str.length()) {
            beforStr = String.valueOf(indexBefor);
            indexBefor++;
            afterStr = "0";
            indexAfter = 1;
            while (afterStr.length() < str.length()) {
                afterStr = reverseString(String.valueOf(indexBefor));
                indexBefor++;
                if ((isASeries(beforStr + str + afterStr))[0] == 1) {
                    if (Long.valueOf((beforStr + str + afterStr).substring(0, (isASeries(beforStr + str + afterStr))[1])) < min) {
                        int[] lenOfEachNumInSeries = isASeries(beforStr + str + afterStr);
                        min = Long.valueOf((beforStr + str + afterStr).substring(0, lenOfEachNumInSeries[1]));
                        return betterNumOfDigitBefor(Long.valueOf((beforStr + str + afterStr).substring(0, lenOfEachNumInSeries[1]))) + beforStr.length();
                    }

                }
            }

        }



        if (afterWasMin) {
            return betterNumOfDigitBefor(min);
        }
        if ( beforWasMin){
            return betterNumOfDigitBefor(min) + otherbeforStr.length(); // need to check when to put minus 1 and when to add 1 !!!

        }
        if( String.valueOf(min).length()- str.length() ==1){
            return betterNumOfDigitBefor(min) +1;
        }
        return betterNumOfDigitBefor(min) +beforStr.length(); // need to check when to put minus 1 and when to add 1 !!!
    }


}
