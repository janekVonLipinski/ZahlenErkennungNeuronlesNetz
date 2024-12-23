package Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

import java.util.List;
import java.util.Random;

public class InitializeNetwork {


    private final SaveReadWeights saveReadWeights = new SaveReadWeights();

    public void initializeMatrix() {

        //TODO values should not be hardcoded
        IMatrix firstLayer = generateRandomLayer( 128, 784);
        IMatrix secondLayer = generateRandomLayer(56, 128);
        IMatrix thirdLayer = generateRandomLayer(10, 56);

        saveReadWeights.saveWeights(List.of(firstLayer, secondLayer, thirdLayer), "weights0");
    }


    public IMatrix generateRandomLayer (int numInputNodes, int numOutputNodes) {
        Random random = new Random();

        double[][] layerWeights = new double[numInputNodes][numOutputNodes];

        double limit = Math.sqrt(6.0 / (numInputNodes + numOutputNodes));

        for (int i = 0; i < numInputNodes; i++) {
            for (int j = 0; j < numOutputNodes; j++) {
                layerWeights[i][j] = -limit + random.nextDouble() * 2 * limit;
            }
        }
        return new Matrix(layerWeights);
    }

}
