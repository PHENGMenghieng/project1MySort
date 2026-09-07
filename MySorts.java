
//  MySort.java
//  Implements Insert Sort, Select Sort, Quick Sort, and Merge Sort.
//  Quick Sort and Merge Sort use recursive helper methods.

public class MySorts
{
    // INSERT SORT
    public static void insertSort(int[] arr)
    {
        for (int i = 1; i < arr.length; i++)
        {
            int key = arr[i];
            int j = i - 1;

            // Shift elements of arr[0..i-1] that are greater than key
            // one position ahead of their current position

            while (j >= 0 && arr[j] > key)
            {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // SELECT SORT
    public static void selectSort(int[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
            {
                if (arr[j] < arr[minIndex])
                {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with arr[i]

            if (minIndex != i)
            {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    // QUICK SORT (public entry point)

    public static void quickSort(int[] arr)
    {
        if (arr.length > 1)
        {
            quickSortRecursive(arr, 0, arr.length - 1);
        }
    }

    // find pivot point for quick sort
    // Uses the last element as the pivot and partitions the array
    // so everything <= pivot is on the left, everything > pivot on the right.
    // Returns the final index of the pivot element.

    private static int pivot(int[] arr, int begin, int end)
    {
        int pivotValue = arr[end];
        int i = begin - 1; // index of the last element known to be <= pivot

        for (int j = begin; j < end; j++)
        {
            if (arr[j] <= pivotValue)
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // place the pivot value in its correct sorted position

        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;
    }

    // quick sort recursive version
    // sort the portion of the given array arr, from begin to end

    private static void quickSortRecursive(int[] arr, int begin, int end)
    {
        if (begin < end)
        {
            int pivotIndex = pivot(arr, begin, end);
            quickSortRecursive(arr, begin, pivotIndex - 1);
            quickSortRecursive(arr, pivotIndex + 1, end);
        }
    }

    // MERGE SORT (public entry point)

    public static void mergeSort(int[] arr)
    {
        if (arr.length > 1)
        {
            mergeSortRecursive(arr, 0, arr.length - 1);
        }
    }

    // merge sort recursive version
    // sort the portion of the given array arr, from begin to end

    private static void mergeSortRecursive(int[] arr, int begin, int end)
    {
        if (begin < end)
        {
            int middle = begin + (end - begin) / 2;
            mergeSortRecursive(arr, begin, middle);
            mergeSortRecursive(arr, middle + 1, end);
            merge(arr, begin, middle, end);
        }
    }

    // merge method
    // merge two sorted portions of given array arr, namely, from start to middle and from middle + 1 to end into one sorted portion, namely, from start to end

    private static void merge(int[] arr, int start, int middle, int end)
    {
        int n1 = middle - start + 1; // size of left half
        int n2 = end - middle;       // size of right half

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[start + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[middle + 1 + j];

        int i = 0, j = 0;
        int k = start;

        while (i < n1 && j < n2)
        {
            if (leftArr[i] <= rightArr[j])
            {
                arr[k] = leftArr[i];
                i++;
            }
            else
            {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // copy remaining elements of left Arr, if there is any

        while (i < n1)
        {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // copy remaining elements of rightArr, if there is any
        
        while (j < n2)
        {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
}
