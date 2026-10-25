import java.util.Arrays;

public class RadixSort {

    public static int[] radixSort(int[] serials) {
        if (serials == null || serials.length == 0) return new int[0];

        int max = serials[0];
        for (int i = 1; i < serials.length; i++) {
            if (serials[i] > max) {
                max = serials[i];
            }
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(serials, exp);
        }
        return serials;
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] test1 = {123456, 654321, 111111};
        System.out.println("Тест 1: " + Arrays.toString(radixSort(test1.clone())));
        // [111111, 123456, 654321], тогда O(N * K)

        int[] test2 = {9000, 1000, 99};
        System.out.println("Тест 2: " + Arrays.toString(radixSort(test2.clone())));
        // [99, 1000, 9000], O(N * K)

        System.out.println("Лучший/средний/худший случай: O(N * K), где K - количество разрядов (проходов)");
        System.out.println("Память:                       O(N) - дополнительный массив для стабильной сортировки");
    }
}