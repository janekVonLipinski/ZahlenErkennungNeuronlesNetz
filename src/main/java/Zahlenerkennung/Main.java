package Zahlenerkennung;

import Zahlenerkennung.ZahlenErkennungsNetz.NumberNeuralNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.InitializeNetwork;

public class Main {

    public static void main(String[] args) {
        /*
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

        //neuralNetwork.train(inputVektor, res, 100000,  1.0);
        */


        InitializeNetwork initializeNetwork = new InitializeNetwork();
        initializeNetwork.initializeMatrix();

        NumberNeuralNetwork n = new NumberNeuralNetwork();

        //n.trainAndTestNetwork(20);
    }
}
