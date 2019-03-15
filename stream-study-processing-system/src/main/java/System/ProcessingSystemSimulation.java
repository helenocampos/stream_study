/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Kafka.ConsumerCreator;
import commons.IKafkaConstants;
import commons.TemperatureMeasurement;
import java.time.Duration;
import java.util.Date;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 *
 * @author helenocampos
 */
public class ProcessingSystemSimulation
{
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT = 10000;
    public static void main(String[] args)
    {
        runConsumer();
    }

    static void runConsumer()
    {
        try (Consumer<String, TemperatureMeasurement> consumer = ConsumerCreator.createConsumer())
        {
            int noMessageFound = 0;
            while (true)
            {
                ConsumerRecords<String, TemperatureMeasurement> consumerRecords = consumer.poll(Duration.ofMillis(510));
                // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
                
                if (consumerRecords.count() == 0)
                {
                    noMessageFound++;
                    if (noMessageFound > MAX_NO_MESSAGE_FOUND_COUNT)
                        // If no message found count is reached to threshold exit loop.
                    {
                        break;
                    } else
                    {
                        continue;
                    }
                }
                
                //print each record.
                consumerRecords.forEach(record ->
                {
                    System.out.println("\n\nRecord Key " + record.key());
                    System.out.println("Record temperature value " + record.value().getTemp());
                    Date recordedDate = new Date(record.value().getTimestamp());
                    System.out.println("Recorded at "+recordedDate.toString());
//                    System.out.println("Record partition " + record.partition());
//                    System.out.println("Record offset " + record.offset());
                });
                // commits the offset of record to broker.
                consumer.commitAsync();
            }
        }
    }
}
