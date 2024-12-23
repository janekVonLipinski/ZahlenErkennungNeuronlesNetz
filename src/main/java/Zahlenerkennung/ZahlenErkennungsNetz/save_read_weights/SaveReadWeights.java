package Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.LayerConnection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaveReadWeights {

    private static final String FILE_LOCATION = "ZahlenErkennungNeuronlesNetz/src/main/resources/weights";
    private static final String FILE_NAME = "weights.txt";

    /**
     * @param neuralNetwork saves a Neural Network to a txt File. Values of a Matrix within a row
     *                      are comma seperated, rows are seperated by semicolon.
     *                      Different matrices are seperated by empty row
     */
    public void saveWeights(NeuralNetwork neuralNetwork) {

        List<LayerConnection> weights = neuralNetwork.getWeights();
        String weightsToSave = weights.stream()
                .map(LayerConnection::getWeightMatrix)
                .map(Object::toString)
                .map(string -> string + "\n")
                .collect(Collectors.joining());

        weightsToSave = weightsToSave.replace("[", "");
        weightsToSave = weightsToSave.replace("]", "");

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_LOCATION + "/" + FILE_NAME)
        )) {
            writer.write(weightsToSave);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public List<IMatrix> readMatrix() {

        List<IMatrix> matrices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(FILE_LOCATION + "/" + FILE_NAME)
        )) {

            List<String> read = reader.lines().toList();

            int i = 0;
            String line;

            while (i < read.size()) {

                List<double[]> arrays = new ArrayList<>();

                while (true) {

                    line = read.get(i);

                    if (line.isEmpty()) {
                        break;
                    }

                    String[] seperated = line.split(",");
                    double[] row = new double[seperated.length];

                    for (int j = 0; j < seperated.length; j++) {
                        row[j] = Double.parseDouble(seperated[j]);
                    }

                    arrays.add(row);
                    i++;
                }

                double[][] arr = new double[arrays.size()][arrays.get(0).length];

                for (int k = 0; k < arrays.size(); k++) {
                    arr[k] = arrays.get(k);
                }

                IMatrix matrix = new Matrix(arr);
                matrices.add(matrix);

                i++;
            }

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return matrices;
    }
}
