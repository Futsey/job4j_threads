package concurrent.completablefuture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MatrixDiagSumEx {

    private static int[][] dArray =
            {{13}, {32}, {634}, {87}, {58}, {55}, {97}, {96}, {45}, {24}, {78}, {23}, {93}, {23}, {13}, {11}};

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] originArr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum rolColSum = new RolColSum();
        rolColSum.sum(originArr);
        RolColSum.asyncSum(originArr);
    }

    public static int[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        int[] sums = new int[2 * n];
        Map<Integer, CompletableFuture<Integer>> futures = new HashMap<>();
        /**
         * считаем сумму по главной диагонали
          */
        futures.put(0, getTask(matrix, 0, n - 1, n - 1));
        /**
         * считаем суммы по побочным диагоналям
         */
        for (int k = 1; k <= n; k++) {
            futures.put(k, getTask(matrix, 0, k - 1,  k - 1));
            if (k < n) {
                futures.put(2 * n - k, getTask(matrix, n - k, n - 1, n - 1));
            }
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Integer> getTask(int[][] data, int startRow, int endRow, int startCol) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int col = startCol;
            for (int i = startRow; i <= endRow; i++) {
                sum += data[i][col];
                col--;
            }
            return sum;
        });
    }
}
