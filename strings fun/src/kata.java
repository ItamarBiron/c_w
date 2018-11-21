
import java.lang.*;
import java.util.Arrays;


public class kata {


    public static void main ( String [] args ){
//        System.out.println("check ");
//       System.out.println("is bestMultiply "+ bestMultiply ("570242795263255478002508926885006217715327585025","570242795263255478002508926885006217715327585025"));
        //System.out.println("is lesThen " + lesThen("570242795263255478002508926885006217715327585025"));
//        System.out.println("is lesThen " + lesThen("10000"));
//        System.out.println("is addTwoStrings " + addTwoStrings("570242795263255478002508926885006217715327585025","999999999999"));
        //System.out.println("is minus " + minus("9999999999999","999999999999"));
        System.out.println("divide by two " +divideByTwo("50"));
        System.out.println(" minusOne" +minusOne("5"));

        System.out.println("is floorSqrt " + floorSqrt("12323309809809534545458098709854808654685688665486860956865654654654324238000980980980"));
        // need to check why the function works only for even number ! :)
        // working for  12 chars

    }

    public static  String minusOne(String a){
        char [] answer = a.toCharArray();
        String count = "0";
        int len = a.length();
        int index =len-1 ;

        int newVal = Character.getNumericValue(a.charAt(a.length()-1)) - 1;
        if (len ==1 && newVal>=0) {
            answer[a.length()-1] = (char)(newVal + '0');
            return new String(answer);
        }
        else {
                while (answer[index]=='0')
                {
                    index --;

                }
            newVal = Character.getNumericValue(a.charAt(index)) - 1;
            answer[index] = (char) (newVal + '0');
            index ++;
            while (index <len) {
                answer[index] = '9';
                index ++;
            }
            index =0 ;
            while (answer [index ]=='0'){
                index ++;
            }
            return new String ( Arrays.copyOfRange(answer, index, len));
        }

    }

    public static String floorSqrt(String a)
    {
         //Base Cases
        if (a.equals("0") || a.equals("1"))
            return a;

        // Do Binary Search for floor(sqrt(a))
        String start ="1"; //multiplayByPowerOfTen("1", a.length()/2-1);
        String end = a;
        String ans="0";
        String sum ="0";
        while (!isSmaller(end , start))
        {
            sum = addTwoStrings(start , end);
            String mid = divideByTwo(sum);

            // If a is a perfect square
            if (bestMultiply(mid,mid).equals( a))
                return mid;


            if (isSmaller(bestMultiply(mid,mid), a))
            {
                start = addTwoStrings(mid ,"1");
                ans = mid;
            }
            else // If mid*mid is greater than a
                end = minusOne(mid);
        }
        return ans;
    }

    public static String check(String a ){
        String answer = "2";
        String count = "1";
        int index = 0;
        String subString =a.substring(a.length()-1-index,a.length()-1);

            while (isSmaller(answer,subString)|| index < a.length()-1){
                index ++;
                answer = bestMultiply(count,count);
                count = addTwoStrings(count , "1");
                subString =a.substring(a.length()-1-index,a.length()-1);

            }


        return count;
    }

    public static String integerSquareRoot(String n) {
        System.out.println(n);
        double value = Double.parseDouble(n);
        String total2 = String.valueOf((int)(Math.floor(Math.sqrt(value))));

        return total2;
    }



