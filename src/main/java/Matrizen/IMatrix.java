package Matrizen;

public interface IMatrix {

    double[][] getMatrix();
    int getAnzahlZeilen();
    int getAnzahlSpalten();
    double getDeterminante();
    IMatrix multipliziere(IMatrix m);
    IMatrix multipliziere(double faktor);
    IMatrix transponiere();
    IMatrix getInverseMatrix();
    IMatrix getDiagonalForm();
    IMatrix getStufenForm();
}
