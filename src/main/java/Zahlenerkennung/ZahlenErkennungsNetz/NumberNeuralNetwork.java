package Zahlenerkennung.ZahlenErkennungsNetz;

import Matrizen.IMatrix;
import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.Model.Picture;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
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
    private INeuralNetwork neuralNetwork;


    public NumberNeuralNetwork(INeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public NumberNeuralNetwork(String fileName) {
        List<IMatrix> weights = saveReadWeights.readMatrix(fileName);
        neuralNetwork = new NeuralNetwork(weights);
    }

    public double trainAndTestNetwork(int numberOfIterations, double learningRate, String fileName) {

        System.out.println("reading pictures");
        Picture[] pictures = readPictures();

        double successRate = 0;

        for (int i = 1; i < numberOfIterations; i++) {

            System.out.println("training network");
            learn(pictures, learningRate, fileName);

            System.out.println("evaluating network");
            successRate = testNeuralNetwork(pictures);
        }

        return successRate;
    }

    public void learn(Picture[] pictures, double learningRate, String fileName) {

        System.out.println("reading weights");

        List<IMatrix> weights = saveReadWeights.readMatrix(fileName);
        neuralNetwork = new NeuralNetwork(weights);

        for (Picture picture : pictures) {
            trainNetworkWith(picture, learningRate);
        }

        System.out.println("saving weights");

        saveReadWeights.saveWeights(neuralNetwork, fileName);
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

    public double testNeuralNetwork(Picture[] pictures) {

        System.out.println("evaluating network");

        double count = 0;
        double good = 0;

        for (Picture picture : pictures) {

            IVektor pictureVector = convertPictureToVectorAndNormalize(picture);
            IVektor outPutCalculatedByNeuralNetwork = neuralNetwork.calculate(pictureVector);
            double[] values = outPutCalculatedByNeuralNetwork.getVektor();

            double highest = 0;
            int index = -1;

            for (int i = 0; i < values.length; i++) {

                double value = values[i];

                if (value > highest) {
                    highest = value;
                    index = i;
                }
            }

            if (index == picture.getLabel()) {
                good++;
            }
            count++;
        }

        return good / count;
    }

    private void trainNetworkWith(Picture picture, double learningRate) {

        IVektor labelVector = convertLabelToVector(picture);
        IVektor pictureVector = convertPictureToVectorAndNormalize(picture);
        neuralNetwork.train(pictureVector, labelVector, 1, learningRate);
    }

    private IVektor convertLabelToVector(Picture picture) {

        int numberOfNumbers = 10; //most based name, i ever came up with
        double[] numbers = new double[numberOfNumbers];
        int label = picture.getLabel();
        numbers[label] = 1;

        return new Vektor(numbers);
    }

    private IVektor convertPictureToVectorAndNormalize(Picture picture) {
        double[] pictureOfDoubles = Arrays.stream(picture.getPixel())
                .mapToDouble(i -> i / 255.0)
                .toArray();

        return new Vektor(pictureOfDoubles);
    }
}
