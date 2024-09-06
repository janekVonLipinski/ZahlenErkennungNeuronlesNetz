package Matrizen.ExceptionHandling;


import Matrizen.IMatrix;

public class ExceptionHandler {

    public void throwExceptionFallsMatrixNichtQuadratischIst(IMatrix matrix) {
        if (matrix.getAnzahlSpalten() != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix ist nicht quadratisch");
        }
    }
}
