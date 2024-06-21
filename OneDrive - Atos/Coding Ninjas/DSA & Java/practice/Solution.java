
public class Solution {

    public static void main(String[] args) {
        // int[][] ip = { { 6, 9, 8, 5 }, { 9, 2, 4, 1 }, { 8, 3, 9, 3 }, { 8, 7,
        // 8, 6 }

        // };

        // };
        // int[][] ip = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        /*
         * int[][] ip = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 },
         * { 16, 17, 18, 19, 20 },
         * { 21, 22, 23, 24, 25 } };
         */
        // int[][] ip = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
        // int[][] ip = { { 0 }, { 0 } };
        int[] input = { 1, 5, 10, 15, 20, 25 };
        int[] input2 = { 2, 4, 5, 9, 15 };

        // System.out.println("Input Array");

        // print2DArray(ip);
        System.out.println("Output");
        System.out.println(maximumSumPath(input, input2));

        // int[] op = printRowWise(ip);
        // for (int i = 0; i < op.length; i++) {
        // System.out.print(op[i] + " ");
        // }
    }

    public static int maximumSumPath(int[] arr1, int[] arr2) {
        int currIdx = 0;
        int[] currArr = arr2;
        int[] otherArr = arr1;
        int curArrLen = currArr.length;
        int otherArrLen = otherArr.length;
        int sum = 0;

        for (int i = currIdx; i < curArrLen; i++) {
            for (int j = currIdx; j < otherArrLen; j++) {
                if (currArr[i] == otherArr[j]) {
                    int[] tmpArr = currArr;
                    currArr = otherArr;
                    otherArr = tmpArr;
                    curArrLen = currArr.length;
                    otherArrLen = otherArr.length;
                    currIdx = i;
                }

            }
            sum += currArr[i];
        }
        // System.out.println("Sum: " + sum);
        return sum;
    }

    public static void print2DArray(int matrix[][]) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;
        for (int i = 0; i < len1D; i++) {
            for (int j = 0; j < len2D; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static String sqrMat(int[][] mat, int n, int m, int diag[]) {
        int len1D = mat.length;
        int len2D = mat[0].length;
        if (m != n) {
            return "NO";
        } else {
            for (int i = 0; i < len1D; i++) {
                diag[i] = mat[i][len2D - (len2D - i)];
            }
            for (int[] x : mat) {
                for (int y : x) {
                    System.out.print(y + " ");
                }
                System.out.println();

            }
            return "YES";
        }

    }

    public static String findLargest(int[][] mat) {
        int len1D = mat.length;
        if (len1D == 0) {
            return "row 0 -2147483648";
        }
        int len2D = mat[0].length;
        // if (len2D == 0) {
        // return "row 0 -2147483648";
        // }
        int largestCol = 0;
        int largestColIdx = 0;
        String entity = "";
        int sum = 0;
        for (int i = 0; i < len1D; i++) {
            sum = 0;
            for (int j = 0; j < len2D; j++) {
                sum += mat[i][j];
            }
            if (sum > largestCol) {
                largestCol = sum;
                largestColIdx = i;
                entity = "row";
            }
        }

        for (int i = 0; i < len2D; i++) {
            sum = 0;
            for (int j = 0; j < len1D; j++) {
                sum += mat[j][i];
            }
            if (sum > largestCol) {
                largestCol = sum;
                largestColIdx = i;
                entity = "column";
            }
        }

        return entity + " " + largestColIdx + " " + largestCol;

    }

    public static void setZeros(int matrix[][]) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;
        boolean[] zeroMapi = new boolean[len1D];
        boolean[] zeroMapj = new boolean[len2D];
        System.out.println("Array Before");
        print2DArray(matrix);
        for (int i = 0; i < len1D; i++) {
            for (int j = 0; j < len2D; j++) {
                if (matrix[i][j] == 0) {
                    zeroMapi[i] = true;
                    zeroMapj[j] = true;

                }
            }
        }
        for (int i = 0; i < zeroMapi.length; i++) {
            if (zeroMapi[i] == true) {
                for (int j = 0; j < len2D; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < zeroMapj.length; i++) {
            if (zeroMapj[i] == true) {
                for (int j = 0; j < len1D; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        System.out.println("Array After");
        print2DArray(matrix);
    }

    public static int[] printRowWise(int[][] a) {
        int len1D = a.length;
        int len2D = a[0].length;
        int[] op = new int[len1D * len2D];
        int count = 0;
        for (int i = 0; i < len1D; i++) {
            for (int j = 0; j < len2D; j++) {
                op[count] = a[i][j];
                count++;
            }
        }
        return op;
    }

    public static boolean isPermutation(String str1, String str2) {
        // Check if lengths are the same
        if (str1.length() != str2.length()) {
            return false;
        }

        // Create frequency arrays for both strings
        int[] freq1 = new int[256]; // Assuming ASCII character set
        int[] freq2 = new int[256];

        // Populate frequency arrays
        for (int i = 0; i < str1.length(); i++) {
            freq1[str1.charAt(i)]++;
            freq2[str2.charAt(i)]++;
        }

        // Compare frequency arrays
        for (int i = 0; i < 256; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void traverseSpiral(int[][] matrix) {
        int len1D = matrix.length - 1;
        int len2D = matrix[0].length - 1;
        int top = 0;
        int bottom = len1D;
        int left = 0;
        int right = len2D;

        while (top <= bottom && left <= right) {
            for (int i = top; i <= right; i++) {
                System.out.print(matrix[top][i] + " ");
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    System.out.print(matrix[bottom][i] + " ");
                }
            }
            bottom--;

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
            }
            left++;
        }
    }

    public static int[][] transpose(int m, int n, int[][] mat) {
        int[][] transpose = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = mat[i][j];
            }
        }
        return transpose;

    }

    public static void wavePrint(int mat[][]) {
        // Down
        // Right
        // Up
        // Left
        int len1D = mat.length;
        if (len1D == 0) {
            return;
        }
        int len2D = mat[0].length;
        // Down
        int col = 0;
        int row;
        // Down
        while (col < len2D) {
            // Down
            row = 0;
            for (int i = 0; i < len1D; i++) {
                System.out.print(mat[i][col] + " ");
            }

            col++;
            if (col >= len2D - 1) {
                break;
            }
            row = len1D - 1;
            for (int i = row; i >= 0; i--) {
                System.out.print(mat[i][col] + " ");
            }

            col++;

        }
    }

    public static void totalSum(int mat[][]) {
        int N = mat.length;
        if (N == 0) {
            System.out.println(0);
            return;
        }

        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // Check for boundary elements
                if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
                    sum += mat[i][j];
                }
                // Check for main diagonal
                else if (i == j) {
                    sum += mat[i][j];
                }
                // Check for anti-diagonal
                else if (i + j == N - 1) {
                    sum += mat[i][j];
                }
            }
        }

        System.out.println(sum);
    }

    public static void leaders(int[] input) {
        int len = input.length;
        boolean found;
        for (int i = len - 1; i >= 0; i += 2) {
            found = false;
            for (int j = i; j < len; j++) {
                if (input[i] < input[j]) {
                    found = false;
                    break;
                } else {
                    found = true;
                }
            }
            if (found) {
                System.out.print(input[i] + " ");
            }
        }
    }

    public static void sort012(int[] arr) {
        int len = arr.length;
        int sum = 0;
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < len - 1; i += 2) {
            sum = arr[i] + arr[i + 1];
            if (sum == 0) {
                zeros += 2;
            }
            if (sum == 1) {
                zeros++;
                ones++;
            }
            if (sum == 2) {
                if (arr[i] == 2 || arr[i + 1] == 2) {
                    zeros++;
                    twos++;
                } else {
                    ones += 2;
                }
            }
            if (sum == 3) {
                ones++;
                twos++;
            }

        }
        if (len % 2 != 0) {
            if (arr[len - 1] == 0) {
                zeros++;
            }
            if (arr[len - 1] == 1) {
                ones++;
            }
            if (arr[len - 1] == 2) {
                twos++;
            }
        }

        int nextCount = 0;
        for (int i = 0; i < zeros; i++) {
            arr[nextCount] = 0;
            nextCount++;
        }

        for (int i = 0; i < ones; i++) {
            arr[nextCount] = 1;
            nextCount++;
        }

        for (int i = 0; i < twos; i++) {
            arr[nextCount] = 2;
            nextCount++;
        }

        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}