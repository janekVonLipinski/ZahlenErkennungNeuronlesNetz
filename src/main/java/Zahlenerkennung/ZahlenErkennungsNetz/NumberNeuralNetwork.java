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


    public void trainAndTestNetwork(int numberOfIterations) {

        System.out.println("reading pictures");
        Picture[] pictures = {};

        try {
            pictures = inputReader.getImages(LABEL_PATH, IMAGE_PATH);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        for (int i = 1; i < numberOfIterations; i++) {

            System.out.println("training network");
            learn(pictures, i);

            System.out.println("evaluating network");
            testNeuralNetwork(pictures);
        }
    }

    public void learn(Picture[] pictures, int i) {
        String fileNameOfCurrentFile = "weights%d".formatted(i - 1);
        String fileNameOfNextFile = "weights%d".formatted(i);
        System.out.println("reading weights");

        List<IMatrix> weights = saveReadWeights.readMatrix(fileNameOfCurrentFile);
        neuralNetwork = new NeuralNetwork(weights);

        for (Picture picture : pictures) {
            trainNetworkWith(picture);
        }

        System.out.println("saving weights");

        saveReadWeights.saveWeights(neuralNetwork, fileNameOfNextFile);
    }

    public void testNeuralNetwork(Picture[] pictures) {

        System.out.println("evaluating network");

        double count = 0;
        double good = 0;

        for (Picture picture : pictures) {

            IVektor pictureVector = convertPictureToVector(picture);
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

        System.out.println("percentage detected correctly: " + good/count);
    }

    private void trainNetworkWith(Picture picture) {

        IVektor labelVector = convertLabelToVector(picture);
        IVektor pictureVector = convertPictureToVector(picture);
        neuralNetwork.train(pictureVector, labelVector, 1, 1.0);
    }

    private IVektor convertLabelToVector(Picture picture) {

        int numberOfNumbers = 10; //most based name, i ever came up with
        double[] numbers = new double[numberOfNumbers];
        int label = picture.getLabel();
        numbers[label] = 1;

        return new Vektor(numbers);
    }

    private IVektor convertPictureToVector(Picture picture) {
        double[] pictureOfDoubles = Arrays.stream(picture.getPixel())
                .mapToDouble(i -> i / 255.0)
                .toArray();


        return new Vektor(pictureOfDoubles);
    }
}
