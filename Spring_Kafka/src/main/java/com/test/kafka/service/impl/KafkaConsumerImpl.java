package com.test.kafka.service.impl;


import com.test.kafka.config.StorageClient;
import com.test.kafka.service.KafkaConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.record.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumerImpl implements KafkaConsumerService {

    @Autowired
    private StorageClient storageClient;

    @Override
    @KafkaListener(id = "basicConsumers", topics = {"demo"})
    public void messageListener1(ConsumerRecord<?, ?> record) {
        /**
         * headers：消息头信息
         * offset：此记录在相应的 Kafka 分区中的位置。
         * partition：记录所在的分区
         * serializedKeySize：序列化的未压缩密钥的大小（以字节为单位），如果 key为 null，则返回的大小为 -1
         * serializedValueSize：序列化的未压缩值（消息正文）的大小（以字节为单位，record.value().getBytes().length）。如果值为 null，则返回的大小为 -1
         * timestamp：记录的时间戳
         * TimestampType：记录的时间戳类型
         * topic：接收此记录的主题
         * value：消息内容
         */
        Headers headers = record.headers();
        long offset = record.offset();
        int partition = record.partition();
        int serializedKeySize = record.serializedKeySize();
        int serializedValueSize = record.serializedValueSize();
        long timestamp = record.timestamp();
        TimestampType timestampType = record.timestampType();
        String topic = record.topic();
        Object value = record.value();

        System.out.println("收到消息：");
        System.out.println("\theaders=" + headers);
        System.out.println("\toffset=" + offset);
        System.out.println("\tpartition=" + partition);
        System.out.println("\tserializedKeySize=" + serializedKeySize);
        System.out.println("\tserializedValueSize=" + serializedValueSize);
        System.out.println("\ttimestamp=" + timestamp);
        System.out.println("\ttimestampType=" + timestampType);
        System.out.println("\ttopic=" + topic);
        System.out.println("\tvalue=" + value);
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            Map<String, String> map = new HashMap<>();
            map.put("number",jsonObject.getString("number"));
            map.put("id",jsonObject.getString("id"));
           storageClient.updatePurchase(map);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
