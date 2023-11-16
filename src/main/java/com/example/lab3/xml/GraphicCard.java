package com.example.lab3.xml;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlType;

@Setter
@ToString
@XmlType(propOrder = {
        "name",
        "memory"
})
public class GraphicCard {

    private String name;
    private String memory;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "memory")
    public String getMemory() {
        return memory;
    }
}
