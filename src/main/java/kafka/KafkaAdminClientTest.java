package kafka;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaAdminClientTest {

        public static void main(String[] args) throws ExecutionException, InterruptedException {

                Properties props = new Properties();
                //props.setProperty("bootstrap.servers", "10.255.15.126:9092");
                props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.255.15.126:9092");
                props.setProperty("group.id", "test_kang");
                props.setProperty("enable.auto.commit", "true");
                props.setProperty("auto.commit.interval.ms", "1000");
                props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

                AdminClient kafkaAdminClient = KafkaAdminClient.create(props);

                ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
                if (listTopicsResult != null) {
                        System.out.println(listTopicsResult.names().get());
                }

                Collection<ConsumerGroupListing> consumerGroups = kafkaAdminClient.listConsumerGroups().all().get();

                consumerGroups.forEach(consumerGroupListing -> System.out.println(consumerGroupListing.groupId()));
                ListConsumerGroupOffsetsResult listConsumerGroupOffsetsResult = kafkaAdminClient.listConsumerGroupOffsets("CID-jingwei-singleunit-souche_crm-singleunit-jingwei");
                Map<TopicPartition, OffsetAndMetadata> topicPartitionOffsetAndMetadataMap = listConsumerGroupOffsetsResult.partitionsToOffsetAndMetadata().get();
                System.out.println(topicPartitionOffsetAndMetadataMap);
    }
}
