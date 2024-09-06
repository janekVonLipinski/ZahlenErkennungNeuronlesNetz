package Matrizen.MatrixImplementierung.MatrixTransponierung;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

public class Transponierung {

    public IMatrix transponiere(IMatrix m) {
        IMatrix matrixKopie = new Matrix((Matrix) m);
        double[][] matrix = matrixKopie.getMatrix();

        for (int zeilenIndex = 0; zeilenIndex < matrix.length; zeilenIndex++) {
            for (int spaltenIndex = zeilenIndex; spaltenIndex < matrix.length; spaltenIndex++) {
                if (zeilenIndex == spaltenIndex) {
                    continue;
                }
                double temp = matrix[zeilenIndex][spaltenIndex];
                matrix[zeilenIndex][spaltenIndex] = matrix[spaltenIndex][zeilenIndex];
                matrix[spaltenIndex][zeilenIndex] = temp;
            }
        }

        return matrixKopie;
    }
}
