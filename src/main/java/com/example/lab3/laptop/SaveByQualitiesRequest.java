package com.example.lab3.laptop;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "param1",
        "value1",
        "param2",
        "value2",
        "param3",
        "value3",
        "param4",
        "value4",
        "param5",
        "value5"
})
@XmlRootElement(name = "saveByQualitiesRequest", namespace = "http://lab3.pl/soap-example")
@Getter @Setter
public class SaveByQualitiesRequest {

    protected String param1;
    protected String value1;
    protected String param2;
    protected String value2;
    protected String param3;
    protected String value3;
    protected String param4;
    protected String value4;
    protected String param5;
    protected String value5;


}
