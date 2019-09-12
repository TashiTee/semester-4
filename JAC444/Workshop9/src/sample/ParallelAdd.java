package sample;

public class ParallelAdd implements Runnable {
    double matrix1[][];
    double matrix2[][];
    int size;
    int start;
    int end;
    public static double sum[][];

    public ParallelAdd(double m1[][], double m2[][]) {
        matrix1 = m1;
        matrix2 = m2;
        size = m1.length;
        sum = new double[size][size];
    }

    public ParallelAdd(double m1[][], double m2[][], int start, int end) {
        matrix1 = m1;
        matrix2 = m2;
        this.size = m1.length;
        this.start = start;
        this.end = end;
        sum = new double[size][size];
    }

    @Override
    public void run() {
        for(int i = this.start; i < this.end; i++) {
            for(int j = 0; j < matrix1.length; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
    }

    public static double[][] parallelAddMatrix(double[][] m1, double[][] m2){
        long startTime, endTime;
        int arrayLength = m1.length;
        double sum[][] = new double[arrayLength][arrayLength];

        ParallelAdd parallel1 = new ParallelAdd(m1, m2, 0, arrayLength/4 - 1);
        ParallelAdd parallel2 = new ParallelAdd(m1, m2, arrayLength / 4, arrayLength / 2 - 1);
        ParallelAdd parallel3 = new ParallelAdd(m1, m2, arrayLength / 2, arrayLength / 4 * 3 - 1);
        ParallelAdd parallel4 = new ParallelAdd(m1, m2, arrayLength / 4 * 3, arrayLength - 1);

        Thread t1 = new Thread(parallel1);
        Thread t2 = new Thread(parallel2);
        Thread t3 = new Thread(parallel3);
        Thread t4 = new Thread(parallel4);

        startTime = System.nanoTime();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        endTime = System.nanoTime();

        System.out.println("Parallel time:\t" + (endTime - startTime));

        return sum;
    }

    public void displayParallelTime() {
        parallelAddMatrix(matrix1, matrix2);
    }
}
