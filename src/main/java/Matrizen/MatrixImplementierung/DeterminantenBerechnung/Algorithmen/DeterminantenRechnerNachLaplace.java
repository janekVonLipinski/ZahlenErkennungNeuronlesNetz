package Matrizen.MatrixImplementierung.DeterminantenBerechnung.Algorithmen;


import Matrizen.ExceptionHandling.ExceptionHandler;
import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.DeterminantenBerechnung.Determinante;
import Matrizen.Util.Zeile;

public class DeterminantenRechnerNachLaplace implements Determinante {

    public double getDeterminante(IMatrix m) {
        new ExceptionHandler().throwExceptionFallsMatrixNichtQuadratischIst(m);
        return getDeterminante(m, 1);
    }

    private double getDeterminante(IMatrix m, double faktor) {
        int anzahlZeilen = m.getAnzahlZeilen();
        int anzahlSpalten = m.getAnzahlSpalten();
        double[][] matrix = m.getMatrix();

        if (anzahlZeilen != anzahlSpalten) {
            throw new IllegalArgumentException("Matrix mus n x n sein");
        }

        if (faktor == 0) {
            return 0;
        }

        if (anzahlZeilen == 2) {
            return getDeterminanteVonZWeiKreuzZweiMatrix(matrix);
        }

        double ergebnis = 0;

        for (int spaltenIndex = 0; spaltenIndex < anzahlSpalten; spaltenIndex++) {

            IMatrix verkleinerteMatrix = streicheErsteZeileUndUebergebeneSpalte(m, spaltenIndex);

            double zunahme =
                    Math.pow(-1, spaltenIndex)
                    * matrix[0][spaltenIndex]
                    * getDeterminante(verkleinerteMatrix, matrix[0][spaltenIndex]);

            ergebnis += zunahme;
        }

        return ergebnis;
    }

    private double getDeterminanteVonZWeiKreuzZweiMatrix(double[][] matrix) {
        return (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
    }

    private IMatrix streicheErsteZeileUndUebergebeneSpalte(IMatrix m, int spalte) {
        return new Zeile().streicheUebergebeneZeileUndSpalte(m, 0, spalte);
    }
}
