package Matrizen.MatrixImplementierung.MatrixUmformung;


import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

public class Diagonalform {

    private final Hilfe gaussHilfsFunktionen;

    public Diagonalform(Hilfe gaussHilfsFunktionen) {
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    public Matrix formeMatrixInDiagonalFormUm(IMatrix matrix) {

        double[][] matrixArray = matrix.getMatrix();

        for (int j = matrixArray.length - 1; j >= 0; j--) {
            subtrahiereJteZeileVonAllenDarueberLiegendenZeilenSodassWerteNullWerden(j, matrixArray);
        }
        return new Matrix(matrixArray);
    }

    private void subtrahiereJteZeileVonAllenDarueberLiegendenZeilenSodassWerteNullWerden(
            int j, double[][] matrixArray) {
        for (int k = 1; j - k >= 0; k++) {
            double[] zeileVonDerSubtrahiertWird = matrixArray[j -k];
            double[] zeileDieSubtrahiertWird = matrixArray[j];

            subtrahiereVonJMinusKteZeileSodassElementNullWird(
                    zeileDieSubtrahiertWird, j, zeileVonDerSubtrahiertWird, matrixArray, k);
        }
    }

    private void subtrahiereVonJMinusKteZeileSodassElementNullWird(
            double[] zeileDieSubtrahiertWird, int j, double[] zeileVonDerSubtrahiertWird,
            double[][] matrixArray, int k) {
        double koeffizient = zeileVonDerSubtrahiertWird[j] / zeileDieSubtrahiertWird[j];

        matrixArray[j - k] = gaussHilfsFunktionen.subtrahiereZeile(zeileDieSubtrahiertWird,
                zeileVonDerSubtrahiertWird, koeffizient);
    }
}
