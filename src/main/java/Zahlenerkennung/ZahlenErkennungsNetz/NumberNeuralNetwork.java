package Zahlenerkennung.ZahlenErkennungsNetz;

import Matrizen.IMatrix;
import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.Model.Picture;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.InitializeNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.SaveReadWeights;
import Zahlenerkennung.reader.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class NumberNeuralNetwork {

    private static final String LABEL_PATH = "ZahlenErkennungNeuronlesNetz/src/main/resources/train-labels.idx1-ubyte";
    private static final String IMAGE_PATH = "ZahlenErkennungNeuronlesNetz/src/main/resources/train-images.idx3-ubyte";
    private final InputReader inputReader = new InputReader();
    private final SaveReadWeights saveReadWeights = new SaveReadWeights();
    private final INeuralNetwork neuralNetwork;


    public NumberNeuralNetwork(INeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public NumberNeuralNetwork(String fileName) {
        List<IMatrix> weights = saveReadWeights.readMatrix(fileName);
        this.neuralNetwork = new NeuralNetwork(weights);
    }

    public NumberNeuralNetwork(List<Integer> neuronsPerLayer, String fileName) {

        InitializeNetwork initializeNetwork = new InitializeNetwork();
        List<IMatrix> weights = initializeNetwork.initializeMatrices(fileName, neuronsPerLayer);
        this.neuralNetwork = new NeuralNetwork(weights);
    }

    public double trainAndTestNetwork(int numberOfIterations, double learningRate, String fileName) {

        Picture[] pictures = readPictures();
        trainNetworkOverIterations(numberOfIterations, learningRate, pictures);
        double successRate = testNeuralNetwork(pictures);
        saveReadWeights.saveWeights(neuralNetwork, fileName);

        return successRate;
    }

    private void trainNetworkOverIterations(int numberOfIterations, double learningRate, Picture[] pictures) {

        for (int i = 1; i < numberOfIterations; i++) {
            learn(pictures, learningRate);
        }
    }

    public Picture[] readPictures() {

        Picture[] pictures = {};

        try {
            pictures = inputReader.getImages(LABEL_PATH, IMAGE_PATH);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return pictures;
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
