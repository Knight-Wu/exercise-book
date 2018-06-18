package algorithm.sort;


import java.util.Arrays;

public class Sort {
    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    // 选择排序
    private static int[] selectionSort(int[] array) {
        for (int i = 0; i + 1 < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    // 冒泡排序
    private static int[] popUpSort(int[] array) {
        int begin = 0;
        for (int i = array.length - 1; i - 1 >= begin; i--) {
            if (array[i] > array[i - 1]) {
                swap(array, i, i - 1);
            }
            if (i == 1) {
                i = array.length;
                begin++;
            }
        }
        return array;
    }


    // 快排
    private static void quickSort(int[] array, int left, int right) {
        int i = left;
        int j = right;
        int pivot = array[(i + j) >> 1];
        while (i <= j) {
            while (array[i] > pivot) {
                i++;
            }
            while (array[j] < pivot) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
                i++;
                j--;
            } else if (i == j) {
                return;
            }

        }
        quickSort(array, left, j);
        quickSort(array, j + 1, right);
    }

    // 插入排序
    public static int[] insertionSort(int[] array) {
        for (int i = 0; i + 1 < array.length; i++) {
            int j = i + 1;
            int temp = array[j];
            while (j - 1 >= 0 && temp > array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
        return array;
    }

    private static void mergeArray(int[] array, int begin, int mid, int last, int[] result) {
        int i = begin;
        int j = mid + 1;
        int n = 0;
        while (i <= mid && j <= last) {
            if (array[i] < array[j]) {
                result[n++] = array[j++];
            } else {
                array[n++] = array[i++];
            }
        }
        while (i <= mid) {
            result[n++] = array[i++];
        }

        while (j <= last) {
            result[n++] = array[j++];
        }
    }


    // 归并排序
    private static int[] mergeSort(int[] array, int begin, int last) {
        int[] result = new int[array.length];
        if (begin < last) {
            int mid = (last + begin) / 2;
            mergeSort(array, begin, mid);
            mergeSort(array, mid + 1, last);
            mergeArray(array, begin, mid, last, result);
        }

        return result;
    }


    public static int searchInsert(Integer[] nums, int target) {
        boolean reverse = true;
        if (nums[0] <= nums[nums.length - 1]) {
            reverse = false;
        }

        for (int i = 0; i + 1 < nums.length; i++) {
            if (!reverse) {
                if (target < nums[0]) {
                    return 0;
                } else if (target > nums[i] && target < nums[i + 1]) {
                    return i + 1;
                } else {
                    return i;
                }
            } else {

                if (target > nums[0]) {
                    return 0;
                } else if (target < nums[i] && target > nums[i + 1]) {
                    return i + 1;
                } else if (nums[i] == target) {
                    return i;
                }

            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 20, 9, 8, 0, 5, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


}
