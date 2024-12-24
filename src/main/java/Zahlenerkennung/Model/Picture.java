package Zahlenerkennung.Model;

import java.util.Arrays;

public class Picture {

    private final int label;
    private final int[] pixel;
    private final int rowOffset;

    public Picture(int label, int[] pixel, int rowOffset) {
        this.label = label;
        this.pixel = pixel;
        this.rowOffset = rowOffset;
    }

    public int getLabel() {
        return label;
    }

    public int[] getPixel() {
        return pixel;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public void printPicture() {

        for (int i = 0; i < pixel.length; i++) {

            if (i % rowOffset == 0) {
                System.out.println("\n");
            }

            if (pixel[i] > 0) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }

        }
    }

    @Override
    public String toString() {
        return "Bild{" +
                "label=" + label +
                ", pixel=" + Arrays.toString(pixel) +
                ", rowOffset=" + rowOffset +
                '}';
    }
}
