package Zahlenerkennung.ZahlenErkennungsNetz;

import Vektor.IVektor;
import Zahlenerkennung.Model.Picture;
import Zahlenerkennung.Model.InputReader;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights.SaveReadWeights;

import java.io.IOException;

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

        try {
            pictures = inputReader.getImages(LABEL_PATH, IMAGE_PATH);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        for (Picture picture : pictures) {

            IVektor labelVector = convertLabelToVector(picture);
            IVektor pictureVector = convertPictureToVector(picture);

            neuralNetwork.train(pictureVector, labelVector, 1, 1.0);
        }

        saveWeights.saveWeights((NeuralNetwork) neuralNetwork);
    }

    private IVektor convertLabelToVector(Picture picture) {
        return null;
    }

    private IVektor convertPictureToVector(Picture picture) {
        return null;
    }
}
