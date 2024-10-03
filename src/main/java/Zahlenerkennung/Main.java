package Zahlenerkennung;

import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String PATH = "C:\\Users\\Admin\\Desktop\\java\\netz\\netz\\ZahlenErkennungNeuronlesNetz\\src\\main\\resources\\";

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

        try {
            neuralNetwork.writeWeightsToFile(PATH + "coolFile.txt");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
