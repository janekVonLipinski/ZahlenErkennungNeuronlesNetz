package Zahlenerkennung.ZahlenErkennungsNetz.save_read_weights;

import Matrizen.IMatrix;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.LayerConnection;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class SaveWeights {

    private static final String FILE_LOCATION = "ZahlenErkennungNeuronlesNetz/src/main/resources/weights";
    private static final String FILE_NAME = "weights.txt";

    public void saveWeights(NeuralNetwork neuralNetwork) {

        List<LayerConnection> weights = neuralNetwork.getWeights();
        String weightsToSave = weights.stream()
                .map(LayerConnection::getWeightMatrix)
                .map(Object::toString)
                .map(string -> string + "\n")
                .collect(Collectors.joining());

        weightsToSave = weightsToSave.replace("[", "");
        weightsToSave = weightsToSave.replace("]", ";");

        System.out.println(weightsToSave);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_LOCATION + "/" + FILE_NAME)
        )) {
            writer.write(weightsToSave);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public List<IMatrix> readMatrix() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(FILE_LOCATION + "/" + FILE_NAME)
        )) {
            reader.readLine();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return null;
    }
}
