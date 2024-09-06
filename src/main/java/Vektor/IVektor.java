package Vektor;


import Matrizen.IMatrix;

public interface IVektor {

    double[] getVektor();
    IVektor multipliziere(IMatrix m);
    IVektor subtrahiere(IVektor v);
}
