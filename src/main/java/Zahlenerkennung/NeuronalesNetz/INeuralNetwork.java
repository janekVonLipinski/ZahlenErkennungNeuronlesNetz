package Zahlenerkennung.NeuronalesNetz;

import Vektor.IVektor;

public interface INeuralNetwork {

    IVektor calculate(IVektor vector);
    void train(IVektor vector, IVektor expectedVector, int numberOfSessions, double learningRate);
}
