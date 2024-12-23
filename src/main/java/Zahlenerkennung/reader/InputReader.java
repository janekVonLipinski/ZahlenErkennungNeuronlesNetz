package Zahlenerkennung.reader;

import Zahlenerkennung.Model.Picture;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class InputReader {
    private static final int NUMBER_OF_HEADER_BYTES = 4;

    public int getLabel(int n, String labelFilePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(labelFilePath)) {
            byte[] buffer = new byte[4];
            fis.skip(NUMBER_OF_HEADER_BYTES);
            fis.read(buffer);
            int numLabels = ByteBuffer.wrap(buffer).getInt();

            if (n >= numLabels) throw new IllegalArgumentException("n has to be in range from [0, %d]: currently %d!".formatted(numLabels, n));

            fis.skip(n);
            return fis.read();
        }
    }

    public Picture getImage(int n, String labelFilePath, String imageFilePath) throws IOException, IllegalArgumentException {
        try (FileInputStream fis  = new FileInputStream(imageFilePath)) {
            byte[] buffer = new byte[4];
            fis.skip(NUMBER_OF_HEADER_BYTES);

            fis.read(buffer);
            int numImages = ByteBuffer.wrap(buffer).getInt();

            if (n >= numImages) throw new IndexOutOfBoundsException();

            fis.read(buffer);
            int numRows = ByteBuffer.wrap(buffer).getInt();

            fis.read(buffer);
            int numCols = ByteBuffer.wrap(buffer).getInt();

            fis.skip(n*numRows*numCols);

            int[] pixels = new int[numRows*numCols];
            for (int i = 0; i < numRows*numCols; i++) {
                pixels[i] = fis.read();
            }

            int label = getLabel(n,  labelFilePath);
            return new Picture(label, pixels, numCols);
        }
    }

    public Picture[] getImages (String labelFilePath, String imageFilePath) throws IOException {
        byte[] buffer = new byte[4];
        int[] labels;
        int numLabels;
        try (FileInputStream fis = new FileInputStream(labelFilePath)) {
            fis.skip(NUMBER_OF_HEADER_BYTES);
            fis.read(buffer);
            numLabels = ByteBuffer.wrap(buffer).getInt();
            labels = new int[numLabels];
            for (int i = 0; i < numLabels; i++)
                labels[i] = fis.read();
        }

        int[][] pixels;
        int numCols;
        try (FileInputStream fis = new FileInputStream(imageFilePath)) {

            fis.skip(NUMBER_OF_HEADER_BYTES);

            fis.read(buffer);
            int numImages = ByteBuffer.wrap(buffer).getInt();

            fis.read(buffer);
            int numRows = ByteBuffer.wrap(buffer).getInt();

            fis.read(buffer);
            numCols = ByteBuffer.wrap(buffer).getInt();

            pixels = new int[numImages][numRows*numCols];
            for (int i = 0; i < numImages; i++) {
                for (int pixel = 0; pixel < numRows*numCols; pixel++) {
                    pixels[i][pixel] = fis.read();
                }
            }
        }

        Picture[] bilder = new Picture[numLabels];
        for (int i = 0; i < numLabels; i++) {
            bilder[i] = new Picture(labels[i], pixels[i], numCols);
        }
        return bilder;
    }
}
