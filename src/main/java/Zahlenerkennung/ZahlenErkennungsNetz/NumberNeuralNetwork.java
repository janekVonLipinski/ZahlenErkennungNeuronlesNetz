package Zahlenerkennung.ZahlenErkennungsNetz;

import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.SaveReadWeights;

public class NumberNeuralNetwork {

    private final INeuralNetwork neuralNetwork;
    private final SaveReadWeights saveWeights = new SaveReadWeights();


    public NumberNeuralNetwork(INeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void learn() {
        saveWeights.saveWeights((NeuralNetwork) neuralNetwork);
    }
}
