public class Main {

    public static void main(String[] args) {

        System.out.println("better is " + betterNumOfDigitBefor(Long.valueOf("10")));
        System.out.println("isThisStringIsASeriesOfNdigitNumber  " + isThisStringIsASeriesOfNdigitNumber("100101102",3));
        System.out.println(numOfDigit((long) 100));
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
        Long ans = (num - (long) Math.pow(10, (double) (numberOfDigit - 1))) * numberOfDigit + 1;

        numberOfDigit--;
        if(numOfDigit(num)>1){
            ans = ans - numOfDigit(num) + 2; // need to substruct
            ans = ans + 9;
        }
        //num = (long) Math.pow(10, (double) (numberOfDigit));
        if( numberOfDigit ==1 ){
            return ans;
        }
        while (numberOfDigit > 1) {
            ans = ans + ((long) Math.pow(10, (double) (numberOfDigit)) - (long) Math.pow(10, (double) (numberOfDigit - 1))) * numberOfDigit + 1;
            numberOfDigit--;
        }
        if(numOfDigit(num)>1){
            ans = ans - numOfDigit(num) + 2; // need to substruct
            ans = ans + 9;
        }

        return ans-1;
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



    public static int isThisStringIsASeriesOfNdigitNumber(String str, int n) {
        Long currentSizeOfSubString = (long) 1;
        Long ans = (long) 0;
        String currentSubString ;
        String nextSubString;
        Long numOfDigit = numOfDigit(Long.valueOf(str));
        Long numOfNumbers = numOfDigit/n;


        for (int i = 0; i <numOfNumbers-1 ; i ++){
            currentSubString = str.substring(i*n,(i+1)*n);
            nextSubString = str.substring((i+1)*n,(i+2)*n);
            boolean firstCondition = Long.valueOf(currentSubString)+1!=Long.valueOf(nextSubString);
            if( !firstCondition && ((i+2)*n == str.length()-1)){
                return -1;
            }
            if( !firstCondition && ((i+2)*n -1== str.length()-1)){
                return -1 ;
            }
            if ((i+2)*n+1>str.length()-1&&!firstCondition){
                return i*n;
            }
            if( firstCondition && ((i+2)*n -1== str.length()-1)){
                return i*n;
            }
            boolean secondCondition = Long.valueOf(currentSubString)+1!=Long.valueOf(str.substring((i+1)*n,(i+2)*n+1));
            if (!secondCondition){
                break;
            }
            if(firstCondition&&secondCondition){
                return i*n;
            }

        }

            return -1; // this string is serius of numbers !
    }


}
