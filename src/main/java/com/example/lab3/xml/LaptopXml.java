package com.example.lab3.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@ToString
@XmlRootElement
@XmlType(propOrder = {
        "manufacturer",
        "screen",
        "processor",
        "ram",
        "disc",
        "graphicCard",
        "os",
        "discReader"
})
public class LaptopXml {

    private String manufacturer;
    private Screen screen;
    private Processor processor;
    private String ram;
    private Disc disc;
    private GraphicCard graphicCard;
    private String os;
    private String discReader;

    @XmlElement(name = "graphic_card")
    public GraphicCard getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(GraphicCard graphicCard) {
        this.graphicCard = graphicCard;
    }

    @XmlElement(name = "os", nillable = true)
    public String getOs() {
        return os;
    }

    @XmlElement(name = "disc_reader")
    public String getDiscReader() {
        return discReader;
    }

    public void setDiscReader(String discReader) {
        this.discReader = discReader;
    }


}
