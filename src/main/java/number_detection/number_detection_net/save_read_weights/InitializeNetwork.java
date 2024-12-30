package number_detection.number_detection_net.save_read_weights;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InitializeNetwork {

    private final SaveReadWeights saveReadWeights = new SaveReadWeights();

    public List<IMatrix> initializeMatrices(String fileName, List<Integer> neuronsPerLayer) {

        List<IMatrix> weights = new ArrayList<>();

        for (int i = 0; i < neuronsPerLayer.size() - 1; i++) {

            int inputNodes = neuronsPerLayer.get(i);
            int outPutNodes = neuronsPerLayer.get(i + 1);

            IMatrix matrix = generateRandomLayer(outPutNodes, inputNodes);
            weights.add(matrix);
        }

        saveReadWeights.saveWeights(weights, fileName);
        return weights;
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
