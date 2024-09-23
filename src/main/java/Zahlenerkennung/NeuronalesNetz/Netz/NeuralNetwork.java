package Zahlenerkennung.NeuronalesNetz.Netz;

import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.IActivationFunction;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.LayerConnection;

import java.util.Arrays;
import java.util.List;

public class NeuralNetwork implements INeuralNetwork {

    private final List<LayerConnection> weights;
    private final IActivationFunction activationFunction;

    public NeuralNetwork(List<double[][]> weights, IActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.weights = weights.stream()
                .map(weight -> new LayerConnection(weight, activationFunction))
                .toList();
    }

    @Override
    public void train(IVektor inputVector, IVektor expectedVector, int numberOfSessions, double learningRate) {

        for (int i = 0; i < numberOfSessions; i++) {

            IVektor outputVector = calculate(inputVector);
            IVektor squaredDifference = calculateSquaredDifference(expectedVector, outputVector);
            IVektor errorVector = backPropagate(squaredDifference);

            for (LayerConnection connection : weights) {
                connection.improveWeights(learningRate);
            }
        }

        System.out.println(calculate(inputVector));

    }

    @Override
    public IVektor calculate(IVektor inputVector) {
        IVektor nextOutputVector = inputVector;

        for (LayerConnection layer : weights) {
            nextOutputVector = layer.calculateOutputVector(nextOutputVector);
            nextOutputVector = new Vektor(
                    Arrays.stream(nextOutputVector.getVektor())
                            .map(activationFunction::function)
                            .toArray()
            );
        }

        return nextOutputVector;
    }

    public IVektor backPropagate(IVektor outputVector) {
        IVektor errorVector = outputVector;

        for (LayerConnection connection : weights) {
            errorVector = connection.backPropagateError(errorVector);
        }

        return errorVector;
    }

    private IVektor calculateSquaredDifference(IVektor expectedVector, IVektor outputVector) {
        IVektor diff = expectedVector.subtrahiere(outputVector);
        return new Vektor(
                Arrays.stream(diff.getVektor())
                        .map(j -> Math.pow(j, 2))
                        .toArray()
        );
    }
}
