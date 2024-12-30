package number_detection.mathematical_neural_net.Netz.NeuralNetworkParts;

import number_detection.number_detection_net.mathematical_neural_net.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;
import number_detection.number_detection_net.mathematical_neural_net.Netz.NeuralNetworkParts.LayerConnection;

class LayerConnectionTest {

    private final double[][] weights = {{}};
    private final LayerConnection connection = new LayerConnection(weights , new SigmoidFunction());

}