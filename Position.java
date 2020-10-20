import java.util.*;
import java.util.concurrent.*;

public class Position{
    private int x;
    private int y;

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    public Position(int row, int column){
        this.x = row;
        this.y = column;
    }

    private int getX(){
        return this.x;
    }

    private int getY(){
        return this.y;
    }

    public static void main(String args[]){
        // long startTime = System.nanoTime();
        // for(int i=0; i< 1000000000; i++){
        //     Object obj = new Object();
        // }
        // long elapsedTime = System.nanoTime() - startTime;
        // System.out.println(startTime);
        // System.out.println(elapsedTime);

        // try {
        //     for(int i=0; i< 1000000000; i++){
        //         Object obj = new Object();
        //     }
        //     Thread.sleep(5);
        // } catch (Exception e) {
        //     System.out.println("1");
        // }
        // System.out.println("2");

        final ExecutorService service = Executors.newSingleThreadExecutor();

        try {
            final Future<Object> f = service.submit(() -> {
                for(int i=0; i< 1000000; i++){
                    Object obj = new Object();
                }
                Thread.sleep(50000); // Simulate some delay
                return "42";
            });

            System.out.println(f.get(1, TimeUnit.SECONDS));
        } catch (final TimeoutException e) {
            System.err.println("Calculation took to long");
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
    }
}