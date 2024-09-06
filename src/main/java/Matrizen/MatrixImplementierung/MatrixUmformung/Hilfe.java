package Matrizen.MatrixImplementierung.MatrixUmformung;

import java.util.Arrays;

public class Hilfe {

    protected boolean istWertAufDiagonaleNull(double[][] matrix, int j) {
        return matrix[j][j] == 0;
    }

    protected void tauscheZeileVonMatrix(double[][] matrixArray, int indexErsteZeile, int indexZweiteZeile) {

        double[] ersteZeile = matrixArray[indexErsteZeile];
        double[] zweiteZeile = matrixArray[indexZweiteZeile];

        matrixArray[indexErsteZeile] = zweiteZeile;
        matrixArray[indexZweiteZeile] = ersteZeile;
    }

    protected double[] subtrahiereZeile(double[] ersteZeile, double[] zweiteZeile,
                                        double koeffiezient) {

        double[] multiplizierteZeile = multipliziereZeile(ersteZeile, koeffiezient);

        double[] returnArray = new double[ersteZeile.length];
        for (int i = 0; i < ersteZeile.length; i++) {
            returnArray[i] = zweiteZeile[i] - multiplizierteZeile[i];
        }

        return returnArray;
    }

    private double[] multipliziereZeile(double[] zeile, double koeffizient)  {
        double[] copy = Arrays.copyOf(zeile, zeile.length);
        return Arrays.stream(copy)
                .map(i -> i * koeffizient)
                .toArray();
    }
}
