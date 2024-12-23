package Zahlenerkennung.ZahlenErkennungsNetz;

import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.Model.Picture;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
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
        Picture[] pictures = new Picture[2];

        try {
            //pictures = inputReader.getImages(LABEL_PATH, IMAGE_PATH);
            for (int i = 0; i < pictures.length; i++) {
                Picture picture = inputReader.getImage(i + 1, LABEL_PATH, IMAGE_PATH);
                pictures[i] = picture;
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        for (Picture picture : pictures) {
            trainNetworkWith(picture);
        }


        saveWeights.saveWeights((NeuralNetwork) neuralNetwork);
    }

    private void trainNetworkWith(Picture picture) {

        IVektor labelVector = convertLabelToVector(picture);
        IVektor pictureVector = convertPictureToVector(picture);
        System.out.println(Arrays.toString(labelVector.getVektor()));
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
                .mapToDouble(i -> i / 256.0)
                .peek(System.out::println)
                .toArray();


        return new Vektor(pictureOfDoubles);
    }
}
