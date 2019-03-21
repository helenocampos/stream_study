/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors;

import Kafka.ProducerCreator;
import commons.IKafkaConstants;
import commons.TemperatureMeasurement;
import commons.TemperatureSensor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 *
 * @author helenocampos
 */
public class SensorsSimulation
{

    private static List<ProducerSensor> sensors;
    private static int tempMin = 0;
    private static int tempMax = 45;

    public static void main(String[] args)
    {
        if (args.length != 0)
        {
            try
            {
                int sensorsAmount = Integer.valueOf(args[0]);

                sensors = new ArrayList(sensorsAmount);
                if (args.length == 4)
                {
                    tempMin = Integer.valueOf(args[2]);
                    tempMax = Integer.valueOf(args[3]);
                    instantiateSensors(sensorsAmount, args[1]);
                } else
                {
                    instantiateSensors(sensorsAmount);
                }
                monitor();
            } catch (NumberFormatException e)
            {
                System.out.println("Invalid argument. Please provide the number of sensors to simulate.");
            }
        }
    }

    private static void instantiateSensors(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            sensors.add(new ProducerSensor(new TemperatureSensor("poco1-sensor_temp" + (i + 1)), ProducerCreator.createProducer()));
        }
    }

    private static void instantiateSensors(int amount, String address)
    {
        for (int i = 0; i < amount; i++)
        {
            sensors.add(new ProducerSensor(new TemperatureSensor("poco1-sensor_temp" + (i + 1)), ProducerCreator.createProducer(address)));
        }
    }

    public static void monitor()
    {

        try
        {
            System.out.println("Initializing sensors monitoring...");
            while (true)
            {
                for (ProducerSensor sensor : sensors)
                {
                    String key = sensor.getSensor().getId() + "_" + new Date().getTime();
                    ProducerRecord<String, TemperatureMeasurement> record = new ProducerRecord<>(IKafkaConstants.TOPIC_NAME, key,
                            sensor.getSensor().getMeasurement(tempMin, tempMax));
                    try
                    {
                        RecordMetadata metadata = sensor.getProducer().send(record).get();
                        System.out.println("Record sent with key " + key + " to partition " + metadata.partition()
                                + " with offset " + metadata.offset());
                    } catch (ExecutionException | InterruptedException e)
                    {
                        System.out.println("Error in sending record");
                        System.out.println(e);
                    }

                }
                Thread.sleep(500);
            }

        } catch (InterruptedException ex)
        {
            Logger.getLogger(SensorsSimulation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
