package number_detection;

import number_detection.number_detection_net.NumberNeuralNetwork;

import java.sql.SQLOutput;
import java.util.List;

public class Main {

    private static final int DEFAULT_NUMBER_OF_GENERATIONS = 10;
    private static final List<Integer> DEFAULT_NETWORK_SIZE = List.of(784, 100, 10);
    private static final double DEFAULT_LEARNING_RATE = 0.01;


    public static void main(String[] args) {
        System.out.println("Starting");
        NumberNeuralNetwork neuralNetwork = new NumberNeuralNetwork(DEFAULT_NETWORK_SIZE, "network");
        neuralNetwork.trainAndTestNetwork(DEFAULT_NUMBER_OF_GENERATIONS, DEFAULT_LEARNING_RATE, "network", "network_result");
    }

}
