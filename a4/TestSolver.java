import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.lang.Math;
import java.lang.IllegalArgumentException;

public class TestSolver {

    int[][] blocks1 = new int[2][2];
    int[][] blocks2 = new int[2][2];
    int[][] blocks3 = new int[3][3];
    int[][] blocks4 = new int[3][3];
    int[][] blocks5 = new int[2][2];
    int[][] blocks6 = new int[3][3];
    int[][] blocks7 = new int[3][3];
    int[][] blocks8 = new int[3][3];

    @Before
    public void setUp() throws Exception {
        int[] blocks1b = { 1, 2, 0, 3 };
        int[] blocks2b = { 3, 1, 0, 2 };
        int[] blocks3b = { 4, 2, 3, 0, 5, 7, 6, 8, 1 };
        int[] blocks4b = { 0, 8, 6, 3, 4, 7, 2, 1, 5 };
        int[] blocks5b = { 1, 2, 3, 0 };
        int[] blocks6b = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
        int[] blocks7b = { 1, 2, 5, 0, 3, 4, 7, 8, 9 };
        int[] blocks8b = { 1, 2, 5, 3, 0, 4, 7, 8, 9 };
        // int[] blocks4b = {0, 8, 6, 3, 4, 7, 2, 1, 5};
        blocks1 = convertArray(blocks1b);
        blocks2 = convertArray(blocks2b);
        blocks3 = convertArray(blocks3b);
        blocks4 = convertArray(blocks4b);
        blocks5 = convertArray(blocks5b);
        blocks6 = convertArray(blocks6b);
        blocks7 = convertArray(blocks7b);
        blocks8 = convertArray(blocks8b);
        return;
    }

    // Make sure null input is caught
    @Test(expected = IllegalArgumentException.class)
    public void testInput() {
        Solver s = new Solver(null);
    }

    // Make sure null input is caught
    @Test()
    public void testSolver1() {
        Board b = new Board(blocks1);
        Solver s = new Solver(b);
        Iterable<Board> soln = s.solution();
        if (soln != null) {
            for (Board c : soln) {
                System.out.println(c);
            }
        }
    }

    private int[][] convertArray(int[] input) {
        double inputRoot = Math.sqrt(input.length);
        int dim = (int) inputRoot;
        int[][] converted = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int correctValue = (i * dim) + j;
                converted[i][j] = input[correctValue];
            }
        }
        return converted;
    }

}
