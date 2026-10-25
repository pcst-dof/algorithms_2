import java.util.Arrays;

public class CountingSort {

    public static int[] countingSort(int[] pixels) {
        int n = pixels.length;
        if (n == 0) return new int[0];

        int[] count = new int[256];

        for (int value : pixels) {
            count[value]++;
        }

        int[] sorted = new int[n];
        int idx = 0;
        for (int v = 0; v < 256; v++) {
            while (count[v] > 0) {
                sorted[idx++] = v;
                count[v]--;
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[] test1 = {255, 0, 128, 128};
        System.out.println("Тест 1: " + Arrays.toString(countingSort(test1)));
        // [0, 128, 128, 255], тогда O(N)

        int[] test2 = {7, 3, 3, 1};
        System.out.println("Тест 2: " + Arrays.toString(countingSort(test2)));
        // [1, 3, 3, 7], O(N)

        System.out.println("Лучший/средний/худший случай: O(N + K), где K=256 - фактически O(N)");
        System.out.println("Память:                       O(K) = O(1) относительно N");
    }
}