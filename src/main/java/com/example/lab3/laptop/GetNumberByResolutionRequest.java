package com.example.lab3.laptop;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "resolution"
})
@XmlRootElement(name = "getNumberByResolutionRequest", namespace = "http://lab3.pl/soap-example")
@Getter @Setter
public class GetNumberByResolutionRequest {

    protected String resolution;


}
