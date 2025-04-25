import java.util.*;
import java.io.*;

public class Solution {

    public static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
    
        while (left < right) {
            int mid = (left + right) / 2;
    
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
    
        return left;
    }
    
    public static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
    
        while (left < right) {
            int mid = (left + right) / 2;
    
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
    
        return left;
    }

    public static void main(String[] args) throws IOException {
        int[] arr = {3, 3, 7, 5, 1, 3};

        Arrays.sort(arr);

        int target = 3;

        int lower = lowerBound(arr, target);
        int upper = upperBound(arr, target);

        System.out.println("lower: " + lower);
        System.out.println("upper: " + upper);
    }
}
