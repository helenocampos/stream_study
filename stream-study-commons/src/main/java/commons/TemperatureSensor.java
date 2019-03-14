/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

/**
 *
 * @author helenocampos
 */
public class TemperatureSensor
{

    private String id;

    public TemperatureSensor(String id){
        this.id = id;
    }
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public TemperatureMeasurement getMeasurement()
    {
        return new TemperatureMeasurement(this.id);
    }
}
