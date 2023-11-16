package com.example.lab3.laptop;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "laptop"
})
@XmlRootElement(name = "getResponse", namespace = "http://lab3.pl/soap-example")
@Getter @Setter
public class GetResponse {

    @XmlElement(required = true)
    protected Laptop laptop;
}
