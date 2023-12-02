package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        for (int i = 4; i <= 6; ++i) {
            correct.addLast(i);
            buggy.addLast(i);
        }
        for (int i = 4; i <= 6; ++i) {
            assertEquals(correct.removeLast(), buggy.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                B.addLast(randVal);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int Bsize = B.size();
                assertEquals(size, Bsize);
            } else if (L.size() > 0) {
                if (operationNumber == 2) {
                    //getLast
                    int last = L.getLast();
                    int Blast = B.getLast();
                    assertEquals(last, Blast);
                } else if (operationNumber == 3) {
                    //removeLast
                    int last = L.removeLast();
                    int Blast = B.removeLast();
                    assertEquals(last, Blast);
                }
            }
        }
    }
}
