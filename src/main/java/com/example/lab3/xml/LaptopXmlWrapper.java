package com.example.lab3.xml;

import javax.xml.bind.annotation.XmlElement;

public class LaptopXmlWrapper {

    private LaptopXml laptopXml;

    @XmlElement(name = "manufacturer")
    public String getManufacturer() {
        return laptopXml.getManufacturer();
    }

    @XmlElement(name = "screen")
    public Screen getScreen() {
        return laptopXml.getScreen();
    }

    @XmlElement(name = "processor")
    public Processor getProcessor() {
        return laptopXml.getProcessor();
    }

    @XmlElement(name = "ram")
    public String getRam() {
        return laptopXml.getRam();
    }

    @XmlElement(name = "disc")
    public Disc getDisc() {
        return laptopXml.getDisc();
    }

    @XmlElement(name = "graphic_card")
    public GraphicCard getGraphicCard() {
        return laptopXml.getGraphicCard();
    }

    @XmlElement(name = "os")
    public String getOs() {
        return laptopXml.getOs();
    }

    @XmlElement(name = "disc_reader")
    public String getDiscReader() {
        return laptopXml.getDiscReader();
    }

    public void setLaptopXml(LaptopXml laptopXml) {
        this.laptopXml = laptopXml;
    }
}
