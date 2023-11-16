package com.example.lab3.laptop;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id"
})
@XmlRootElement(name = "getLaptop", namespace = "http://lab3.pl/soap-example")
@Getter @Setter
public class GetLaptop {

    protected long id;


}