    public static String addTwoStrings( String a, String b){
        int theBiggerStringSize = Math.max(a.length(),b.length());
        int theSmallerStringSize = Math.min(a.length(),b.length());
        int sizeOfStringOfSum = theBiggerStringSize+1;
        char [] sumInCharArray = new char [sizeOfStringOfSum];
        int addOne=0; // if the sums of the 2 digit are grater then 9 we need to remmber add "1"
        String resultInString;
        int whoIsBigger;
        String GreaterString;
        char [] smallerStringWithZeros = new char [theBiggerStringSize];
        if (theBiggerStringSize == a.length()) {
            whoIsBigger=0;
            GreaterString =a;
        }
        else {
            whoIsBigger =1;
            GreaterString =b;
        }




            for (int i = theBiggerStringSize-1; i >=0 ; i--) {
                    sumInCharArray[i] = '0';
                    smallerStringWithZeros [i] = '0';
            }
            sumInCharArray[0]= '0';



        if ( whoIsBigger ==0){
        for (int i = theSmallerStringSize-1; i >=0 ; i--) {
            smallerStringWithZeros[theBiggerStringSize-1-(theSmallerStringSize-1-i)] = b.charAt(i);
        }
    }
        else{
        for (int i = theSmallerStringSize-1; i >=0 ; i--) {
            smallerStringWithZeros[theBiggerStringSize-1-(theSmallerStringSize-1-i)] = a.charAt(i);
        }
    }



    int index =0 ;
        for ( int i =theBiggerStringSize-1 ;i>=0; i-- ) {
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
        if (addOne ==1){
            sumInCharArray[0]= '1';
        }

        resultInString = new String (sumInCharArray);
        if (sumInCharArray[0]=='0'){
            return resultInString.substring(1,resultInString.length());
        }
        return resultInString;
}



    public static String multiplayTwoStrings ( String a, String b){
        String multiplication ="0";
        String index = "1";
        String counter = "0";
        while (!counter.equals(a)){
            multiplication = addTwoStrings(multiplication,b);
            counter = addTwoStrings(counter,"1");
        }

        return multiplication;
    }

    public static String multiplayByTen ( String a){
        String answer = a + "0";

        return answer;
    }

    public static String multiplayByPowerOfTen ( String a,int n){
        String answer = a;
        for (int i =0; i<n ; i++){
            answer = answer+"0";
        }

        return answer;
    }

    public static int firstIndexIsNotTheSame(String a, String b){
        if (a.length()!= b.length()){
            System.out.println("a and b are not the same size , firstIndexNotTheSame");
            System.out.println("a is " + a);
            System.out.println("b is " + b);
            return b.length();
        }
         int len = a.length()-1;
        for ( int i = len ; i>=0 ; i --  ){
            if (!(a.charAt(i) == b.charAt(i))){
                return i ;
            }
        }
        return -1;
    }

    public static String multiplyLessThenTen(String a,int num){
        String answer = "0";
        int index =0 ;
        int len = a.length();
        int multiplyCurrentDigitByNum;
        String multiplayInString;
        int numOfTen =0;
        int lessThenTen =0 ;
        String SemiMultiply="0";
        String multiplyCurrentDigitInPowerOfTen ="0";
        for ( index =0 ; index <len; index++){
            multiplyCurrentDigitByNum= Character.getNumericValue(a.charAt(index))*num;
            multiplayInString = String.valueOf(multiplyCurrentDigitByNum);
            if(index == len -1){
                multiplyCurrentDigitInPowerOfTen = multiplayTwoStrings(a.charAt(len-1)+"",String.valueOf(num));
            }
            else{
                multiplyCurrentDigitInPowerOfTen = multiplayByPowerOfTen(multiplayInString,len-1-index);

            }
            answer = addTwoStrings(answer,multiplyCurrentDigitInPowerOfTen);
        }

        return answer;
    }

    public static String bestMultiply ( String a, String b){
        String answer = "0";
        int index =0 ;
        int len = b.length();
        String semiMultiply ="0";
        String SemiWithPower="0";
        for ( int i =0 ; i<len; i++){
            semiMultiply= multiplyLessThenTen(a,Character.getNumericValue(b.charAt(i)));
            SemiWithPower = multiplayByPowerOfTen(semiMultiply,len-1-i);
            answer = addTwoStrings(answer,SemiWithPower);
        }
        return answer;
    }

    public static boolean isSmaller ( String a, String b){
        char [] mayBeSamller = a.toCharArray();
        char [] mayBeGrater = b.toCharArray();
        if ( a. length()< b.length()){
            return true;
        }
        if (b.length()<a.length()){
            return false;
        }
        int index =0 ;
        while (index < b.length()){
            if ( Character.getNumericValue(mayBeSamller[index])<Character.getNumericValue(mayBeGrater[index])){
                return true ;
            }
            else{
                if ( Character.getNumericValue(mayBeSamller[index])>Character.getNumericValue(mayBeGrater[index])){
                    return false ;
                }

            }
            index ++;
        }
        return false;


    }

    public static String divideByTwo(String a){
        if (a.length()==1){
            int number =Integer.parseInt(a);
            number = number /2 ;
            return ""+number;
        }
        String multiplyByFive = multiplyLessThenTen(a,5);
        String result = multiplyByFive.substring(0,multiplyByFive.length()-1);
        if (result.equals(""))
            return "0";
        return result;
    }

    public static String minus ( String a, String b){  // return the a-b a is the bigger one !
        String index="1";
        while (!a.equals(addTwoStrings(b,index))){
            index = addTwoStrings(index,"1");
        }
        return index;

    }


    public static String lesThen ( String a){
        String squreNum ="0";
        String greaterSquareNum =a;
        String index = "1";
        String counter = a.substring(0,a.length()/2);
        if ( a.length() %2 ==1){
            counter= "9"+a.substring(1,a.length()/2);
        }
        String counter2 = addTwoStrings(counter, "1");
        String minRange="0";
        String maxRange;
        String dividedString="0";
        if(a.length()>1){
             maxRange= "9"+a.substring(1,a.length()/2)+"0";
        }
        else{
            maxRange= "3";
        }

        String sum ="0";

        long i =0 ;
        while (true){
//            if ( i%100 == 0 ){
//                System.out.println(squreNum);
//            }
//            i++;
            squreNum = bestMultiply(counter,counter);
            greaterSquareNum= bestMultiply(addTwoStrings(counter,"1"),addTwoStrings(counter,"1"));
            //counter = addTwoStrings(counter,counter);
            if (isSmaller(squreNum,a)&&isSmaller(a,greaterSquareNum)){
                return counter;
            }
            if (!isSmaller(squreNum,a)&&!isSmaller(a,squreNum)){
                return counter;
            }
            if (!isSmaller(greaterSquareNum,a)&&!isSmaller(a,greaterSquareNum)){
                return addTwoStrings(counter,"1");
            }
            if(isSmaller(squreNum,a)){
                sum = addTwoStrings(maxRange,counter);
                dividedString = divideByTwo(sum);
                if (isSmaller(counter,dividedString)){
                    minRange= counter;
                    counter = dividedString;
                }
                else{
//                    int theFirstDifftentChar = firstIndexIsNotTheSame(a,squreNum);
//                    if ( theFirstDifftentChar==-1){
//                        return "";
//                    }
                   counter = addTwoStrings(counter , "10");
                    //counter = addTwoStrings(counter , multiplayByPowerOfTen("1",(theFirstDifftentChar-1)/2-1));
                   // counter = minus(a,squreNum);
                   // maxRange = counter;
                    //counter = divideByTwo(addTwoStrings(minRange,counter));
                }


            }
            else
                if(isSmaller(a,squreNum)){
                    maxRange = counter;
                    counter = divideByTwo(addTwoStrings(minRange,counter));
                }
        }

        //return counter;
    }
}

