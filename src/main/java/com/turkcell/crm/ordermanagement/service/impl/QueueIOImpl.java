package com.turkcell.crm.ordermanagement.service.impl;

import com.google.gson.Gson;
import com.turkcell.crm.ordermanagement.entity.Order;
import com.turkcell.crm.ordermanagement.repository.OrderRepository;
import com.turkcell.crm.ordermanagement.service.QueueIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
public class QueueIOImpl implements QueueIO {

    @Value("${kafka-client-id}")
    private String kafkaClientId;

    @Value("${kafka-bootstrap-servers}")
    private String kafkaBootstrapServers;

    @Value("${kafka-consumer-key-deserializer}")
    private String kafkaProducerKeyDeserializer;

    @Value("${kafka-consumer-value-deserializer}")
    private String kafkaProducerValueDeserializer;

    @Value("${kafka-order-topic}")
    private String kafkaOrderTopic;

    @Value("${kafka-order-group-id}")
    private String kafkaOrderGroupId;

    @Value("${auto_commit_interval_ms_config}")
    private String autoCommitIntervalMsConfig;

    @Value("${session_timeout_ms_config}")
    private String sessionTimeoutMsConfig;

    final
    OrderRepository orderRepository;

    public QueueIOImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    /**
     * getting orders from queue and store to document db
     *
     */
    @Override
    public void queueToDocumentDB() throws InterruptedException {
        KafkaConsumer<String, String> consumer = createKafkaConsumer();

        consumer.subscribe(Collections.singletonList(kafkaOrderTopic));

        Gson gson = new Gson();
        List<Order> ordersFromQueue = new ArrayList<>();

        while (true) {
            ordersFromQueue.clear();
            Thread.sleep(10000);
            ConsumerRecords<String, String> records = consumer.poll(100);
            log.info(records.count() + " orders has been recorded");
            for (ConsumerRecord<String, String> record : records) {
                log.debug("Consumer Record: topic:" + record.topic() + " offset:" + record.offset());
                Order order = gson.fromJson(record.value(), Order.class);
                ordersFromQueue.add(order);
            }
            if (ordersFromQueue.size() > 0)
                orderRepository.saveAll(ordersFromQueue);
        }
    }


    /**
     * returns consumer of kafka
     *
     * @return
     */
    public KafkaConsumer<String, String> createKafkaConsumer() {
        Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaBootstrapServers);
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,kafkaProducerKeyDeserializer);
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,kafkaProducerValueDeserializer);
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaOrderGroupId);
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, kafkaClientId);
        configProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        configProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMsConfig);
        configProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeoutMsConfig);

        return new KafkaConsumer<>(configProperties);
    }

}
