/**
 * Created by berkin on 29.04.2016.
 */
public class QuickSort {
    public void quickSort(int[] A, int p, int r){
        int i = p, j = r, h;

        int pivot = A[(p + r) / 2];

        do {
            while (A[i] < pivot)
                i++;
            while (A[j] > pivot)
                j--;
            if (i <= j) {
                h = A[i];
                A[i] = A[j];
                A[j] = h;
                i++;
                j--;
            }
        } while (i <= j);

        if (p < j)
            quickSort(A, p, j);
        if (i < r)
            quickSort(A, i, r);
    }
}
