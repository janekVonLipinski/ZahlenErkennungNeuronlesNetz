package Zahlenerkennung.NeuronalesNetz;

import Vektor.IVektor;
import Zahlenerkennung.Model.Bild;

public interface INeuralNetwork {

    void save();
    void readWeights();
    IVektor verarbeite(IVektor vektor);
}
