import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {

    public static double[] bucketSort(double[] signal) {
        int n = signal.length;
        if (n == 0) return new double[0];

        List<List<Double>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }

        for (double value : signal) {
            int index = (int) (n * value);
            if (index == n) index = n - 1;
            buckets.get(index).add(value);
        }

        for (int i = 0; i < n; i++) {
            List<Double> bucket = buckets.get(i);
            for (int j = 1; j < bucket.size(); j++) {
                double key = bucket.get(j);
                int k = j - 1;
                while (k >= 0 && bucket.get(k) > key) {
                    bucket.set(k + 1, bucket.get(k));
                    k--;
                }
                bucket.set(k + 1, key);
            }
        }

        double[] sorted = new double[n];
        int idx = 0;
        for (List<Double> bucket : buckets) {
            for (double v : bucket) {
                sorted[idx++] = v;
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        double[] test1 = {0.55, 0.05, 0.95, 0.35};
        System.out.println("Тест 1: " + Arrays.toString(bucketSort(test1)));
        // [0.05, 0.35, 0.55, 0.95], тогда O(N)

        double[] test2 = {0.12, 0.13, 0.90};
        System.out.println("Тест 2: " + Arrays.toString(bucketSort(test2)));
        // [0.12, 0.13, 0.90], O(N)

        System.out.println("Лучший/средний случай: O(N) - равномерное распределение");
        System.out.println("Худший случай:         O(N^2) - все элементы в одной корзине");
        System.out.println("Память:                O(N)");
    }
}
