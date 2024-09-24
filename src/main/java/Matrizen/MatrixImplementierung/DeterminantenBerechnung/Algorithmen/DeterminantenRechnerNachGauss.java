package Matrizen.MatrixImplementierung.DeterminantenBerechnung.Algorithmen;


import Matrizen.ExceptionHandling.ExceptionHandler;
import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.DeterminantenBerechnung.Determinante;
import Matrizen.MatrixImplementierung.Matrix;

public class DeterminantenRechnerNachGauss implements Determinante {

    @Override
    public double getDeterminante(IMatrix m) {
        new ExceptionHandler().throwExceptionFallsMatrixNichtQuadratischIst(m);

        IMatrix matrix = new Matrix((Matrix) m);
        IMatrix dreiecksMatrix = matrix.getStufenForm();
        double[][] stufenMatrixArray = dreiecksMatrix.getMatrix();

        double ergebnis = 1;
        for (int i = 0; i < dreiecksMatrix.getAnzahlZeilen(); i++) {
            ergebnis *= stufenMatrixArray[i][i];
        }

        return ergebnis;
    }
}
