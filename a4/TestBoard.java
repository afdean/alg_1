import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.lang.Math;

public class TestBoard {

    int[][] blocks1 = new int[2][2];
    int[][] blocks2 = new int[2][2];
    int[][] blocks3 = new int[3][3];
    int[][] blocks4 = new int[3][3];
    int[][] blocks5 = new int[2][2];
    int[][] blocks6 = new int[3][3];

    @Before
    public void setUp() throws Exception {
        int[] blocks1b = {1, 2, 0, 3};
        int[] blocks2b = {3, 1, 0, 2};
        int[] blocks3b = {4, 2, 3, 0, 5, 7, 6, 8, 1};
        int[] blocks4b = {0, 8, 6, 3, 4, 7, 2, 1, 5};
        int[] blocks5b = {1, 2, 3, 0 };
        int[] blocks6b = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        // int[] blocks4b = {0, 8, 6, 3, 4, 7, 2, 1, 5};
        blocks1 = convertArray(blocks1b);
        blocks2 = convertArray(blocks2b);
        blocks3 = convertArray(blocks3b);
        blocks4 = convertArray(blocks4b);
        blocks5 = convertArray(blocks5b);
        blocks6 = convertArray(blocks6b);
        return;
    }

    // Check if hamming returns right value
    @Test
    public void testHamming1() {
        Board b = new Board(blocks1);
        Assert.assertEquals(b.hamming(), 1);
    }

    // Check if hamming returns right value
    @Test
    public void testHamming2() {
        Board b = new Board(blocks2);
        Assert.assertEquals(b.hamming(), 3);
    }

    // Check if hamming returns right value
    @Test
    public void testHamming3() {
        Board b = new Board(blocks3);
        Assert.assertEquals(b.hamming(), 4);
    }

    // Check if hamming returns right value
    @Test
    public void testHamming4() {
        Board b = new Board(blocks4);
        Assert.assertEquals(b.hamming(), 8);
    }

    // Check if manhattan returns right value
    @Test
    public void testMan1() {
        Board b = new Board(blocks1);
        Assert.assertEquals(b.manhattan(), 1);
    }

    // Check if manhattan returns right value
    @Test
    public void testMan2() {
        Board b = new Board(blocks2);
        Assert.assertEquals(b.manhattan(), 3);
    }

    // Check if manhattan returns right value
    @Test
    public void testMan3() {
        Board b = new Board(blocks3);
        Assert.assertEquals(b.manhattan(), 11);
    }

    // Check if manhattan returns right value
    @Test
    public void testMan4() {
        Board b = new Board(blocks4);
        Assert.assertEquals(b.manhattan(), 18);
    }

    // Check if isGoal returns right value
    @Test
    public void testGoal1() {
        Board b = new Board(blocks1);
        Assert.assertEquals(b.isGoal(), false);
        Assert.assertEquals(b.isGoal(), false);
    }

    // Check if isGoal returns right value
    @Test
    public void testGoal2() {
        Board b = new Board(blocks5);
        Assert.assertEquals(b.isGoal(), true);
        Assert.assertEquals(b.isGoal(), true);
    }

    // Check if isGoal returns right value
    @Test
    public void testGoal3() {
        Board b = new Board(blocks3);
        Assert.assertEquals(b.isGoal(), false);
        Assert.assertEquals(b.isGoal(), false);
    }

    // Check if isGoal returns right value
    @Test
    public void testGoal4() {
        Board b = new Board(blocks6);
        Assert.assertEquals(b.isGoal(), true);
        Assert.assertEquals(b.isGoal(), true);
    }

    // Check if equals matches the exact same board
    @Test
    public void testEquals1() {
        Board b = new Board(blocks6);
        Assert.assertEquals(b.equals(b), true);
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
