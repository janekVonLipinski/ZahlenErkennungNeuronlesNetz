package Zahlenerkennung.NeuronalesNetz.Netz;

import Matrizen.IMatrix;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.LayerConnection;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.IActivationFunction;

import java.util.List;

public class NeuralNetwork {

    private final List<LayerConnection> connections;
    private final IActivationFunction activationFunction;

    public NeuralNetwork(List<LayerConnection> connections, IActivationFunction activationFunction) {
        this.connections = connections;
        this.activationFunction = activationFunction;
    }
}
