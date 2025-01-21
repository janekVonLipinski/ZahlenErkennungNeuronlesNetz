package Matrizen.MatrixImplementierung.MatrixArithmetic;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMultiplikatorTest {

    private final MatrixMultiplikator matrixMultiplikator = new MatrixMultiplikator();

    @Test
    void GIVEN_matrixIsNotQuadratic_THEN_resultIsCorrect() {

        double[][] matrix1 = {{2, 2, 2}, {1, 1, 1}};
        double[][] matrix = {{1}, {2}, {2}};

        IMatrix m = new Matrix(matrix1);
        IMatrix m1 = new Matrix(matrix);

        double[][] expected = {{10}, {5}};

        IMatrix result = matrixMultiplikator.multipliziere(m ,m1);

        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    void GIVEN_matrixIsQuadratic_THEN_resultIsCorrect() {

        double[][] matrix1 = {{2, 2}, {1, 1}};
        double[][] matrix = {{3, 3}, {4, 4}};

        IMatrix m = new Matrix(matrix1);
        IMatrix m1 = new Matrix(matrix);

        double[][] expected = {{14, 14}, {7, 7}};

        IMatrix result = matrixMultiplikator.multipliziere(m ,m1);

        assertArrayEquals(expected, result.getMatrix());
    }

}