/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors;

import commons.TemperatureMeasurement;
import commons.TemperatureSensor;
import org.apache.kafka.clients.producer.Producer;


/**
 *
 * @author helenocampos
 */
public class ProducerSensor
{
    private TemperatureSensor sensor;
    private Producer<String, TemperatureMeasurement> producer;
    
    public ProducerSensor(TemperatureSensor sensor, Producer producer){
        this.sensor = sensor;
        this.producer = producer;
    }

    public TemperatureSensor getSensor()
    {
        return sensor;
    }

    public void setSensor(TemperatureSensor sensor)
    {
        this.sensor = sensor;
    }

    public Producer<String, TemperatureMeasurement> getProducer()
    {
        return producer;
    }

    public void setProducer(Producer<String, TemperatureMeasurement> producer)
    {
        this.producer = producer;
    }
            
    
}
