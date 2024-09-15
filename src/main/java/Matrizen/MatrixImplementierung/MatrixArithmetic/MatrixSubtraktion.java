package Matrizen.MatrixImplementierung.MatrixArithmetic;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

public class MatrixSubtraktion {

    public IMatrix subtrahiere(IMatrix m1, IMatrix m2) {

        if (m1.getAnzahlSpalten() != m2.getAnzahlSpalten() || m1.getAnzahlZeilen() != m2.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrizen müssen selben rang haben");
        }

        double[][] ergebnis = new double[m1.getAnzahlZeilen()][m1.getAnzahlSpalten()];

        for (int i = 0; i < m1.getAnzahlZeilen(); i++) {
            for (int j = 0; j < m1.getAnzahlSpalten(); j++) {
                ergebnis[i][j] =  m1.getMatrix()[i][j] - m2.getMatrix()[i][j];
            }
        }

        return new Matrix(ergebnis);
    }
}
