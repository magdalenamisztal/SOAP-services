package com.example.lab3.laptop;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "number"
})
@XmlRootElement(name = "getNumberByResolutionResponse", namespace = "http://lab3.pl/soap-example")
@Getter @Setter
public class GetNumberByResolutionResponse {

    @XmlElement(required = true)
    protected int number;
}
