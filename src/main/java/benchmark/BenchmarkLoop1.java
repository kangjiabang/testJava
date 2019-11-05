package benchmark;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput,Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms1G", "-Xmx1G"})
@Warmup(iterations = 0)
@Measurement(iterations = 1,time = 10,timeUnit = TimeUnit.MILLISECONDS)
public class BenchmarkLoop1 {

    @Param({"10"})
    private int N;

    private int count;

    private List<String> DATA_FOR_TESTING;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkLoop1.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        DATA_FOR_TESTING = createData();
    }

   /* @Benchmark
    @Threads(value = 10)
    public void loopFor(Blackhole bh) {
        for (int i = 0; i < DATA_FOR_TESTING.size(); i++) {
            String s = DATA_FOR_TESTING.get(i); //take out n consume, fair with foreach
            bh.consume(s);
        }
    }*/

    @Benchmark
    @Threads(value = 10)
    public void testForBaidu(Blackhole bh) {
        long startTime = System.currentTimeMillis();
        //for (int i = 0; i < DATA_FOR_TESTING.size(); i++) {
            testOKHttpRequest();
            count++;
            System.out.println("time spent:" + (System.currentTimeMillis() - startTime) + " Count:" + count);
       // }
    }

    public void testOKHttpRequest() {

        String url = "http://www.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().header("testPerf", "1")
                .get()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            //System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private List<String> createData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            data.add("Number : " + i);
        }
        return data;
    }

}