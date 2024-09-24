package Zahlenerkennung.Model;

public class Bild {

    private final int label;
    private final int[] pixel;
    private final int rowOffset;

    public Bild(int label, int[] pixel, int rowOffset) {
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
}
