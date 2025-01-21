package number_detection;

import number_detection.model.Picture;
import number_detection.number_detection_net.NumberNeuralNetwork;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> ints = List.of(784, 128, 56, 10);
        NumberNeuralNetwork neuralNetwork = new NumberNeuralNetwork(ints, "initializedWeights");
        Picture[] arr = {};
        double successRate = neuralNetwork.trainAndTestNetwork(
                arr, 20, 0.01, "weightsAfterTraining");
        System.out.println(successRate);
    }

    private static StringBuilder testLearnRates() {
        int i = 0;
        StringBuilder returnString = new StringBuilder();

        for (double learningRate = 0.1; learningRate <= 1; learningRate += 0.1) {

            String fileName = "weights%d".formatted(i);

            NumberNeuralNetwork n = new NumberNeuralNetwork(List.of(784, 128, 56, 10), fileName);

            double successRate = n.trainAndTestNetwork(20, learningRate, fileName);

            returnString.append(learningRate);
            returnString.append(" with successRate: ");
            returnString.append(successRate);
            returnString.append("\n");

            i++;
        }
        return returnString;
    }
}
