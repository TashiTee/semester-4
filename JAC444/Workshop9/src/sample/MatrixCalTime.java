package sample;


import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MatrixCalTime {
    static int size = 2000;
    double matrix1[][] = new double[size][size];
    double matrix2[][] = new double[size][size];

    public MatrixCalTime() {
        this.initialize();
    }

    public void initialize() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix1[i][j] = Math.random();
                matrix2[i][j] = Math.random();
            }
        }
    }

    public void getTime() throws InterruptedException {
        //sequential method time
        SequentialAdd seqAdd = new SequentialAdd(matrix1, matrix2);
        seqAdd.displaySequentialTime();

        //parallel method time
        ParallelAdd parAdd = new ParallelAdd(matrix1, matrix2);
        parAdd.displayParallelTime();

    }

}
