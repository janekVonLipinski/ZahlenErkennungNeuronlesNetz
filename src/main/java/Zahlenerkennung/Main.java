package Zahlenerkennung;

import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;
import Zahlenerkennung.ZahlenErkennungsNetz.NumberNeuralNetwork;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

        double[][] firstLayer = fillMatrix(128, 784);
        double[][] secondLayer = fillMatrix(56, 128);
        double[][] thirdLayer = fillMatrix(10, 56);

        NeuralNetwork neuralNetwork = new NeuralNetwork(
                List.of(firstLayer, secondLayer, thirdLayer),
                new SigmoidFunction()
        );


        NumberNeuralNetwork n = new NumberNeuralNetwork(neuralNetwork);
        n.learn();
    }


    private static double[][] fillMatrix(int x, int y) {
        Random random = new Random();
        double[][] arr = new double[x][y];
        for (double[] row : arr) {
            Arrays.fill(row, random.nextDouble(0, 1.0));
        }
        return arr;
    }
}
