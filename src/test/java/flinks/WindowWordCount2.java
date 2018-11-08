package flinks;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class WindowWordCount2 {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Tuple2<Integer,Integer>> dataStream = env
                .socketTextStream("localhost", 9999)
                .flatMap(new Splitter()).keyBy(0).timeWindow(Time.seconds(5)).sum(1);
        dataStream.process(new ProcessFunction<Tuple2<Integer,Integer>, Object>() {

            @Override
            public void processElement(Tuple2<Integer, Integer> value, Context ctx, Collector<Object> out) throws Exception {
                System.out.println("total word count:" + value.f1);
            }
        });

        dataStream.print();

        env.execute("Window WordCount");
    }

    public static class Splitter implements FlatMapFunction<String,Tuple2<Integer,Integer>> {


        @Override
        public void flatMap(String sentence, Collector<Tuple2<Integer, Integer>> out) throws Exception {
            for (String word : sentence.split(" ")) {
                out.collect(Tuple2.of(1,1));
            }
        }
    }

}