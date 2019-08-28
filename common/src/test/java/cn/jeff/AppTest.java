package cn.jeff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void completeException() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toLowerCase,
                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture<String> handler = cf.handle((s, th) -> {
            return (th != null) ? "message upon cancel":"";
        });
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        assertTrue("Was not completed normally", cf.isCompletedExceptionally());
        try {
            cf.join();
            fail("Should have thrown an exception");
        }catch (CompletionException ex) {
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }
        assertEquals("message upon cancel", handler.join());
    }

    @Test
    public void listPic() {
        List<String> list = new ArrayList<>();
        list.add("ss");
        list.add("aa");
        list.add("aa");
        List<String> collect = list.stream().filter(item -> {
            if ("aa".equals(item)) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        System.out.println(collect.size());
        System.out.println(collect);
        System.out.println(list.size());
    }

    @Test
    public void testt(){
        Map<String, String> map = new HashMap<>();

    }
}
