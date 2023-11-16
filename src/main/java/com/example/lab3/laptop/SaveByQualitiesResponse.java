package com.example.lab3.laptop;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "number"
})
@XmlRootElement(name = "saveByQualitiesResponse", namespace = "http://lab3.pl/soap-example")
@Getter @Setter
public class SaveByQualitiesResponse {

    @XmlElement(required = true)
    protected int number;
}
