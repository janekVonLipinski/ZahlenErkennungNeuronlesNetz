package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import Vektor.IVektor;

public class LayerConnection {

    private final IMatrix weightMatrix;
    private final IMatrix transposedWeightMatrix;
    private IVektor error;

    public LayerConnection(double[][] weights) {
        weightMatrix = new Matrix(weights);
        transposedWeightMatrix = weightMatrix.transponiere();
    }

    public IVektor getError() {
        return error;
    }

    public IVektor calculateOutputVector(IVektor inputVektor) {
        return inputVektor.multipliziere(weightMatrix);
    }

    public void improveNeuron() {

    }

    public IVektor backPropagateError(IVektor nextLayerOutputVector) {
        error = nextLayerOutputVector.multipliziere(transposedWeightMatrix);
        return error;
    }
}
