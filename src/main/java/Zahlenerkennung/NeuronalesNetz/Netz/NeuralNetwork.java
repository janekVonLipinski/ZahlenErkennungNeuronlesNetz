package Zahlenerkennung.NeuronalesNetz.Netz;

import Matrizen.IMatrix;
import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.IActivationFunction;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.LayerConnection;

import java.util.Arrays;
import java.util.List;

public class NeuralNetwork implements INeuralNetwork {

    private final List<LayerConnection> layersConnections;
    private final IActivationFunction activationFunction;

    public NeuralNetwork(List<double[][]> weights, IActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.layersConnections = weights.stream()
                .map(weight -> new LayerConnection(weight, activationFunction))
                .toList();
    }

    public NeuralNetwork(List<IMatrix> weights) {
        this.activationFunction = new SigmoidFunction();
        this.layersConnections = weights.stream()
                .map(weight -> new LayerConnection(weight, activationFunction))
                .toList();
    }

    @Override
    public List<IMatrix> train(IVektor inputVector, IVektor expectedVector, int numberOfIterations, double learningRate) {

        for (int i = 0; i < numberOfIterations; i++) {

            IVektor outputVector = calculate(inputVector);
            IVektor squaredDifference = calculateSquaredDifference(expectedVector, outputVector);
            backPropagate(squaredDifference);

            for (LayerConnection connection : layersConnections) {
                connection.improveWeights(learningRate);
            }
        }

        return layersConnections.stream()
                .map(LayerConnection::getWeightMatrix)
                .toList();
    }

    @Override
    public IVektor calculate(IVektor inputVector) {
        IVektor nextOutputVector = inputVector;

        for (LayerConnection layer : layersConnections) {
            nextOutputVector = layer.calculateOutputVector(nextOutputVector);
            nextOutputVector = new Vektor(Arrays.stream(nextOutputVector.getVektor())
                    .map(activationFunction::function)
                    .toArray()
            );
        }

        return nextOutputVector;
    }

    public IVektor backPropagate(IVektor outputVector) {
        IVektor errorVector = outputVector;

        for (int i = layersConnections.size() - 1; i >= 0; i--) {
            LayerConnection connection = layersConnections.get(i);
            errorVector = connection.backPropagateError(errorVector);
        }

        return errorVector;
    }

    private IVektor calculateSquaredDifference(IVektor expectedVector, IVektor outputVector) {
        IVektor diff = expectedVector.subtrahiere(outputVector);
        return new Vektor(
                Arrays.stream(diff.getVektor())
//                        .map(j -> j * j)
                        .toArray()
        );
    }

    @Override
    public List<LayerConnection> getLayersConnections() {
        return layersConnections;
    }
}
