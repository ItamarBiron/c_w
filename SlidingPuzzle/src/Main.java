import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[][] a = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//                if (i * j == 4) {
//                    a[i][j] = 0;
//
//                } else {

                a[i][j] = j + 1 + i * 3;

                // }
            }
        }

        a[0][0] = 4;
        a[0][1] = 5;
        a[0][2] = 3;
        a[1][0] = 7;
        a[1][1] = 0;
        a[1][2] = 6;
        a[2][0] = 1;
        a[2][1] = 8;
        a[2][2] = 2;

        System.out.println(howManyDisOrderOverAll(a)+" indexOfRowThatContainzero "+indexOfRowThatContainzero(a));
        betterSolve(a);
    }


    public static List<Integer> betterSolve(int[][] a) {
        // your code here!
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < a[0].length * a[0].length; i++) {
            ans.add(i + 1);
        }
//        ans.add(0);
        System.out.println(ans);

        if ((howManyDisOrderOverAll(a)+indexOfRowThatContainzero(a)) % 2 == a[0].length%2) {

            return ans;
        }

        return null;
    }

    public static int howManyDisOrderCaseThisElement(int[][] array, int elementVal, int elementXPostion, int elementYPostion) {
        int ans = 0;

        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array[1].length; j++) {
                if (j + 1 + i * 3 < elementYPostion + 1 + elementXPostion * 3 && array[i][j] > elementVal &&
                elementVal!=0 && array[i][j]!=0) {
                    ans++;
                }

            }
        }

        return ans;
    }

    public static int howManyDisOrderOverAll(int[][] array) {
        int ans = 0;
        int addition;

        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array[1].length; j++) {
                addition = howManyDisOrderCaseThisElement(array, array[i][j], i, j);
                ans = ans +addition ;


            }
        }
        return ans;
    }

    public static int indexOfRowThatContainzero (int [][]array){
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array[1].length; j++) {
                    if(array[i][j]==0){
                        return i+1;
                    }



            }
        }
        return 0;

    }


}


