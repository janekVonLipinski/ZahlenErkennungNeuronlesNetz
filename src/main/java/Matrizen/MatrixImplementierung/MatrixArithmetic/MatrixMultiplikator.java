package Matrizen.MatrixImplementierung.MatrixArithmetic;


import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

public class MatrixMultiplikator {

    public IMatrix multipliziere(IMatrix m1, IMatrix m2) {
        IMatrix ergebnis = new Matrix(new double[m1.getAnzahlZeilen()][m2.getAnzahlSpalten()]);

        if (m1.getAnzahlSpalten() != m2.getAnzahlZeilen()) {
            throw new IllegalArgumentException(
                    "Zeilen von linker Matrix müssen gleich den Spalten der zweiten sein"
            );
        }

        if (m1.getAnzahlSpalten() == 1 && m2.getAnzahlZeilen() == 1) {
            for (int i = 0; i < m1.getAnzahlZeilen(); i++) {
                for (int j = 0; j < m2.getAnzahlSpalten(); j++) {
                    ergebnis.getMatrix()[i][j] = m1.getMatrix()[i][0] * m2.getMatrix()[0][j];
                }
            }

            return ergebnis;
        }

        for (int i = 0; i < ergebnis.getAnzahlZeilen(); i++) {
            for (int j = 0; j < ergebnis.getAnzahlSpalten(); j++) {
                ergebnis.getMatrix()[i][j] = 0;
                for (int k = 0; k < m1.getAnzahlZeilen(); k++) {
                    ergebnis.getMatrix()[i][j] += (m1.getMatrix()[i][k] * m2.getMatrix()[k][j]);
                }
            }
        }

        return ergebnis;
    }

    public IMatrix multipliziere(IMatrix m, double skalar) {
        IMatrix kopierteMatrix = new Matrix((Matrix) m);
        double[][] matrix = kopierteMatrix.getMatrix();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] *= skalar;
            }
        }

        return kopierteMatrix;
    }
}
