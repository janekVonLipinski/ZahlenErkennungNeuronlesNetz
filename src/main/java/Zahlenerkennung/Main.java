package Zahlenerkennung;

import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;
import Zahlenerkennung.ZahlenErkennungsNetz.NumberNeuralNetwork;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        double[] input = {0.9, 0.1, 0.8};
        double[][] firstConnection = {{0.9, 0.3, 0.4}, {0.2, 0.8, 0.2}, {0.1, 0.5, 0.6}};
        double[][] secondConnection = {{0.3, 0.7, 0.5}, {0.6, 0.5, 0.2}, {0.8, 0.1, 0.9}};

        IVektor inputVektor = new Vektor(input);

        NeuralNetwork neuralNetwork = new NeuralNetwork(
                List.of(firstConnection, secondConnection), new SigmoidFunction()
        );

        IVektor result = neuralNetwork.calculate(inputVektor);

        double[] expected = {0.5, 0.5, 0.5};
        IVektor res = new Vektor(expected);

        neuralNetwork.train(inputVektor, res, 100000,  1.0);


        NumberNeuralNetwork n = new NumberNeuralNetwork(neuralNetwork);
        n.learn();
    }
}
