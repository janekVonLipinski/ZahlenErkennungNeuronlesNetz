package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction;

import Vektor.IVektor;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.IActivationFunction;

import java.util.Arrays;

public class SigmoidFunction implements IActivationFunction {

    @Override
    public double function(double input) {
        return 1 / (1 + Math.exp(-1 * input));
    }
}
