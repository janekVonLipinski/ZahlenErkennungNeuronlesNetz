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
    public IVektor getError() {
        return error;
    }

    public IVektor calculateOutputVector(IVektor inputVektor) {

        IVektor outputVector = inputVektor.multipliziere(weightMatrix);
        inputFromPrevLayerWithoutSigmoid = inputVektor;
        outputOfThisLayer = outputVector;

        return outputVector;
    }

    public IVektor backPropagateError(IVektor nextLayerOutputVector) {
        error = nextLayerOutputVector.multipliziere(transposedWeightMatrix);
        return error;
    }

    public IMatrix improveWeights(double learningRate) {

        IMatrix transposedError = inputFromPrevLayerWithoutSigmoid.transformiereVektorInMatrix();
        IMatrix transposedVector = transposedError.transponiere();
        IMatrix change = calculateChangeMatrix(learningRate);

        IMatrix changeMatrix = change.multipliziere(transposedVector);

        weightMatrix = weightMatrix.subtrahiere(changeMatrix);

        return weightMatrix;
    }

    private IMatrix calculateChangeMatrix(double learningRate) {
        double[] v = outputOfThisLayer.getVektor();
        double[] changeArray = new double[v.length];

        for (int i = 0; i < v.length; i++) {
            double value = v[i];
            double errorValue = error.getVektor()[i];
            double newValue = calculateDerivation(errorValue, value, learningRate);
            changeArray[i] = newValue;
        }

        IVektor changeVector = new Vektor(changeArray);
        return changeVector.transformiereVektorInMatrix();
    }

    private double calculateDerivation(double error, double input, double learningRate) {
        double sigmoidOfInput = sigmoid.function(input);
        return learningRate * error * sigmoidOfInput * (1 - sigmoidOfInput);
    }
}
