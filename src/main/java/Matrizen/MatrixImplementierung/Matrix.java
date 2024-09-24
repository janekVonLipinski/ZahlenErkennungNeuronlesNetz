package Matrizen.MatrixImplementierung;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.DeterminantenBerechnung.Algorithmen.DeterminantenRechnerNachLaplace;
import Matrizen.MatrixImplementierung.DeterminantenBerechnung.Determinante;
import Matrizen.MatrixImplementierung.Inverse.InverseMatrix;
import Matrizen.MatrixImplementierung.MatrixArithmetic.MatrixMultiplikator;
import Matrizen.MatrixImplementierung.MatrixArithmetic.MatrixSubtraktion;
import Matrizen.MatrixImplementierung.MatrixTransponierung.Transponierung;
import Matrizen.MatrixImplementierung.MatrixUmformung.Diagonalform;
import Matrizen.MatrixImplementierung.MatrixUmformung.Hilfe;
import Matrizen.MatrixImplementierung.MatrixUmformung.Stufenform;


import java.util.Arrays;
import java.util.stream.Collectors;

public class Matrix implements IMatrix {

    private final double[][] matrix;
    private final Determinante determinantenRechner;
    private final MatrixMultiplikator matrixMultiplikator;
    private final InverseMatrix inverseMatrix;
    private final Stufenform stufenform;
    private final Diagonalform diagonalform;
    private final Transponierung transponierung;

    public Matrix(double[][] matrix, Determinante determinantenRechner, MatrixMultiplikator matrixMultiplikator,
                  InverseMatrix inverseMatrix, Stufenform stufenForm, Diagonalform diagonalform, Transponierung transponierung) {
        this.matrix = matrix;
        this.determinantenRechner = determinantenRechner;
        this.matrixMultiplikator = matrixMultiplikator;
        this.inverseMatrix = inverseMatrix;
        this.stufenform = stufenForm;
        this.diagonalform = diagonalform;
        this.transponierung = transponierung;
    }

    public Matrix(Matrix m) {
        this.matrix = Arrays.stream(m.getMatrix()).map(double[]::clone).toArray(double[][]::new);
        this.determinantenRechner = m.determinantenRechner;
        this.matrixMultiplikator = m.matrixMultiplikator;
        this.inverseMatrix = m.inverseMatrix;
        this.stufenform = m.stufenform;
        this.diagonalform = m.diagonalform;
        this.transponierung = m.transponierung;
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.determinantenRechner = new DeterminantenRechnerNachLaplace();
        this.matrixMultiplikator = new MatrixMultiplikator();
        this.inverseMatrix = new InverseMatrix();
        this.stufenform = new Stufenform(new Hilfe());
        this.diagonalform = new Diagonalform(new Hilfe());
        this.transponierung = new Transponierung();
    }

    @Override
    public double[][] getMatrix() {
        return matrix;
    }

    @Override
    public int getAnzahlSpalten() {
        return matrix[0].length;
    }

    @Override
    public int getAnzahlZeilen() {
        return matrix.length;
    }
    @Override
    public double getDeterminante() {
        return determinantenRechner.getDeterminante(this);
    }

    @Override
    public IMatrix multipliziere(IMatrix m) {
        return matrixMultiplikator.multipliziere(this, m);
    }

    @Override
    public IMatrix multipliziere(double skalar) {
        return matrixMultiplikator.multipliziere(this, skalar);
    }

    @Override
    public IMatrix transponiere() {
        return transponierung.transponiere(this);
    }

    @Override
    public IMatrix getInverseMatrix() {
        return inverseMatrix.getInverseMatrix(this);
    }

    @Override
    public IMatrix getStufenForm() {
        return stufenform.formeMatrixInStufenformUm(this);
    }

    @Override
    public IMatrix getDiagonalForm() {
        return diagonalform.formeMatrixInDiagonalFormUm(this);
    }

    @Override
    public IMatrix subtrahiere(IMatrix matrix) {
        return new MatrixSubtraktion().subtrahiere(this, matrix);
    }

    @Override
    public String toString() {
        return Arrays.stream(matrix)
                .map(row -> Arrays.toString(row) + "\n")
                .collect(Collectors.joining());
    }
}
