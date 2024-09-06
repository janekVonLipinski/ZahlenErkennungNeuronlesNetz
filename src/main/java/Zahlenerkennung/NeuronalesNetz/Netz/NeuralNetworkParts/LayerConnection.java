package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import Vektor.IVektor;

public class LayerConnection {

    private final IMatrix weightMatrix;

    public LayerConnection(double[][] weights) {
        weightMatrix = new Matrix(weights);
    }

    public IVektor calculateOutputVector(IVektor inputVektor) {
        return inputVektor.multipliziere(weightMatrix);
    }
}
