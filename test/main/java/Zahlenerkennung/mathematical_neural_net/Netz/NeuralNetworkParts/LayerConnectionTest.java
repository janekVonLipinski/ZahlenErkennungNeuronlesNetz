package Zahlenerkennung.mathematical_neural_net.Netz.NeuralNetworkParts;

import Zahlenerkennung.number_detection_net.mathematical_neural_net.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;
import Zahlenerkennung.number_detection_net.mathematical_neural_net.Netz.NeuralNetworkParts.LayerConnection;

class LayerConnectionTest {

    private final double[][] weights = {{}};
    private final LayerConnection connection = new LayerConnection(weights , new SigmoidFunction());

}