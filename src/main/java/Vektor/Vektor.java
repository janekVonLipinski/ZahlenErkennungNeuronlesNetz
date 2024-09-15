package Vektor;


import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Vektor implements IVektor {

    private final double[] vektor;

    public Vektor(double[] werte) {
        this.vektor = werte;
    }

    public Vektor(Vektor v) {
        this.vektor = Arrays.copyOf(v.vektor, v.vektor.length);
    }

    @Override
    public double[] getVektor() {
        return vektor;
    }

    @Override
    public IVektor multipliziere(IMatrix matrix) {

        IMatrix vektorAlsMatrix = transformiereVektorInMatrix();
        IMatrix neueMatrix = matrix.multipliziere(vektorAlsMatrix);

        return transfromiereMatrixInVektor(neueMatrix);
    }

    @Override
    public IMatrix transformiereVektorInMatrix() {
        double[][] vektorAlsArray = new double[vektor.length][1];

        for (int i = 0; i < vektor.length; i++) {
            vektorAlsArray[i][0] = vektor[i];
        }

        return new Matrix(vektorAlsArray);
    }

    @Override
    public IVektor subtrahiere(IVektor v2) {
        double[] werteVonV2 = v2.getVektor();
        double[] differenz = new double[werteVonV2.length];

        for (int i = 0; i < werteVonV2.length; i++) {
            differenz[i] = werteVonV2[i] - vektor[i];
        }

        return new Vektor(differenz);
    }

    @Override
    public double calculateSumOfElements() {
        double sum = 0;

        for (double element : vektor) {
            sum += element;
        }

        return sum;
    }

    private IVektor transfromiereMatrixInVektor(IMatrix neueMatrix) {
        double[][] array = neueMatrix.getMatrix();
        double[] vektor = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            vektor[i] = array[i][0];
        }

        return new Vektor(vektor);
    }

    @Override
    public String toString() {
        return Arrays.stream(vektor)
                .mapToObj(value -> Double.toString(value) + "\n")
                .collect(Collectors.joining());
    }
}
