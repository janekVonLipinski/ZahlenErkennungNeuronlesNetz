package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import Vektor.IVektor;
import Vektor.Vektor;

import java.util.Arrays;

public class LayerConnection {

    private IMatrix weightMatrix;
    private IMatrix transposedWeightMatrix;
    private final IActivationFunction sigmoid;
    private IVektor outputOfThisLayerWithoutSigmoid;
    private IVektor error;
    private IVektor inputFromPrevLayer;

    public LayerConnection(double[][] weights, IActivationFunction activationFunction) {
        weightMatrix = new Matrix(weights);
        transposedWeightMatrix = weightMatrix.transponiere();
        sigmoid = activationFunction;
    }

    public LayerConnection(IMatrix weights, IActivationFunction activationFunction) {
        weightMatrix = weights;
        transposedWeightMatrix = weightMatrix.transponiere();
        sigmoid = activationFunction;
    }

    public IMatrix getWeightMatrix() {
        return weightMatrix;
    }

    public IVektor calculateOutputVector(IVektor inputVektor) {

        IVektor outputVector = new Vektor(
                Arrays.stream(inputVektor.multipliziere(weightMatrix).getVektor())
//                .map(sigmoid::function)
                .toArray()
        );

        inputFromPrevLayer = inputVektor;
        outputOfThisLayerWithoutSigmoid = outputVector;

        return outputVector;
    }

    public IVektor backPropagateError(IVektor nextLayerOutputVector) {
        error = nextLayerOutputVector;
        return error.multipliziere(transposedWeightMatrix);
    }

    public IMatrix improveWeights(double learningRate) {

        IMatrix input = inputFromPrevLayer.transformiereVektorInMatrix();
        IMatrix transposedInputVector = input.transponiere();
        IMatrix change = calculateErrorVector(learningRate);
        IMatrix changeMatrix = change.multipliziere(transposedInputVector);

        weightMatrix = weightMatrix.subtrahiere(changeMatrix);
        transposedWeightMatrix = weightMatrix.transponiere();
        return weightMatrix;
    }

    private IMatrix calculateErrorVector(double learningRate) {

        double[] outputFromThisLayerArray = outputOfThisLayerWithoutSigmoid.getVektor();
        double[] errorVector = error.getVektor();
        double[] changeArray = new double[outputFromThisLayerArray.length];

        for (int i = 0; i < outputFromThisLayerArray.length; i++) {
            double value = outputFromThisLayerArray[i];
            double newValue = calculateDerivation(errorVector[i], value);
            changeArray[i] = newValue * learningRate;
        }

        IVektor changeVector = new Vektor(changeArray);
        return changeVector.transformiereVektorInMatrix();
    }

    private double calculateDerivation(double error, double output) {
        return error * sigmoid.function(output) * sigmoid.function(1 - output);
    }
}
