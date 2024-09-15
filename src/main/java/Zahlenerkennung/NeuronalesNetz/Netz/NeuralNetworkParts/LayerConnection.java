package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import Vektor.IVektor;

public class LayerConnection {

    private final IMatrix weightMatrix;
    private final IMatrix transposedWeightMatrix;
    private final IActivationFunction sigmoid;
    private IVektor error;
    private IVektor inputFromPrevLayer;

    public LayerConnection(double[][] weights, IActivationFunction activationFunction) {
        weightMatrix = new Matrix(weights);
        transposedWeightMatrix = weightMatrix.transponiere();
        sigmoid = activationFunction;
    }

    public IVektor getError() {
        return error;
    }

    public IVektor calculateOutputVector(IVektor inputVektor) {
        return inputVektor.multipliziere(weightMatrix);
    }

    public void improveNeuron() {
        IMatrix matrix = error.transformiereVektorInMatrix();
        System.out.println(matrix);
    }

    public IVektor backPropagateError(IVektor nextLayerOutputVector) {
        error = nextLayerOutputVector.multipliziere(transposedWeightMatrix);
        return error;
    }

    protected double calculateDerivation(double error, double input, double inputPrev) {
        double sigmoidOfInput = sigmoid.function(input);
        return -error * sigmoidOfInput * (1 - sigmoidOfInput) * inputPrev;
    }
}
