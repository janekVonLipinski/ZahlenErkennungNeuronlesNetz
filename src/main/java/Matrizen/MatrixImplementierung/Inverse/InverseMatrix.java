package Matrizen.MatrixImplementierung.Inverse;


import Matrizen.ExceptionHandling.ExceptionHandler;
import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import Matrizen.Util.Zeile;

public class InverseMatrix {

    public IMatrix getInverseMatrix(IMatrix m) {
        new ExceptionHandler().throwExceptionFallsMatrixNichtQuadratischIst(m);

        double determinanteVonM = m.getDeterminante();
        double kehrWertVonDeterminante = 1 / determinanteVonM;

        if (m.getAnzahlSpalten() == 2) {
            double[][] matrix = m.getMatrix();
            double[][] inverseBeiZweiKreuzZweiOhneMultiplikationMitEinsDurchD =
                    {{matrix[1][1], -matrix[0][1]}, {-matrix[1][0], matrix[0][0]}};
            double determinante = m.getDeterminante();

            return new Matrix(inverseBeiZweiKreuzZweiOhneMultiplikationMitEinsDurchD)
                    .multipliziere(1 / determinante);
        }

        IMatrix adjunkteVonM = getAdjunkte(m);
        return adjunkteVonM.multipliziere(kehrWertVonDeterminante);
    }

    private IMatrix getAdjunkte(IMatrix m) {
        IMatrix kofaktorenMatrix = getKofaktorenMatrix(m);
        return kofaktorenMatrix.transponiere();
    }

    private IMatrix getKofaktorenMatrix(IMatrix m) {
        double[][] matrix = m.getMatrix();
        double[][] neueMatrix = new double[matrix.length][matrix.length];

        for (int zeilenIndex = 0; zeilenIndex < matrix.length; zeilenIndex++) {
            for (int spaltenIndex = 0; spaltenIndex < matrix.length; spaltenIndex++) {
                neueMatrix[zeilenIndex][spaltenIndex] = getKofaktor(m, zeilenIndex, spaltenIndex);
            }
        }

        return new Matrix(neueMatrix);
    }

    private double getKofaktor(IMatrix m, int zeile, int spalte) {

        IMatrix verkleinerteMatrix = new Zeile().streicheUebergebeneZeileUndSpalte(m, zeile, spalte);
        return Math.pow(-1, zeile + spalte) * verkleinerteMatrix.getDeterminante();
    }
}
