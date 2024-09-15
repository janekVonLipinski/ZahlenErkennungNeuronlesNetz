package Zahlenerkennung;

import Matrizen.IMatrix;
import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        double[] input = {0.9, 0.1, 0.8};
        double[][] firstConnection = {{0.9, 0.3, 0.4}, {0.2, 0.8, 0.2}, {0.1, 0.5, 0.6}};
        double[][] secondConnection = {{0.3, 0.7, 0.5}, {0.6, 0.5, 0.2}, {0.8, 0.1, 0.9}};

        double[] expectedVector = {0.726, 0.708, 0.778};

        NeuralNetwork neuralNetwork = new NeuralNetwork(
                List.of(firstConnection, secondConnection), new SigmoidFunction()
        );

        IVektor result = neuralNetwork.calculate(new Vektor(input));

        System.out.println("ein toller test");

        neuralNetwork.backPropagate(result);
        IMatrix matrix = neuralNetwork.getWeights().get(0).improveWeights();
        System.out.println(matrix);
    }
}
