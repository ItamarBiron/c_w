
import java.lang.*;
import java.util.Arrays;


public class kata {


    public static void main(String[] args) {
//
        System.out.println("start");
        //  System.out.println(" minusOne" +minusOne("23232328323423423423215435345345345343458098856756556809400840980980980980809092343243243243243098799634"));
//          for (int i =0 ; i <1000000; i++){
//             // minusOne("23232328323423423423215435345345345343458098856756556809400840980980980980809092343243243243243098799634");
//          addTwoStrings("1","23232328323423423423215435345345345343458098856756556809400840980980980980809092343243243243243098799634");
//          } // about 170 tests, 99 in first tests group at leats 55 in the other group


//        floorSqrt("12323309809809534545458098709854808654685688665486860956865654654654324238000980980980");
//        floorSqrt("23232328323215435345345345343458098856756556809400840980980980980809092343243243243243098799634");
        double startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            System.out.println(floorSqrt("12323309809809534545458098709854808654685688665486860956865654654654324238000980980980"));
            //betterMultiplyLessThenTen("12345",9);

        }
        double endTime = System.nanoTime();
        double resultTime = endTime - startTime;
        double resultTimeInSec = resultTime * (100000000);
        System.out.println(resultTimeInSec);

        System.out.println(betterMinusOne("2000000001000000"));
        System.out.println(betterMultiplyLessThenTen("999999999999999",2));
        // need to check why the function works only for even number ! :)
        // working for  12 chars

    }

    public static String returnNumbertimesNine(int n) {
        String answer = "";
        while (n > 0) {
            answer = answer + "9";
            n--;
        }
        return answer;
    }

    public static String fifteenCharsMultiply(String str, int num){
        return String.valueOf(Long.valueOf(str)*num);
    }

    public static String betterMultiplyLessThenTen(String a, int num) {
        int len = a.length();
        int numOfLongs = len/15;
        int lastLongLength = len % 15;
        String answer = "0";
        int power = 0;
        int toAdd =0 ;

        if(lastLongLength>0){
            toAdd =1;
        }

        if (len < 15) {
            return fifteenCharsMultiply(a,num);
        }
        String [] stringToLongs = new String[a.length()/15 +toAdd];
        int i ;
        for( i = 0; i <numOfLongs; i++){
            stringToLongs[i]=a.substring(15*(i) ,15*(i+1));
            power = power+stringToLongs[i].length();
            answer = addTwoStrings(answer, multiplayByPowerOfTen(String.valueOf(Long.valueOf(stringToLongs[i])*num),len-power));
        }

        if ( lastLongLength>0){
            stringToLongs[i] = a.substring(15*i,a.length());
            power = power+stringToLongs[i].length();
            answer = addTwoStrings(answer, multiplayByPowerOfTen(String.valueOf(Long.valueOf(stringToLongs[i])*num),len-power));

        }

        return answer;
    }

    public static String betterMinusOne(String a) {
        int len = a.length();
        String answer;
        String newDigit;
        int index = len - 1;

        if (len < 15) {
            return String.valueOf(Long.valueOf(a) - 1);
        }

        while (a.charAt(index) == '0') {
            index--;
        }
        newDigit = String.valueOf(Integer.valueOf(a.charAt(index) + "") - 1);
        answer = a.substring(0, index) + newDigit + returnNumbertimesNine(len - 1 - index);
        if (answer.charAt(0) == '0') {
            return answer.substring(1, answer.length());
        }
        return answer;

    }

    public static String minusOne(String a) {
        char[] answer = a.toCharArray();
        String count = "0";
        int len = a.length();
        int index = len - 1;

//        if ( len <15){
//            return String.valueOf(Long.valueOf(a)-1);
//        }

        int newVal = Character.getNumericValue(a.charAt(a.length() - 1)) - 1;
        if (len == 1 && newVal >= 0) {
            answer[a.length() - 1] = (char) (newVal + '0');
            return new String(answer);
        } else {
            while (answer[index] == '0') {
                index--;

            }
            newVal = Character.getNumericValue(a.charAt(index)) - 1;
            answer[index] = (char) (newVal + '0');
            index++;
            while (index < len) {
                answer[index] = '9';
                index++;
            }
            index = 0;
            while (answer[index] == '0') {
                index++;
            }
            return new String(Arrays.copyOfRange(answer, index, len));
        }

    }

    public static String floorSqrt(String a) {
        //Base Cases
        if (a.equals("0") || a.equals("1"))
            return a;

        // Do Binary Search for floor(sqrt(a))
        String start = multiplayByPowerOfTen("1", a.length() / 2 - 1); // need to consider to put "1" instead
        String end = multiplayByPowerOfTen("9", a.length() / 2); //// same here
        String ans = "0";
        String sum = "0";

        while (!isSmaller(end, start)) {
            sum = addTwoStrings(start, end);
            String mid = divideByTwo(sum);

            // If a is a perfect square
            if (bestMultiply(mid, mid).equals(a))
                return mid;


            if (isSmaller(bestMultiply(mid, mid), a)) {
                start = addTwoStrings(mid, "1");
                ans = mid;
            } else // If mid*mid is greater than a
                end = minusOne(mid);
        }
        return ans;
    }


    public static String addTwoStrings(String a, String b) {
        int theBiggerStringSize = Math.max(a.length(), b.length());
        int theSmallerStringSize = Math.min(a.length(), b.length());
        int sizeOfStringOfSum = theBiggerStringSize + 1;
        char[] sumInCharArray = new char[sizeOfStringOfSum];
        int addOne = 0; // if the sums of the 2 digit are grater then 9 we need to remmber add "1"
        String resultInString;
        int whoIsBigger;
        String GreaterString;
        char[] smallerStringWithZeros = new char[theBiggerStringSize];
        if (theBiggerStringSize == a.length()) {
            whoIsBigger = 0;
            GreaterString = a;
        } else {
            whoIsBigger = 1;
            GreaterString = b;
        }


        for (int i = theBiggerStringSize - 1; i >= 0; i--) {
            sumInCharArray[i] = '0';
            smallerStringWithZeros[i] = '0';
        }
        sumInCharArray[0] = '0';


        if (whoIsBigger == 0) {
            for (int i = theSmallerStringSize - 1; i >= 0; i--) {
                smallerStringWithZeros[theBiggerStringSize - 1 - (theSmallerStringSize - 1 - i)] = b.charAt(i);
            }
        } else {
            for (int i = theSmallerStringSize - 1; i >= 0; i--) {
                smallerStringWithZeros[theBiggerStringSize - 1 - (theSmallerStringSize - 1 - i)] = a.charAt(i);
            }
        }


        int index = 0;
        for (int i = theBiggerStringSize - 1; i >= 0; i--) {
            int digit = addOne + Character.getNumericValue(GreaterString.charAt(theBiggerStringSize - 1 - index))
                    + Character.getNumericValue(smallerStringWithZeros[theBiggerStringSize - 1 - index]);
            char c = (char) ((Character.getNumericValue(sumInCharArray[i]) + digit % 10) + '0');
            //System.out.println(c);

            sumInCharArray[theBiggerStringSize - index] = (char) ((Character.getNumericValue(sumInCharArray[i]) + digit % 10) + '0');
            if (digit > 9) {
                addOne = 1;
            } else {
                addOne = 0;
            }
            index++;
        }
        if (addOne == 1) {
            sumInCharArray[0] = '1';
        }

        resultInString = new String(sumInCharArray);
        if (sumInCharArray[0] == '0') {
            return resultInString.substring(1, resultInString.length());
        }
        return resultInString;
    }


    public static String multiplayTwoStrings(String a, String b) {
        String multiplication = "0";
        String index = "1";
        String counter = "0";
        while (!counter.equals(a)) {
            multiplication = addTwoStrings(multiplication, b);
            counter = addTwoStrings(counter, "1");
        }

        return multiplication;
    }

    public static String multiplayByTen(String a) {
        String answer = a + "0";

        return answer;
    }

    public static String multiplayByPowerOfTen(String a, int n) {
        String answer = a;
        for (int i = 0; i < n; i++) {
            answer = answer + "0";
        }

        return answer;
    }

    public static int firstIndexIsNotTheSame(String a, String b) {
        if (a.length() != b.length()) {
            System.out.println("a and b are not the same size , firstIndexNotTheSame");
            System.out.println("a is " + a);
            System.out.println("b is " + b);
            return b.length();
        }
        int len = a.length() - 1;
        for (int i = len; i >= 0; i--) {
            if (!(a.charAt(i) == b.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public static String multiplyLessThenTen(String a, int num) {
        String answer = "0";
        int index = 0;
        int len = a.length();
        int multiplyCurrentDigitByNum;
        String multiplayInString;
        int numOfTen = 0;
        int lessThenTen = 0;
        String SemiMultiply = "0";
        String multiplyCurrentDigitInPowerOfTen = "0";
        for (index = 0; index < len; index++) {
            multiplyCurrentDigitByNum = Character.getNumericValue(a.charAt(index)) * num;
            multiplayInString = String.valueOf(multiplyCurrentDigitByNum);
            if (index == len - 1) {
                multiplyCurrentDigitInPowerOfTen = multiplayTwoStrings(a.charAt(len - 1) + "", String.valueOf(num));
            } else {
                multiplyCurrentDigitInPowerOfTen = multiplayByPowerOfTen(multiplayInString, len - 1 - index);

            }
            answer = addTwoStrings(answer, multiplyCurrentDigitInPowerOfTen);
        }

        return answer;
    }

    public static String bestMultiply(String a, String b) {
        String answer = "0";
        int index = 0;
        int len = b.length();
        String semiMultiply = "0";
        String SemiWithPower = "0";
        for (int i = 0; i < len; i++) {
            semiMultiply = betterMultiplyLessThenTen(a, Character.getNumericValue(b.charAt(i)));
            SemiWithPower = multiplayByPowerOfTen(semiMultiply, len - 1 - i);
            answer = addTwoStrings(answer, SemiWithPower);
        }
        return answer;
    }

    public static boolean isSmaller(String a, String b) {
        char[] mayBeSamller = a.toCharArray();
        char[] mayBeGrater = b.toCharArray();
        if (a.length() < b.length()) {
            return true;
        }
        if (b.length() < a.length()) {
            return false;
        }
        int index = 0;
        while (index < b.length()) {
            if (Character.getNumericValue(mayBeSamller[index]) < Character.getNumericValue(mayBeGrater[index])) {
                return true;
            } else {
                if (Character.getNumericValue(mayBeSamller[index]) > Character.getNumericValue(mayBeGrater[index])) {
                    return false;
                }

            }
            index++;
        }
        return false;


    }

    public static String divideByTwo(String a) {
        if (a.length() == 1) {
            int number = Integer.parseInt(a);
            number = number / 2;
            return "" + number;
        }
        String multiplyByFive = multiplyLessThenTen(a, 5);
        String result = multiplyByFive.substring(0, multiplyByFive.length() - 1);
        if (result.equals(""))
            return "0";
        return result;
    }


}

