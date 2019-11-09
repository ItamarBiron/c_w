import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(lcs("anothertest", "notatest"));
    }

    static String lcs(String a, String b) {
        String ans = "";
        for (int i = 0; i < a.length(); i++) {
            if (b.indexOf(a.charAt(i)) >= 0 ) {
                ans = ans + a.charAt(i);
            }
        }
        char [] stringInChars = ans.toCharArray();
        Arrays.sort(stringInChars);
        String sortedString = new String (stringInChars);
        return sortedString;
    }
}

