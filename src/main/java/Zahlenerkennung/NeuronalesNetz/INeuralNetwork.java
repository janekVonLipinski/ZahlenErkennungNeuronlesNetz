package Zahlenerkennung.NeuronalesNetz;

import Vektor.IVektor;

public interface INeuralNetwork {

    void save();
    void readWeights();
    IVektor calculate(IVektor vektor);
}
