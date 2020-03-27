/*
package benchmark;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput,Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms1G", "-Xmx1G"})
@Warmup(iterations = 0)
@Measurement(iterations = 1,time = 10,timeUnit = TimeUnit.MILLISECONDS)
public class BenchmarkLoopTable {

    @Param({"10"})
    private int N;

    private int count;

    Table<String,String,Object> table = HashBasedTable.create();

    Map<CacheKey,Object> hashMap =  new HashMap<>();

    private List<String> DATA_FOR_TESTING;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkLoopTable.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
       // DATA_FOR_TESTING = createData();
    }

   */
/* @Benchmark
    @Threads(value = 10)
    public void loopFor(Blackhole bh) {
        for (int i = 0; i < DATA_FOR_TESTING.size(); i++) {
            String s = DATA_FOR_TESTING.get(i); //take out n consume, fair with foreach
            bh.consume(s);
        }
    }*//*


    @Benchmark
    @Threads(value = 10)
    public void testForTable(Blackhole bh) {

        for (int i =0;i< 100000;i++) {
            table.put("hello" + i,"world" + i,"hello world");
        }
    }

    @Benchmark
    @Threads(value = 10)
    public void testForMap(Blackhole bh) {

        for (int i =0;i< 100000;i++) {
            hashMap.put(new CacheKey("hello" + i,"world" + i),"hello world");
        }
    }

    private static class CacheKey {
        private String firstKey;
        private String secondKey;

        public CacheKey(String firstKey, String secondKey) {
            this.firstKey = firstKey;
            this.secondKey = secondKey;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return Objects.equals(firstKey, cacheKey.firstKey) &&
                    Objects.equals(secondKey, cacheKey.secondKey);
        }

        @Override
        public int hashCode() {

            return Objects.hash(firstKey, secondKey);
        }


    }

}*/
