package Zahlenerkennung.NeuronalesNetz.Netz;

import Vektor.IVektor;
import Vektor.Vektor;
import Zahlenerkennung.NeuronalesNetz.INeuralNetwork;
import Zahlenerkennung.NeuronalesNetz.Netz.NeuralNetworkParts.ActivationFunction.SigmoidFunction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NeuralNetworkTest {

    private static final double TOLERATED_DIFFERENCE = 0.01;
    @Test
    void Given_values_from_the_book_Then_result_is_the_same_as_in_the_booK() {
        double[] input = {0.9, 0.1, 0.8};
        double[][] firstConnection = {{0.9, 0.3, 0.4}, {0.2, 0.8, 0.2}, {0.1, 0.5, 0.6}};
        double[][] secondConnection = {{0.3, 0.7, 0.5}, {0.6, 0.5, 0.2}, {0.8, 0.1, 0.9}};

        double[] expectedVector = {0.726, 0.708, 0.778};

        INeuralNetwork neuralNetwork = new NeuralNetwork(List.of(firstConnection, secondConnection), new SigmoidFunction());
        IVektor result = neuralNetwork.calculate(new Vektor(input));

        System.out.println(result);
        assertArrayEquals(result.getVektor(), expectedVector, TOLERATED_DIFFERENCE);
    }
}