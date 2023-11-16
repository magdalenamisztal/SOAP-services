package com.example.lab3.xml;

import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Setter
@ToString
@XmlType(propOrder = {
        "resolution",
        "size",
        "type",
        "touchscreen"
})
public class Screen {

    private String resolution;
    private String size;
    private String type;
    private String touchscreen;

    @XmlElement(name = "resolution")
    public String getResolution() { return resolution; }

    @XmlElement(name = "size")
    public String getSize() {
        return size;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    @XmlElement(name = "touchscreen")
    public String getTouchscreen() {
        return touchscreen;
    }

}
