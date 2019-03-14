/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author helenocampos
 */
public class TemperatureMeasurement
{
    private long timestamp;
    private float temp;
    private String sensorId;

    public TemperatureMeasurement(){
        
    }
    
    public TemperatureMeasurement(String sensorId){
        this.timestamp = new Date().getTime();
        this.temp = new Random().nextFloat()*45;
        this.sensorId = sensorId;
    }
    
    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public float getTemp()
    {
        return temp;
    }

    public void setTemp(float temp)
    {
        this.temp = temp;
    }

    public String getSensorId()
    {
        return sensorId;
    }

    public void setSensorId(String sensorId)
    {
        this.sensorId = sensorId;
    }
    
    
}
