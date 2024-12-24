package Zahlenerkennung;

import Zahlenerkennung.ZahlenErkennungsNetz.NumberNeuralNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.InitializeNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.SaveReadWeights;

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

        int i = 0;
        String returnString = "";

        for (double learningRate = 0.1; learningRate <= 1; learningRate += 0.1) {

            String fileName = "weights%d".formatted(i);

            InitializeNetwork initializeNetwork = new InitializeNetwork();
            initializeNetwork.initializeMatrix(fileName);
            NumberNeuralNetwork n = new NumberNeuralNetwork(fileName);

            double successRate = n.trainAndTestNetwork(20, learningRate, fileName);

            returnString += learningRate;
            returnString += " with successRate: ";
            returnString += successRate;
            returnString += "\n";

            i++;
        }

        SaveReadWeights saveReadWeights = new SaveReadWeights();
        saveReadWeights.write(returnString);

        /*
        SaveReadWeights saveReadWeights = new SaveReadWeights();

        NumberNeuralNetwork network = new NumberNeuralNetwork("weights1");
        NumberNeuralNetwork network3 = new NumberNeuralNetwork("weights3");
        NumberNeuralNetwork network5 = new NumberNeuralNetwork("weights5");

        System.out.println("Initialized network correctly");

        Picture[] pictures = network.readPictures();

        System.out.println("network 1\n");
        network.testNeuralNetwork(pictures);


        System.out.println("network 3\n");
        network3.testNeuralNetwork(pictures);


        System.out.println("network 5\n");
        network5.testNeuralNetwork(pictures);
        */
    }
}
