package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import Vektor.IVektor;
import Vektor.Vektor;

public class LayerConnection {

    private IMatrix weightMatrix;
    private final IMatrix transposedWeightMatrix;
    private final IActivationFunction sigmoid;
    private IVektor outputOfThisLayer;
    private IVektor error;
    private IVektor inputFromPrevLayerWithoutSigmoid;

    public LayerConnection(double[][] weights, IActivationFunction activationFunction) {
        weightMatrix = new Matrix(weights);
        transposedWeightMatrix = weightMatrix.transponiere();
        sigmoid = activationFunction;
    }

    public IMatrix getWeightMatrix() {
        return weightMatrix;
    }

    public IVektor getError() {
        return error;
    }

    public IVektor calculateOutputVector(IVektor inputVektor) {

        IVektor outputVector = inputVektor.multipliziere(weightMatrix);
        inputFromPrevLayerWithoutSigmoid = inputVektor;
        outputOfThisLayer = outputVector;

        return outputVector;
    }

    public IMatrix improveWeights() {

        IMatrix transposedError = inputFromPrevLayerWithoutSigmoid.transformiereVektorInMatrix();
        IMatrix transposedVector = transposedError.transponiere();

        double[] v = outputOfThisLayer.getVektor();
        double[] changeArray = new double[v.length];

        for (int i = 0; i < v.length; i++) {
            double value = v[i];
            double errorValue = error.getVektor()[i];

            double newValue = calculateDerivation(errorValue, value);
            changeArray[i] = newValue;
        }

        IVektor changeVector = new Vektor(changeArray);
        IMatrix changeVectorTransformedToMatrix = changeVector.transformiereVektorInMatrix();
        IMatrix changeMatrix = changeVectorTransformedToMatrix.multipliziere(transposedVector);

        weightMatrix = weightMatrix.subtrahiere(changeMatrix);

        return weightMatrix;
    }

    public IVektor backPropagateError(IVektor nextLayerOutputVector) {
        error = nextLayerOutputVector.multipliziere(transposedWeightMatrix);
        return error;
    }

    protected double calculateDerivation(double error, double input) {
        double sigmoidOfInput = sigmoid.function(input);
        return error * sigmoidOfInput * (1 - sigmoidOfInput);
    }
}
