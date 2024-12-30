package number_detection.number_detection_net.mathematical_neural_net;

import Matrizen.IMatrix;
import Vektor.IVektor;
import number_detection.number_detection_net.mathematical_neural_net.Netz.NeuralNetworkParts.LayerConnection;

import java.util.List;

public interface INeuralNetwork {

    IVektor calculate(IVektor vector);
    List<IMatrix> train(IVektor vector, IVektor expectedVector, int numberOfSessions, double learningRate);
    List<LayerConnection> getLayersConnections();
}
