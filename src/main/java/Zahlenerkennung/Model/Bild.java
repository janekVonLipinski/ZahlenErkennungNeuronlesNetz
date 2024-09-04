package Zahlenerkennung.Model;

public class Bild {

    private final String label;
    private final byte[] pixel;
    private final int rowOffset;

    public Bild(String label, byte[] pixel, int rowOffset) {
        this.label = label;
        this.pixel = pixel;
        this.rowOffset = rowOffset;
    }

    public String getLabel() {
        return label;
    }

    public byte[] getPixel() {
        return pixel;
    }

    public int getRowOffset() {
        return rowOffset;
    }
}
