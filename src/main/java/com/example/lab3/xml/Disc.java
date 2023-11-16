package com.example.lab3.xml;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class Disc {

    private String storage;
    private String type;

    @XmlElement(name = "storage")
    public String getStorage() {
        return storage;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }
}
