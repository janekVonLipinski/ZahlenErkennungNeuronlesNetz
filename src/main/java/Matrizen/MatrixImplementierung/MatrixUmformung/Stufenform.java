package Matrizen.MatrixImplementierung.MatrixUmformung;


import Matrizen.MatrixImplementierung.Matrix;

public class Stufenform {

    private final Hilfe gaussHilfsFunktionen;

    public Stufenform(Hilfe gaussHilfsFunktionen) {
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    public Matrix formeMatrixInStufenformUm(Matrix matrix) {
        double[][] matrixArray = matrix.getMatrix();

        for (int diagonalElementIndex = 0; diagonalElementIndex < matrixArray.length; diagonalElementIndex++) {

            tauscheSolangeZeilenBisWertAufDiagonaleNichtNullIst(matrixArray, diagonalElementIndex);
            eliminiereAlleElementeUnterDiagonalElement(diagonalElementIndex, matrixArray);
        }

        return new Matrix(matrixArray);
    }

    private void eliminiereAlleElementeUnterDiagonalElement(int diagonalElementIndex, double[][] matrixArray) {

        for (int naechsterZeilenIndex = 1;
             diagonalElementIndex + naechsterZeilenIndex < matrixArray.length;
             naechsterZeilenIndex++) {

            int indexVonNaechsterZeileDieEliminiertWird = diagonalElementIndex + naechsterZeilenIndex;

            double[] zeileNachDerEntwickeltWird = matrixArray[diagonalElementIndex];
            double[] zeileInDerEliminiertWird = matrixArray[indexVonNaechsterZeileDieEliminiertWird];

            double diagonalElement = zeileNachDerEntwickeltWird[diagonalElementIndex];
            double zuEliminierendesElement = zeileInDerEliminiertWird[diagonalElementIndex];

            double koeffizientDerMitZeileMultipliziertWird = zuEliminierendesElement / diagonalElement;

            matrixArray[indexVonNaechsterZeileDieEliminiertWird] = eliminiereElement(
                    zeileNachDerEntwickeltWird, zeileInDerEliminiertWird, koeffizientDerMitZeileMultipliziertWird);
        }
    }

    private void tauscheSolangeZeilenBisWertAufDiagonaleNichtNullIst(double[][] matrixArray, int zeileNachDerEntwickeltWird) {
        int andereZeile = 1;
        while (gaussHilfsFunktionen.istWertAufDiagonaleNull(matrixArray, zeileNachDerEntwickeltWird)) {
            gaussHilfsFunktionen.tauscheZeileVonMatrix(matrixArray, zeileNachDerEntwickeltWird, andereZeile);
            andereZeile++;
        }
    }

    private double[] eliminiereElement(double[] zeileNachDerEntwickeltWird, double[] zeileInDerEliminiertWird,
                                       double koeffizientDerMitZeileMultipliziertWird) {

        return gaussHilfsFunktionen.subtrahiereZeile(zeileNachDerEntwickeltWird,
                zeileInDerEliminiertWird, koeffizientDerMitZeileMultipliziertWird);
    }
}
