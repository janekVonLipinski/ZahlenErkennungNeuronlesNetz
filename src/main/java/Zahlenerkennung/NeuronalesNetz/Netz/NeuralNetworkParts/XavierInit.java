package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts;

import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;

import java.util.Random;

public class XavierInit {

    private LayerConnection generateRandomLayer (int numInputNodes, int numOutputNodes) {
        Random random = new Random();

        double[][] layerWeights = new double[numInputNodes][numOutputNodes];
        // Xavier Initialization
        double limit = Math.sqrt(6 / (numInputNodes + numOutputNodes));

        for (int i = 0; i < numInputNodes; i++) {
            for (int j = 0; j < numOutputNodes; j++) {
                layerWeights[i][j] = -limit + random.nextDouble() * 2 * limit;
                System.out.println( layerWeights[i][j]);
            }
        }
        return new LayerConnection(layerWeights, new SigmoidFunction());
    }

}
