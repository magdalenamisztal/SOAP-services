package com.example.lab3.xml;

import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "laptops")
@ToString
public class LaptopsList {

    private List<LaptopXml> laptops;

    @XmlElement(name = "laptop")
    public List<LaptopXml> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<LaptopXml> laptops) {
        this.laptops = laptops;
    }

}
