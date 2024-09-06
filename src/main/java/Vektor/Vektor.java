package Vektor;


import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Vektor implements IVektor {

    private final double[] vektorWerte;

    public Vektor(double[] werte) {
        this.vektorWerte = werte;
    }

    public Vektor(Vektor v) {
        this.vektorWerte = Arrays.copyOf(v.vektorWerte, v.vektorWerte.length);
    }

    @Override
    public double[] getVektor() {
        return vektorWerte;
    }

    @Override
    public IVektor multipliziere(IMatrix matrix) {

        IMatrix vektorAlsMatrix = transformiereVektorInMatrix();
        IMatrix neueMatrix = matrix.multipliziere(vektorAlsMatrix);

        return transfromiereMatrixInVektor(neueMatrix);
    }

    public IVektor subtrahiere(IVektor v2) {
        double[] werteVonV2 = v2.getVektor();
        double[] differenz = new double[werteVonV2.length];

        for (int i = 0; i < werteVonV2.length; i++) {
            differenz[i] = werteVonV2[i] - vektorWerte[i];
        }

        return new Vektor(differenz);
    }

    private IVektor transfromiereMatrixInVektor(IMatrix neueMatrix) {
        double[][] array = neueMatrix.getMatrix();
        double[] vektor = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            vektor[i] = array[i][0];
        }

        return new Vektor(vektor);
    }

    private IMatrix transformiereVektorInMatrix() {
        double[][] vektorAlsArray = new double[vektorWerte.length][1];

        for (int i = 0; i < vektorWerte.length; i++) {
            vektorAlsArray[i][0] = vektorWerte[i];
        }

        return new Matrix(vektorAlsArray);
    }

    @Override
    public String toString() {
        return Arrays.stream(vektorWerte)
                .mapToObj(value -> Double.toString(value) + "\n")
                .collect(Collectors.joining());
    }
}
