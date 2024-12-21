package Zahlenerkennung.ZahlenErkennungsNetz;

import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.SaveWeights;

public class NumberNeuralNetwork {

    private final INeuralNetwork neuralNetwork;
    private final SaveWeights saveWeights = new SaveWeights();


    public NumberNeuralNetwork(INeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void learn() {
        saveWeights.saveWeights((NeuralNetwork) neuralNetwork);
    }
}
