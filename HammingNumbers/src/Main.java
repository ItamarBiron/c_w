import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Long> longList = new ArrayList<>();

    static int twoIndex =0 ;
    static int treeIndex =0;
    static int fiveIndex=0;
    public static void main(String[] args) {

        longList.add(Long.valueOf(1));

        System.out.println(getHumming(2));



    }

    public static long getHumming(int n){
        while(longList.size()<n){
            long min = Math.min(2*longList.get(twoIndex),3*longList.get(treeIndex));
            min = Math.min(min,5*longList.get(fiveIndex));
            longList.add(min);
            if(min ==2*longList.get(twoIndex)){
                twoIndex++;
            }
            if(min ==3*longList.get(treeIndex)){
                treeIndex++;
            }
            if(min ==5*longList.get(fiveIndex)){
                fiveIndex++;
            }
        }
        return longList.get(longList.size()-1);
    }

    public static int maxPowerOfBase(long number, int base) {
        int ans = 0;
        while (number % base == 0) {
            number = number / base;
            ans++;
        }
        return ans;
    }


    public static long bestRecurcive2and3(int n) {
        if (n <= 3) {
            return n;
        }
        long prev = bestRecurcive2and3(n - 1);
        int maxPowerOf2 = maxPowerOfBase(prev, 2);
        int maxPowerOf3 = maxPowerOfBase(prev, 3);
        long canBeNext = prev * 2;
        int index = 0;
        double minWithMultiplyBy3AndDivideBy2 = 2;
        double minWithMultiplyBy2AndDivideBy3 = 2;
        double division = 1;
        int counterFirst2 = 0;
        int counterFirst3 = 0;
        int counterSecond2 = 0;
        int counterSecond3 = 0;
        int howMuchDivideBy3 = 0;
        while (index < maxPowerOf2) {
            division = division / 2;
            index++;
            if (division < 1) {
                division = division * 3;
                howMuchDivideBy3++;
            }
            if (division < minWithMultiplyBy3AndDivideBy2) {
                minWithMultiplyBy3AndDivideBy2 = division;
                counterFirst2 = index;
                counterFirst3 = howMuchDivideBy3;
            }
        }


        int howMuchMultiplyBy2 = 0;
        index = 0;
        division = 1;
        while (index < maxPowerOf3) {
            division = division / 3;
            index++;
            while (division < 1) {
                division = division * 2;
                howMuchMultiplyBy2++;
            }
            if (division < minWithMultiplyBy2AndDivideBy3) {
                minWithMultiplyBy2AndDivideBy3 = division;
                counterSecond3 = index;
                counterSecond2 = howMuchMultiplyBy2;
            }
        }
        if (minWithMultiplyBy2AndDivideBy3 < minWithMultiplyBy3AndDivideBy2) {
            return prev / (long) Math.pow(3, counterSecond3) * (long) Math.pow(2, counterSecond2);

        }
        return prev / (long) Math.pow(2, counterFirst2) * (long) Math.pow(3, counterFirst3);

    }


}





