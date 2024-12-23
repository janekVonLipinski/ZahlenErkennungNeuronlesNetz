package Zahlenerkennung.NeuronalesNetz;

import Vektor.IVektor;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.LayerConnection;

import java.util.List;

public interface INeuralNetwork {

    IVektor calculate(IVektor vector);
    void train(IVektor vector, IVektor expectedVector, int numberOfSessions, double learningRate);
    List<LayerConnection> getLayersConnections();
}
