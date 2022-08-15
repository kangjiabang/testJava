package optimize.performance;

public class CpuCompute {


    public static void main(String[] args) {

            Performance performance = new Performance();

        new Thread(()->{ performance.cpu();},"cpu run").start();
            new Thread(()->{performance.cpuWait();},"cpuWait").start();

    }

}
