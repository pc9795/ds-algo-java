package geeks_for_geeks.queue;

import geeks_for_geeks.ds.queue.QueueApplications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestQueue {
    private final ByteArrayOutputStream out=new ByteArrayOutputStream();
    private final PrintStream originalOut=System.out;

    @BeforeEach
    void changeSysout(){
        System.setOut(new PrintStream(out));
    }

    @Test
    void testSlidingWindowMaximum(){
        int[]arr={8, 5, 10, 7, 9, 4, 5, 12, 90, 13};
        QueueApplications.slidingWindowMaximum(arr,4);
        Assertions.assertEquals(out.toString(),"10 10 10 9 12 90 90");
    }
}
