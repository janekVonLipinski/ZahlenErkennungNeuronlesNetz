package Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts;

import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LayerConnectionTest {

    private final double[][] weights = {{}};
    private final LayerConnection connection = new LayerConnection(weights , new SigmoidFunction());

    @Test
    void Given_values_From_book_then_delta_is_correct() {
        double sum = 2.3;
        double error = 0.8;

        double result = connection.calculateDerivation(error, sum);
        double expected = -0.0265;

        assertEquals(expected, result, 0.001);
    }

}