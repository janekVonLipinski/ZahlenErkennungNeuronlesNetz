package Zahlenerkennung.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class InputReader {
    private String labelFilePath;
    private String imageFilePath;

    public InputReader(String labelFilePath, String imageFilePath) {
        this.labelFilePath = labelFilePath;
        this.imageFilePath = imageFilePath;
    }

    public void setLabelFilePath(String labelFilePath) {
        this.labelFilePath = labelFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public int getLabel(int n) throws IOException, IndexOutOfBoundsException {
        try (FileInputStream fis = new FileInputStream(labelFilePath)) {
            byte[] buffer = new byte[4];
            fis.skip(4);
            fis.read(buffer);
            int numLabels = ByteBuffer.wrap(buffer).getInt();

            if (n >= numLabels) throw new IndexOutOfBoundsException();

            fis.skip(n);
            return fis.read();
        }
    }

    public Bild getImage(int n) throws IOException, IndexOutOfBoundsException {
        try (FileInputStream fis  = new FileInputStream(imageFilePath)) {
            byte[] buffer = new byte[4];
            fis.skip(4);

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

            int label = getLabel(n);
            return new Bild(label, pixels, numCols);
        }
    }

    public Bild[] getImages () throws IOException {
        byte[] buffer = new byte[4];
        int[] labels;
        int numLabels;
        try (FileInputStream fis = new FileInputStream(labelFilePath)) {
            fis.skip(4);
            fis.read(buffer);
            numLabels = ByteBuffer.wrap(buffer).getInt();
            labels = new int[numLabels];
            for (int i = 0; i < numLabels; i++)
                labels[i] = fis.read();
        }

        int[][] pixels;
        int numCols;
        try (FileInputStream fis = new FileInputStream(imageFilePath)) {

            fis.skip(4);

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
                if (i%1000 == 0) System.out.println(i + " images done!");
            }
        }

        Bild[] bilder = new Bild[numLabels];
        for (int i = 0; i < numLabels; i++) {
            bilder[i] = new Bild(labels[i], pixels[i], numCols);
        }
        return bilder;
    }
}
