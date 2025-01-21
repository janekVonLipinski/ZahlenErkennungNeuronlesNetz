package Matrizen.MatrixImplementierung.MatrixTransponierung;

import Matrizen.IMatrix;
import Matrizen.MatrixImplementierung.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransponierungTest {

    private final Transponierung transponierung = new Transponierung();

    @Test
    void GIVEN_() {

        double[][] matrix = {{1, 2}, {1, 2}, {1, 2}};
        IMatrix testMatrix = new Matrix(matrix);

        double[][] expectedMatrix = {{1, 1, 1}, {2, 2, 2}};

        IMatrix result = transponierung.transponiere(testMatrix);

        assertArrayEquals(expectedMatrix, result.getMatrix());
    }

}