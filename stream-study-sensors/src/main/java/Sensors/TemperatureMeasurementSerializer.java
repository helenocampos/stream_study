/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors;

import com.fasterxml.jackson.databind.ObjectMapper;
import commons.TemperatureMeasurement;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;

/**
 *
 * @author helenocampos
 */
public class TemperatureMeasurementSerializer implements Serializer<TemperatureMeasurement>
{
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
    @Override
    public byte[] serialize(String topic, TemperatureMeasurement data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
        System.out.println("Error in serializing object"+ data);
        }
        return retVal;
    }
    @Override
    public void close() {
    }
}
