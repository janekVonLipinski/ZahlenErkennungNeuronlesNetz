package Zahlenerkennung.ZahlenErkennungsNetz;

import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.Model.Picture;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.SaveReadWeights;
import Zahlenerkennung.reader.InputReader;

import java.io.IOException;
import java.util.Arrays;

public class NumberNeuralNetwork {

    private static final String LABEL_PATH = "ZahlenErkennungNeuronlesNetz/src/main/resources/train-labels.idx1-ubyte";
    private static final String IMAGE_PATH = "ZahlenErkennungNeuronlesNetz/src/main/resources/train-images.idx3-ubyte";
    private final InputReader inputReader = new InputReader();
    private final SaveReadWeights saveWeights = new SaveReadWeights();
    private final INeuralNetwork neuralNetwork;


    public NumberNeuralNetwork(INeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void learn() {
        Picture[] pictures = {};
        Picture picture = null;


        try {
            //pictures = inputReader.getImages(LABEL_PATH, IMAGE_PATH);
            picture = inputReader.getImage(1, LABEL_PATH, IMAGE_PATH);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        trainNetworkWith(picture);

        /*
        for (Picture picture : pictures) {
            trainNetworkWith(picture);
        }

        saveWeights.saveWeights((NeuralNetwork) neuralNetwork);
        */
    }

    private void trainNetworkWith(Picture picture) {

        IVektor labelVector = convertLabelToVector(picture);
        IVektor pictureVector = convertPictureToVector(picture);
        neuralNetwork.train(pictureVector, labelVector, 1, 1.0);

    }

    private IVektor convertLabelToVector(Picture picture) {

        int numberOfNumbers = 10; //based name, i ever came up with
        double[] numbers = new double[numberOfNumbers];
        int label = picture.getLabel();
        numbers[label] = 1;

        return new Vektor(numbers);
    }

    private IVektor convertPictureToVector(Picture picture) {
        double[] pictureOfDoubles = Arrays.stream(picture.getPixel())
                .mapToDouble(i -> i)
                .toArray();

        return new Vektor(pictureOfDoubles);
    }
}
