package Zahlenerkennung.NeuronalesNetz;

import Matrizen.IMatrix;
import Vektor.IVektor;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.LayerConnection;

import java.util.List;

public interface INeuralNetwork {

    IVektor calculate(IVektor vector);
    List<IMatrix> train(IVektor vector, IVektor expectedVector, int numberOfSessions, double learningRate);
    List<LayerConnection> getLayersConnections();
}
