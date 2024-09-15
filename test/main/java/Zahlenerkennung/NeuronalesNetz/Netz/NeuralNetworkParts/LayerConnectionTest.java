package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts;

import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;

class LayerConnectionTest {

    private final double[][] weights = {{}};
    private final LayerConnection connection = new LayerConnection(weights , new SigmoidFunction());

}