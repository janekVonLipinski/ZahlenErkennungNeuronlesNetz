package number_detection.number_detection_net;

import number_detection.model.Picture;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;


public class PictureInput {

    private static final String TRAIN_LABEL_PATH = "src/main/resources/train-labels.idx1-ubyte";
    private static final String TRAIN_IMAGE_PATH = "src/main/resources/train-images.idx3-ubyte";
    private static final String EVALUATION_LABEL_PATH = "src/main/resources/t10k-labels.idx1-ubyte";
    private static final String EVALUATION_IMAGE_PATH = "src/main/resources/t10k-images.idx3-ubyte";
    private final InputReader inputReader = new InputReader();

    public Picture[] getEvaluationImages() {

        Picture[] pictures = readPictures(EVALUATION_LABEL_PATH, EVALUATION_IMAGE_PATH);

        if (pictures == null || Arrays.stream(pictures).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException();
        }

        return pictures;
    }

    public Picture[] getTrainingImages() {

        Picture[] pictures = readPictures(TRAIN_LABEL_PATH, TRAIN_IMAGE_PATH);

        if (pictures == null || Arrays.stream(pictures).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException();
        }

        return pictures;
    }

    private Picture[] readPictures(String labelPath, String imagePath) {

        Picture[] pictures = {};

        try {
            pictures = inputReader.getImages(labelPath, imagePath);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return pictures;
    }
}
