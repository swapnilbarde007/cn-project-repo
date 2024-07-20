
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int[] input2 = { 1, 2, 3, 4, 5, 5, 6 };
        // System.out.println("Input Array");
        // print1DArray(sortArray(input1));
        // System.out.println();
        // print1DArray(input2);
        // System.out.println();
        // System.out.println("Output Array");
        // equilibrium(input2);
        // print1DArray(rotate(input2, 4));
        // int[] x = allIndexes(new int[] { 34, 57, 82, 41, 65, 35, 82, 27, 36, 12, 6,
        // 40, 66, 99, 25, 29, 22, 25, 12, 24,
        // 65, 15, 5, 43, 28, 33, 76, 32, 13, 95, 22, 84, 71, 23, 28, 7, 65, 94, 18, 47,
        // 9, 42, 61, 73 }, 61);
        // print1DArray(x);

        // int x[] = Arrays.copyOfRange(input2, 0, input2.length - 1);

        // System.out.println(pairSum(input2, 6));

        // print1DArray(intersection(input1, input2));

        // System.out.println(removeConsecutiveDuplicates("aabcccba"));
        // print1DArray(quickSort(new int[] { 1, 8, 3, 9, 4, 5, 7 }, 0, 6));
        // int[] arr = new int[] { 1, 8, 3, 9, 4, 5, 7 };
        // System.out.println("Before: ");
        // print1DArray(arr);
        // System.out.println();
        // System.out.println("After: ");
        // quickSort(arr, 0, 6);
        // print1DArray(arr);
        // towerOfHanoi(3, 'A', 'B', 'C');

        System.out.println(staircase(5));

    }

    public static int staircase(int n) {
        if (n == 0) {
            return 1;

        }
        if (n < 0) {
            return 0;
        }
        return staircase(n - 1) + staircase(n - 2) + staircase(n - 3);
    }

    public static String addStars(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String smallOp = addStars(s.substring(1));

        if (s.charAt(0) == s.charAt(1)) {
            return s.charAt(0) + "*" + smallOp;
        } else {
            return s.charAt(0) + "" + smallOp;
        }
    }

    public static int convertStringToInt(String input) {
        if (input.length() == 1) {
            int smallOp1 = input.charAt(0) - 48;
            return smallOp1;
        }
        int smallOp2 = convertStringToInt(input.substring(0, input.length() - 1)) * 10;
        int smallOp3 = input.charAt(input.length() - 1) - 48;
        return (smallOp2 + smallOp3);
    }

    public static boolean isPalindrome(String str) {
        if (str.length() == 0) {
            return true;
        }
        if (str.length() == 1) {
            return true;
        }
        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            String newStr = str.substring(1, str.length() - 1);
            boolean smallInput = isPalindrome(newStr);
            return smallInput;
        } else {
            return false;
        }
    }

    public static void permutationOfString(String input) {
        permuteStringHelper("", input);
    }

    private static void permuteStringHelper(String prefix, String input) {
        int n = input.length();
        if (n == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permuteStringHelper(prefix + input.charAt(i), input.substring(0, i) + input.substring(i + 1, n));
            }
        }
    }

    public static double findGeometricSum(int k) {
        if (k == 0) {
            return 1;
        }
        double pow = power(2, k);
        return (double) ((1 / pow) + findGeometricSum(k - 1));
    }

    public static double power(int b, int p) {
        int pow = 1;
        while (p > 0) {
            pow *= b;
            p--;
        }
        return pow;

    }

    public static int binarySearch(int input[], int element) {
        if (input.length == 0) {
            return -1;
        }
        return bSearchHelper(input, 0, input.length, element);

    }

    public static int bSearchHelper(int[] input, int startIdx, int endIdx, int element) {
        int mid = (startIdx + endIdx) / 2;
        if (startIdx > endIdx) {
            return -1;
        }
        if (input[mid] == element) {
            return mid;
        }
        if (input[mid] > element) {
            return bSearchHelper(input, startIdx, mid - 1, element);
        } else {
            return bSearchHelper(input, mid + 1, endIdx, element);
        }
    }

    private static final String[] KEYPAD_MAP = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    public static String[] keypad(int n) {
        if (n <= 0) {
            return new String[0];
        }

        String numberStr = Integer.toString(n);
        List<String> result = new ArrayList<>();
        generateCombinations(numberStr, 0, new StringBuilder(), result);
        return result.toArray(new String[0]);
    }

    // Helper method to generate combinations recursively
    private static void generateCombinations(String numberStr, int index, StringBuilder current, List<String> result) {
        if (index == numberStr.length()) {
            result.add(current.toString());
            return;
        }

        int digit = numberStr.charAt(index) - '0';
        String letters = KEYPAD_MAP[digit];

        if (letters.isEmpty()) {
            generateCombinations(numberStr, index + 1, current, result);
        } else {
            for (char letter : letters.toCharArray()) {
                current.append(letter);
                generateCombinations(numberStr, index + 1, current, result);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }

    public static void towerOfHanoi(int disks, char source, char auxiliary, char destination) {
        if (disks == 1) {
            System.out.println(source + " -> " + destination);
            return;
        }

        towerOfHanoi(disks - 1, source, destination, auxiliary);
        System.out.println(source + " -> " + destination);
        towerOfHanoi(disks - 1, auxiliary, source, destination);
    }

    public static void towerOfHanoi1(int n, int start, int end) {
        if (n == 1) {
            printMove(start, end);
            return;
        }

        int other = 6 - (start + end);
        towerOfHanoi1(n - 1, start, other);
        printMove(start, end);
        towerOfHanoi1(n - 1, other, end);
    }

    private static void printMove(int start, int end) {

        if (start == 1 && end == 2) {
            System.out.println("A -> B");
        }
        if (start == 1 && end == 3) {
            System.out.println("A -> C");
        }
        if (start == 2 && end == 3) {
            System.out.println("B -> C");
        }
        if (start == 2 && end == 1) {
            System.out.println("B -> A");
        }
        if (start == 3 && end == 1) {
            System.out.println("C -> A");
        }
        if (start == 3 && end == 2) {
            System.out.println("C -> B");
        }
    }

    public static void quickSort(int[] input, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int pivot = input[rightIndex];

        int leftPointer = leftIndex;
        int rightPointer = rightIndex;

        while (leftPointer < rightPointer) {

            while (input[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (input[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(input, leftPointer, rightPointer);
        }
        // Out of while loop means leftPointer and rightPointer meet
        swap(input, leftPointer, rightIndex);

        quickSort(input, leftIndex, leftPointer - 1);
        quickSort(input, leftPointer + 1, rightIndex);
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    public static void mergeSort(int[] arr) {
        // Create Two Array
        if (arr.length <= 1) {
            return;
        }
        int mid = arr.length / 2;
        int[] arrA = new int[mid];
        int[] arrB = new int[arr.length - mid];
        for (int i = 0; i < mid; i++) {
            arrA[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            arrB[i - mid] = arr[i];
        }
        mergeSort(arrA);
        mergeSort(arrB);

        mergeArr(arrA, arrB, arr);
        return;
    }

    public static int[] mergeArr(int[] a, int[] b, int c[]) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k] = a[i];
                i++;
                k++;
            } else {
                c[k] = b[j];
                j++;
                k++;
            }
        }
        if (i < a.length) {
            while (i < a.length) {
                c[k] = a[i];
                i++;
                k++;
            }
        }
        if (j < b.length) {
            while (j < b.length) {
                c[k] = b[j];
                j++;
                k++;
            }
        }

        return c;

    }

    public static String removeConsecutiveDuplicates(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String smOp = removeConsecutiveDuplicates(s.substring(1));
        if (s.charAt(0) == smOp.charAt(0)) {
            return smOp;
        } else {
            return s.charAt(0) + smOp;
        }
    }

    public static int binarySearchRec1(int[] arr, int startIdx, int endIdx, int element) {
        if (startIdx > endIdx) {
            return -1;
        }
        int mid = (startIdx + endIdx) / 2;
        if (element == arr[mid]) {
            return mid;
        }
        if (element > arr[mid]) {
            return binarySearchRec1(arr, mid + 1, endIdx, element);
        } else {
            return binarySearchRec1(arr, startIdx, mid - 1, element);
        }

    }

    public static String replacePi(String input) {
        if (input.length() <= 1) {
            return input;
        }
        if (input.charAt(0) == 'p' && input.charAt(1) == 'i') {
            String smallOp = replacePi(input.substring(2));
            return "3.14" + smallOp;
        } else {
            String smallOp = replacePi(input.substring(1));
            return input.charAt(0) + smallOp;
        }

    }

    public static String removeX(String input) {
        if (input.length() == 0) {
            return input;
        }
        String smallOp = removeX(input.substring(0, input.length() - 1));
        if (input.charAt(input.length() - 1) != 'x') {
            return smallOp + String.valueOf(input.charAt(input.length() - 1));
        }
        return smallOp;
    }

    public static String replaceCharacter(String input, char c1, char c2) {
        if (input.length() == 0) {
            return input;
        }
        char[] inputArr = input.toCharArray();
        if (inputArr[input.length() - 1] == c1) {
            inputArr[input.length() - 1] = c2;
        }
        String smOp = replaceCharacter(new String(Arrays.copyOfRange(inputArr, 0, inputArr.length - 1)), c1, c2);
        return smOp + inputArr[input.length() - 1];

    }

    public static int[] allIndexes(int input[], int x) {
        int[] foundInter = new int[input.length];
        int smOp = allIndexesHelper(Arrays.copyOfRange(input, 0, input.length), x);
        System.out.println("IDX: " + smOp);
        foundInter[0] = smOp;

        int counter = 1;

        while (smOp >= 0 && smOp < input.length - 1) {
            int tmp = smOp;
            smOp = allIndexesHelper(Arrays.copyOfRange(input, smOp + 1, input.length), x);
            if (smOp == -1) {
                break;
            }
            smOp += tmp + 1;
            System.out.println("IDX: " + smOp);
            if (smOp != -1) {
                foundInter[counter] = smOp;
                counter++;
            }

        }
        int[] foundOp = new int[counter];
        foundOp = Arrays.copyOfRange(foundInter, 0, counter);
        return foundOp;

    }

    public static int allIndexesHelper(int input[], int x) {
        if (input.length == 0) {
            return -1;
        }
        if (input[0] == x) {
            return 0;
        }
        int smallOp = allIndexesHelper(Arrays.copyOfRange(input, 1, input.length), x);
        if (smallOp == -1) {
            return -1;
        } else {
            return smallOp + 1;
        }
    }

    public static int countZerosRec(int input) {
        if (input == 0) {
            return 0;
        }

        if (input % 10 == 0) {
            return 1 + countZerosRec(input / 10);
        } else {
            return countZerosRec(input / 10);
        }
    }

    public static double findGeometricSum1(int k) {
        if (k == 0) {
            return 1;
        } else {
            double powOp = 1 / pow(2, k);
            double smallOp = powOp + findGeometricSum1(k - 1);
            return smallOp;
        }

    }

    public static double pow(int n, int m) {
        int count = 0;
        double pow = 1;
        while (count < m) {
            pow = pow * n;
            count++;
        }
        return pow;
    }

    public static boolean checkPalindrome1(String inputString) {
        if (inputString.length() == 0 || inputString.length() == 1) {
            return true;
        }
        if (inputString.charAt(0) != inputString.charAt(inputString.length() - 1)) {
            return false;
        }
        return checkPalindrome1(inputString.substring(1, inputString.length() - 1));
    }

    public static int lastIndex(int input[], int x) {
        if (input.length == 0) {
            return -1;
        }
        int smallOp = lastIndex(Arrays.copyOfRange(input, 1, input.length), x);
        if (smallOp == -1) {
            if (input[0] == x) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return smallOp + 1;
        }
    }

    public static int firstIndex(int input[], int x) {
        if (input.length == 0) {
            return -1;
        }

        if (input[0] == x) {
            return 0;
        }
        int smallOutput = firstIndex(Arrays.copyOfRange(input, 1, input.length), x);
        if (smallOutput == -1) {
            return -1;
        } else {
            return smallOutput + 1;
        }

    }

    public static boolean checkArraySorted(int startIdx, int[] arr) {
        if (arr[startIdx - 1] > arr[startIdx]) {
            return false;
        }
        if (startIdx == arr.length - 1) {
            return true;
        }

        return checkArraySorted(startIdx + 1, arr);

    }

    public static boolean checkNumber(int input[], int x) {
        if (input.length == 0) {
            return false;
        }
        if (input[input.length - 1] == x) {
            return true;
        }
        return checkNumber(Arrays.copyOfRange(input, 0, input.length - 1), x);
    }

    public static int countDigits(int n) {
        if (n == 0) {
            return 0;
        }
        return countDigits(n / 10) + 1;
    }

    public static int factRec(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factRec(n - 1);
    }

    public static int sum(int input[]) {
        if (input.length == 1) {
            return input[0];
        }
        return sum(Arrays.copyOfRange(input, 1, input.length)) + input[0];
    }

    public static int[] rotate(int[] arr, int d) {

        int len = arr.length - 1;

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < len; j++) {
                int tmp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = tmp;
            }
        }

        return arr;
    }

    public static int pairSum(int[] arr, int num) {
        arr = insertionSort(arr);
        int len = arr.length - 1;
        if (arr.length == 0) {
            return 0;
        }
        int prev = arr[0];
        int pairs = 0;
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= len && i != j; j++) {
                if (arr[i] + arr[j] == num) {
                    pairs++;
                    prev = arr[j];
                }

            }

        }
        return pairs;
    }

    public static int findDuplicate(int[] arr) {
        arr = selectionSort(arr);
        int tmp = arr[0];
        int len = arr.length - 1;
        for (int i = 1; i <= len; i++) {
            if (tmp == arr[i]) {
                return arr[i];
            } else {
                tmp = arr[i];
            }
        }
        return -1;
    }

    public static int findUnique(int[] arr) {
        arr = sort2(arr);

        for (int i = 0; i <= arr.length - 1; i += 2) {
            if (i == arr.length - 1) {
                return arr[i];
            }
            if (arr[i] != arr[i + 1]) {
                return arr[i];
            }
        }
        return -1;
    }

    public static int[] sort2(int[] arr) {

        int len = arr.length;
        int counter = 0;
        while (counter <= len - 1) {
            int smallest = arr[counter];
            int smallestIdx = counter;
            for (int i = counter + 1; i < len; i++) {
                if (arr[i] < smallest) {
                    smallest = arr[i];
                    smallestIdx = i;
                }
            }
            int tmp = arr[smallestIdx];
            arr[smallestIdx] = arr[counter];
            arr[counter] = tmp;
            counter++;
        }
        return arr;
    }

    public static int equilibrium(int[] arr) {
        int len = arr.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += arr[i];
        }
        int left = 0;
        int right = sum - arr[0];
        // System.out.println("Sum: " + sum + "| Left : " + left + "| Right: " + right);
        for (int i = 1; i < len; i++) {
            left = left + arr[i - 1];
            right = sum - left - arr[i];
            // System.out.println("Sum: " + sum + "| Left : " + left + "| Right: " + right);
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    public static void intersection1(int[] arr1, int[] arr2) {
        arr1 = sortArray(arr1);
        arr2 = sortArray(arr2);
        int i = 0;
        int j = 0;
        int k = 0;
        while (k <= (arr1.length + arr2.length) - 1) {
            if (i == arr1.length || j == arr2.length)
                break;
            if (arr1[i] < arr2[j]) {
                k++;
                i++;
            } else if (arr2[j] < arr1[i]) {
                k++;
                j++;

            } else {
                System.out.print(arr1[i] + " ");
                i++;
                j++;
                k++;
            }
        }
    }

    public static void intersection(int[] arr1, int[] arr2) {
        arr1 = sortArray(arr1);
        arr2 = sortArray(arr2);
        int len1 = arr1.length;
        int len2 = arr2.length;
        int counter = 0;
        for (int i = 0; i < len1; i++) {

            for (int j = 0; j < len2; j++) {
                if (arr2[j] <= arr1[i] && arr1[i] > counter) {
                    if (arr1[i] == arr2[j]) {
                        System.out.print(arr1[i] + " ");
                        counter = arr1[i];
                        break;
                    }
                } else {
                    break;
                }
            }
        }

    }

    public static int[] sortArray(int[] inputArr) {
        int smallest = inputArr[0];
        int smallestIdx = 0;
        int len = inputArr.length;
        int count = 0;
        while (count <= len - 1) {
            smallestIdx = count;
            smallest = inputArr[count];
            for (int i = count + 1; i < len; i++) {
                if (inputArr[i] < smallest) {
                    smallest = inputArr[i];
                    smallestIdx = i;
                }
            }
            int tmp = inputArr[count];
            inputArr[count] = inputArr[smallestIdx];
            inputArr[smallestIdx] = tmp;
            count++;
        }
        return inputArr;
    }

    public static int[] countS(int n, int m, int[] a, int[] b) {
        int[] op = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (b[j] < a[i]) {
                    count++;
                }
            }
            op[i] = count;
        }
        return op;
    }

    public static int smallestDifferencePair(int[] arr1, int n, int[] arr2, int m) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] sortedMerged = new int[m + n];

        while (k <= sortedMerged.length - 1) {
            if (i == n) {
                for (int x = j; x < m; x++) {
                    sortedMerged[k] = arr2[x];
                    k++;
                }
                break;
            }
            if (j == m) {
                for (int x = i; x < n; x++) {
                    sortedMerged[k] = arr1[x];
                    k++;
                }
                break;
            }

            if (arr1[i] > arr2[j]) {
                sortedMerged[k] = arr2[j];
                k++;
                j++;
            } else {
                sortedMerged[k] = arr1[i];
                k++;
                i++;
            }

        }
        int diff = sortedMerged[1] - sortedMerged[0];

        for (int p = 1; p < k - 2; p++) {
            int tmp = sortedMerged[p + 1] - sortedMerged[p];
            if (tmp < diff) {
                diff = tmp;
            }
        }
        return diff;
    }

    public static int secondLargestElement(int[] arr, int n) {
        int largest = arr[0];
        int secondLargest = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];

            } else if (arr[i] > secondLargest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    public static int sqrtN(long N) {

        long mid = 0;
        long left = 1;
        long right = N / 2;
        long closest = 0;

        while (left < right) {
            mid = left + (right - left) / 2;
            long sqr = mid * mid;
            if ((sqr == N)) {
                return (int) mid;
            } else {
                if (N > sqr) {
                    left = mid + 1;
                    closest = (int) mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        return (int) closest;

    }

    public static int[] mergingSortedArrays(int[] arr1, int[] arr2) {
        int[] arr3 = new int[arr1.length + arr2.length];
        int k = 0;
        int i = 0;
        int j = 0;

        while (k <= arr3.length - 1) {
            if (i > arr1.length - 1) {
                for (int x = j; x <= arr2.length - 1; x++) {
                    arr3[k] = arr2[x];
                    k++;
                }
            } else if (j > arr2.length - 1) {
                for (int x = i; x <= arr1.length - 1; x++) {
                    arr3[k] = arr1[x];
                    k++;
                }
            } else {
                if (arr1[i] > arr2[j]) {
                    arr3[k] = arr2[j];
                    j++;
                } else {
                    arr3[k] = arr1[i];
                    i++;
                }
                k++;
            }

        }
        return arr3;
    }

    public static int[] insertionSort(int[] arr) {
        int len = arr.length;
        int tmp;
        for (int i = 1; i < len; i++) {
            int j = i - 1;
            tmp = arr[i];
            while (j >= 0 && arr[j] > tmp) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = tmp;
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        int len = arr.length;
        int smallestIdx = 0;
        int tmp;
        for (int i = 0; i < len - 1; i++) {
            smallestIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[smallestIdx]) {
                    smallestIdx = j;
                }
            }

            tmp = arr[i];
            arr[i] = arr[smallestIdx];
            arr[smallestIdx] = tmp;

        }

        return arr;
    }

    public static int kthSmallest(int[] arr, int n, int k) {
        int tmp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr[k - 1];
    }

    public static int[] bubbleSort(int[] arr) {
        int len = arr.length;
        int tmp;
        int i = len - 1;
        int count = len;
        while (count >= 0) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
            i--;
            count--;
        }

        return arr;

    }

    public static int binarySearchClosest(int[] nums, int left, int right, int target) {
        int closesIdx = 0;

        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            closesIdx = mid;
        }
        return closesIdx;
    }

    public static int binarySearch2(int[] nums, int target) {

        int l = 0;
        int r = nums.length - 1;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (target > nums[mid]) {
                l = mid + 1;

            } else {
                r = mid - 1;

            }

        }
        return -1;

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

    public static void print1DArray(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
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