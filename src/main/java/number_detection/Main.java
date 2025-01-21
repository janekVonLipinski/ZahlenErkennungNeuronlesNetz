package number_detection;

import number_detection.number_detection_net.NumberNeuralNetwork;

import java.util.List;

public class Main {

    private static final int DEFAULT_NUMBER_OF_GENERATIONS = 10;
    private static final List<Integer> DEFAULT_NETWORK_SIZE = List.of(784, 100, 10);
    private static final double DEFAULT_LEARNING_RATE = 0.01;


    public static void main(String[] args) {

        NumberNeuralNetwork neuralNetwork;

        if (args.length > 0) {
            String file = args[0];
            neuralNetwork = new NumberNeuralNetwork(file);
        } else {
            neuralNetwork = new NumberNeuralNetwork(DEFAULT_NETWORK_SIZE, "network");
        }

        neuralNetwork.trainAndTestNetwork(DEFAULT_NUMBER_OF_GENERATIONS, DEFAULT_LEARNING_RATE, "network", "network_result");
    }

}
