package com.example.lab3.xml;

import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Setter
@ToString
@XmlRootElement
@XmlType(propOrder = {
        "name",
        "physicalCores",
        "clockSpeed"
})
public class Processor {

    private String name;
    private String physicalCores;
    private String clockSpeed;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "physical_cores")
    public String getPhysicalCores() {
        return physicalCores;
    }

    @XmlElement(name = "clock_speed")
    public String getClockSpeed() {
        return clockSpeed;
    }
}
