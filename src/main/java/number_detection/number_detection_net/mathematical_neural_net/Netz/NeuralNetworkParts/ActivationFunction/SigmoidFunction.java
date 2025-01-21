package number_detection.number_detection_net.mathematical_neural_net.Netz.NeuralNetworkParts.ActivationFunction;

import number_detection.number_detection_net.mathematical_neural_net.Netz.NeuralNetworkParts.IActivationFunction;

public class SigmoidFunction implements IActivationFunction {

    @Override
    public double function(double input) {
        return 1 / (1 + Math.exp(-1 * input));
    }
}
