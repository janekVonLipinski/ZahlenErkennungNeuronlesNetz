package Matrizen.Util;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

public class Zeile {

    public IMatrix streicheUebergebeneZeileUndSpalte(IMatrix m, int gestricheneZeile, int gestricheneSpalteIndex) {
        int anzahlSpalten = m.getAnzahlSpalten();
        int anzahlZeilen = m.getAnzahlZeilen();

        double[][] matrix = m.getMatrix();
        double[][] neueMatrix = new double[anzahlZeilen - 1][anzahlSpalten - 1];
        double[][] alteMatrix = matrix.clone();

        int alteMatrixZeilenIndex = 0;

        for (int zeilenIndex = 0; zeilenIndex < neueMatrix.length; zeilenIndex++) {

            if (istZeileUebersprungeneZeile(gestricheneZeile, zeilenIndex)) {
                alteMatrixZeilenIndex = ueberspringeZeile(alteMatrixZeilenIndex);
            }

            kopiereSpalteVonAlterMatrixInNeueMatrix(gestricheneSpalteIndex, zeilenIndex,
                    alteMatrixZeilenIndex, neueMatrix, alteMatrix);

            alteMatrixZeilenIndex++;
        }

        return new Matrix(neueMatrix);
    }

    private void kopiereSpalteVonAlterMatrixInNeueMatrix(int gestricheneSpalteIndex, int zeilenIndex, int alteMatrixZeilenIndex,
                                                         double[][] neueMatrix, double[][] alteMatrix) {

        int alteMatrixSpalteIndex = 0;

        for (int spaltenIndexVonNeuerMatrix = 0; spaltenIndexVonNeuerMatrix < neueMatrix.length; spaltenIndexVonNeuerMatrix++) {

            if (istUebersprungeneSpalte(gestricheneSpalteIndex, alteMatrixSpalteIndex)) {
                alteMatrixSpalteIndex = uebersrpingeSpalte(alteMatrixSpalteIndex);
            }

            double[] spalteVonNeuerMatrix = neueMatrix[zeilenIndex];
            double[] spalteVonAlterMatrix = alteMatrix[alteMatrixZeilenIndex];

            ersetzteElementInSpalte(spaltenIndexVonNeuerMatrix, alteMatrixSpalteIndex,
                    spalteVonNeuerMatrix, spalteVonAlterMatrix);

            alteMatrixSpalteIndex++;
        }
    }

    private void ersetzteElementInSpalte(int spaltenIndex, int alteMatrixSpaltenIndex,
                                         double[] neueMatrix, double[] alteMatrix) {
        neueMatrix[spaltenIndex] = alteMatrix[alteMatrixSpaltenIndex];
    }

    private int uebersrpingeSpalte(int alteMatrixSpalte) {
        alteMatrixSpalte++;
        return alteMatrixSpalte;
    }

    private boolean istUebersprungeneSpalte(int gestricheneSpalte, int alteMatrixSpalte) {
        return alteMatrixSpalte == gestricheneSpalte;
    }

    private boolean istZeileUebersprungeneZeile(int zeile, int zeilenIndex) {
        return zeilenIndex == zeile;
    }

    private int ueberspringeZeile(int alteMatrixZeile) {
        alteMatrixZeile++;
        return alteMatrixZeile;
    }
}
