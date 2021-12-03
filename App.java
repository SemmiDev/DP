import java.util.*;

public class App {
    public static void main(String[] args) {
        int length = 10;          
        int kapasitasKnapsack = 15;        
        // int[] weights = sortDescending(random(length, 1, 50));
        // int[] profit = random(length, 1, 50);

        int[] weights = {12,1,2,1,10};
        int[] profit = {4,2,2,1,4};
    
        sortDescending(weights);
    
        int result = greedyKnapsack(weights, profit, kapasitasKnapsack);
        System.out.println("Hasil : " + result);

        System.out.println("-----------------------");

        result = dynamicProgrammingKnapsack(weights, profit, kapasitasKnapsack);
        System.out.println("Hasil : " + result);
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] sortDescending(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        int[] left = new int[arr.length / 2];
        int[] right = new int[arr.length - left.length];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[i + left.length];
        }
        left = sortDescending(left);
        right = sortDescending(right);
        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] > right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            result[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            result[k] = right[j];
            j++;
            k++;
        }
        return result;
    }

    public static int dynamicProgrammingKnapsack(int[] weights, int[] profits, int kapasitasKnapsack) {
        int[] dp = new int[kapasitasKnapsack + 1];
        for (int i = 0; i < profits.length; i++) {
            for (int j = kapasitasKnapsack; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + profits[i]);
            }
        }
        printArray(dp);
        return dp[kapasitasKnapsack];
    }

    public static int greedyKnapsack(int[] weights, int[] profit, int kapasitasKnapsack) {
        int totalProfit = 0;
        int totalWeight = 0;
        for (int i = 0; i < weights.length; i++) {
            if (totalWeight + weights[i] <= kapasitasKnapsack) {
                System.out.println("Item " + weights[i] + " ditambahkan dengan profit " + profit[i]);
                totalWeight += weights[i];
                totalProfit += profit[i];
            }
        }
        return totalProfit;
    }

    public static int[] random(int length, int min, int max) {
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = (int) (Math.random() * (max - min) + min);
        }
        return data;
    }
}