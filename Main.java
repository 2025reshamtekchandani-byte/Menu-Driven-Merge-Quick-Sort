
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    // Merge Sort
    static void mergeSort(int[] prices, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(prices, left, mid);
            mergeSort(prices, mid + 1, right);

            merge(prices, left, mid, right);
        }
    }

    static void merge(int[] prices, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = prices[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = prices[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                prices[k] = L[i];
                i++;
            } else {
                prices[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            prices[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            prices[k] = R[j];
            j++;
            k++;
        }
    }

    // Quick Sort
    static void quickSort(int[] prices, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(prices, low, high);

            quickSort(prices, low, pivotIndex - 1);
            quickSort(prices, pivotIndex + 1, high);
        }
    }

    static int partition(int[] prices, int low, int high) {
        int pivot = prices[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (prices[j] <= pivot) {
                i++;

                int temp = prices[i];
                prices[i] = prices[j];
                prices[j] = temp;
            }
        }

        int temp = prices[i + 1];
        prices[i + 1] = prices[high];
        prices[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of products must be positive.");
            sc.close();
            return;
        }

        int[] prices = new int[n];

        System.out.println("Enter product prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        System.out.println("\nChoose Sorting Method:");
        System.out.println("1. Merge Sort");
        System.out.println("2. Quick Sort");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();

        int[] result = Arrays.copyOf(prices, n);

        switch (choice) {
            case 1:
                mergeSort(result, 0, n - 1);
                System.out.println("\nSorted using Merge Sort:");
                System.out.println(Arrays.toString(result));
                break;

            case 2:
                quickSort(result, 0, n - 1);
                System.out.println("\nSorted using Quick Sort:");
                System.out.println(Arrays.toString(result));
                break;

            default:
                System.out.println("Invalid choice!");
                sc.close();
                return;
        }

        // Compare both sorting methods
        int[] mergeResult = Arrays.copyOf(prices, n);
        int[] quickResult = Arrays.copyOf(prices, n);

        mergeSort(mergeResult, 0, n - 1);
        quickSort(quickResult, 0, n - 1);

        System.out.println("\nComparison of Sorted Outputs:");
        System.out.println("Merge Sort: " + Arrays.toString(mergeResult));
        System.out.println("Quick Sort: " + Arrays.toString(quickResult));

        if (Arrays.equals(mergeResult, quickResult)) {
            System.out.println("Both sorting methods produce the same result.");
        }

        sc.close();
    }
}
