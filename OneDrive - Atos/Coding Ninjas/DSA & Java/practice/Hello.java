
public class Hello {
    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "adbc";

        // int[][] freq = new int[str1.length()][2];
        if (str1.length() != str2.length()) {
            // return false
        } else {
            int[][] str1Freq = new int[str1.length()][2];
            int[][] str2Freq = new int[str2.length()][2];
            str1Freq = getFreq(str1Freq, str1);
            str2Freq = getFreq(str2Freq, str2);
            boolean matchP = false;
            boolean match = false;
            for (int i = 0; i < str1Freq.length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (str1Freq[i][1] == str2Freq[j][1]) {
                        if (str1Freq[i][0] == str2Freq[j][0]) {
                            match = true;
                        } else {
                            match = false;
                        }
                    }
                    if (!match) {
                        break;
                    }
                }
                if (match) {
                    matchP = true;
                } else {
                    matchP = false;
                }
            }
            if (matchP) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }

        // for (int i = 0; i < freq.length; i++) {
        // for (int j = 0; j < 2; j++) {
        // System.out.print(freq[i][j]);
        // }
        // System.out.println();
        // }
    }

    public static int[][] getFreq(int[][] freq, String str) {
        int count = 0;
        int len = str.length() - 1;
        for (int i = 0; i <= len; i++) {
            count = 0;
            for (int j = 0; j <= len; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    count++;
                }
            }
            freq[i][0] = count;
            freq[i][1] = str.charAt(i);
        }
        return freq;
    }
}