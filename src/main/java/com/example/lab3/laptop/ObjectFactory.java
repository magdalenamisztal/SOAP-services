package com.example.lab3.laptop;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    public Laptop createLaptop() {
        return new Laptop();
    }

    public GetLaptop createGetLaptop() {
        return new GetLaptop();
    }
}
