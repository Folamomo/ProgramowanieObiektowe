import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MaxMultiThread {
    private ExecutorService executor = Executors.newCachedThreadPool();
    public <T> Future<T> execute(Callable<T> callable){
        return executor.submit(callable);
    }

    public void end(){
        executor.shutdown();
    }

}
