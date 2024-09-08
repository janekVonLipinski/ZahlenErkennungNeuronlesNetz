package Zahlenerkennung.NeuronalesNetz.Netz;

import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.LayerConnection;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.IActivationFunction;

import java.util.Arrays;
import java.util.List;

public class NeuralNetwork implements INeuralNetwork {

    private final List<LayerConnection> weights;
    private final IActivationFunction activationFunction;

    public NeuralNetwork(List<double[][]> weights, IActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.weights = weights.stream()
                .map(LayerConnection::new)
                .toList();
    }

    @Override
    public void save() {

    }

    @Override
    public void readWeights() {

    }

    @Override
    public void train(IVektor inputVector) {
        IVektor outputVector = calculate(inputVector);
        IVektor errorVector = backPropagate(outputVector);

        weights.forEach(i -> System.out.println(i.getError()));
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

    protected IVektor backPropagate(IVektor outputVector) {
        IVektor errorVector = outputVector;

        for (LayerConnection connection : weights) {
            errorVector = connection.backPropagateError(errorVector);
        }

        return errorVector;
    }
}