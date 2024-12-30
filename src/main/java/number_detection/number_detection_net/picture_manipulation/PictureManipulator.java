package number_detection.number_detection_net.picture_manipulation;

import number_detection.model.Picture;

import java.io.IOException;

public class PictureManipulator {

    private static final String LABEL_PATH = "ZahlenErkennungNeuronlesNetz/src/main/resources/train-labels.idx1-ubyte";
    private static final String IMAGE_PATH = "ZahlenErkennungNeuronlesNetz/src/main/resources/train-images.idx3-ubyte";
    private final InputReader inputReader = new InputReader();

    public Picture[] readPictures() {

        Picture[] pictures = {};

        try {
            pictures = inputReader.getImages(LABEL_PATH, IMAGE_PATH);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return pictures;
    }
}
