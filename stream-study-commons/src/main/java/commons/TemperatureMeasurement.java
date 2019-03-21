/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author helenocampos
 */
public class TemperatureMeasurement
{

    public float getDateFloat()
    {
        return dateFloat;
    }

    public void setDateFloat(float dateFloat)
    {
        this.dateFloat = dateFloat;
    }

    private long timestamp;
    private float dateFloat;
    private float temp;
    private String sensorId;

    public TemperatureMeasurement()
    {

    }

    public TemperatureMeasurement(String sensorId, int tempMin, int tempMax)
    {
        this.timestamp = new Date().getTime();
        this.temp = new Random().nextFloat() * (tempMax - tempMin) + tempMin;
        this.sensorId = sensorId;
        calculateDateFloat();
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

    public final void calculateDateFloat()
    {
        Date date = new Date(this.timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        this.setDateFloat(hour + (0.01f * (minute * 100) / 60) + (0.0001f * (second * 100) / 60));
    }
    
    

}
