package Matrizen.MatrixImplementierung.MatrixTransponierung;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

public class Transponierung {

    public IMatrix transponiere(IMatrix m) {
        IMatrix matrixKopie = new Matrix((Matrix) m);
        double[][] matrix = matrixKopie.getMatrix();
        double[][] transponierteMatrix = new double[m.getAnzahlSpalten()][m.getAnzahlZeilen()];

        for (int zeilenIndex = 0; zeilenIndex < m.getAnzahlZeilen(); zeilenIndex++) {
            for (int spaltenIndex = 0; spaltenIndex < m.getAnzahlSpalten(); spaltenIndex++) {
                transponierteMatrix[spaltenIndex][zeilenIndex] = matrix[zeilenIndex][spaltenIndex];
            }
        }

        return new Matrix(transponierteMatrix);
    }
}
