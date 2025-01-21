package number_detection.number_detection_net;

import Matrizen.IMatrix;
import Vektor.IVektor;
import Vektor.Vektor;
import number_detection.model.Picture;
import number_detection.number_detection_net.mathematical_neural_net.INeuralNetwork;
import number_detection.number_detection_net.mathematical_neural_net.Netz.NeuralNetwork;
import number_detection.number_detection_net.save_read_weights.InitializeNetwork;
import number_detection.number_detection_net.save_read_weights.SaveReadWeights;

import java.util.Arrays;
import java.util.List;

public class NumberNeuralNetwork {

    private final SaveReadWeights saveReadWeights = new SaveReadWeights();
    private final INeuralNetwork neuralNetwork;
    private final PictureInput pictureInput = new PictureInput();


    public NumberNeuralNetwork(String fileName) {
        List<IMatrix> weights = saveReadWeights.readMatrix(fileName);
        this.neuralNetwork = new NeuralNetwork(weights);
    }

    public NumberNeuralNetwork(List<Integer> neuronsPerLayer, String fileName) {

        InitializeNetwork initializeNetwork = new InitializeNetwork();
        List<IMatrix> weights = initializeNetwork.initializeMatrices(fileName, neuronsPerLayer);
        this.neuralNetwork = new NeuralNetwork(weights);
    }

    public void trainAndTestNetwork(int numberOfIterations, double learningRate, String fileName, String resultLabel) {

        Picture[] trainPictures = pictureInput.getTrainingImages();
        Picture[] evaluationPictures = pictureInput.getEvaluationImages();

        String evaluationString = trainNetworkOverIterations(numberOfIterations, learningRate, trainPictures, evaluationPictures);
        saveReadWeights.saveWeights(neuralNetwork, fileName);
        saveReadWeights.write(evaluationString, resultLabel);
    }

    private String trainNetworkOverIterations(int numberOfIterations, double learningRate, Picture[] pictures, Picture[] evaluationPictures) {

        StringBuilder evaluationString = new StringBuilder();

        for (int i = 1; i < numberOfIterations; i++) {
            learn(pictures, learningRate);
            double successRate = testNeuralNetwork(evaluationPictures);
            evaluationString.append(successRate).append("\n");
        }

        return evaluationString.toString();
    }

    public void learn(Picture[] pictures, double learningRate) {

        for (Picture picture : pictures) {
            trainNetworkWith(picture, learningRate);
        }
    }

    public double testNeuralNetwork(Picture[] pictures) {

        double totalEvaluatedNumbers = 0;
        double correctlyDetectedNumbers = 0;

        for (Picture picture : pictures) {

            IVektor pictureVector = convertPictureToVectorAndNormalize(picture);
            IVektor outPutCalculatedByNeuralNetwork = neuralNetwork.calculate(pictureVector);
            double[] values = outPutCalculatedByNeuralNetwork.getVektor();

            int detectedNumber = getBestFit(values);

            if (detectedNumber == picture.getLabel()) {
                correctlyDetectedNumbers++;
            }

            System.out.println("output of the net for input is");
            System.out.println(outPutCalculatedByNeuralNetwork);

            totalEvaluatedNumbers++;
        }

        return correctlyDetectedNumbers / totalEvaluatedNumbers;
    }

    private int getBestFit(double[] values) {

        double highest = 0;
        int index = -1;

        for (int i = 0; i < values.length; i++) {

            double value = values[i];

            if (value > highest) {
                highest = value;
                index = i;
            }
        }

        return index;
    }

    private void trainNetworkWith(Picture picture, double learningRate) {

        IVektor labelVector = convertLabelToVector(picture);
        IVektor pictureVector = convertPictureToVectorAndNormalize(picture);
        neuralNetwork.train(pictureVector, labelVector, 1, learningRate);
    }

    private IVektor convertLabelToVector(Picture picture) {

        int numberOfNumbers = 10; //most based name, I ever came up with
        double[] numbers = new double[numberOfNumbers];
        int label = picture.getLabel();
        numbers[label] = 1;

        return new Vektor(numbers);
    }

    private IVektor convertPictureToVectorAndNormalize(Picture picture) {
        double[] normalizedPictures = Arrays.stream(picture.getPixel())
                .mapToDouble(i -> i / 255.0)
                .toArray();

        return new Vektor(normalizedPictures);
    }
}
