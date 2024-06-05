package org.goktan;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        Runnable run = () -> System.out.println("Runnable program"); // no return
        Thread t1 = new Thread(run);
        t1.start();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Runnable> runnableList = Arrays.asList(
                () -> System.out.println("Runnable 1"),
                () -> System.out.println("Runnable 2"),
                () -> System.out.println("Runnable 3"),
                () -> System.out.println("Runnable 4"),
                () -> System.out.println("Runnable 5"),
                () -> System.out.println("Runnable 6"),
                () -> System.out.println("Runnable 7"),
                () -> System.out.println("Runnable 8"),
                () -> System.out.println("Runnable 9"));


        List<Callable<Integer>> callableList = Arrays.asList(
                () -> {int a = 1; System.out.println("Callable 1"); return 1;},
                () -> {int a = 1; System.out.println("Callable 2"); return 2;},
                () -> {int a = 1; System.out.println("Callable 3"); return 3;},
                () -> {int a = 1; System.out.println("Callable 4"); return 4;},
                () -> {int a = 1; System.out.println("Callable 5"); return 5;});

        try {
            List<Future<Integer>> result = executorService.invokeAll(callableList); // The invokeAll just works with callable.
            result.forEach(r -> {
                try {
                    System.out.println("Result" + r.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        IntStream.range(0,9).forEach(i -> executorService.submit(runnableList.get(i)));


    }
}