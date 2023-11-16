package com.example.lab3.laptop;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "laptop", namespace = "http://lab3.pl/soap-example", propOrder = {
        "id",
        "manufacturer",
        "screenSize",
        "screenResolution",
        "screenType",
        "touchScreen",
        "processor",
        "physicalCores",
        "clockSpeed",
        "ram",
        "discStorage", "discType", "graphicCard", "graphicCardMemory", "os", "discReader"
})
@Entity
@Table(name = "laptops")
@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @XmlElement(required = true)
    private String manufacturer;
    @XmlElement(required = true)
    private String screenSize;
    @XmlElement(required = true)
    private String screenResolution;
    @XmlElement(required = true)
    private String screenType;
    @XmlElement(required = true)
    private String touchScreen;
    @XmlElement(required = true)
    private String processor;
    @XmlElement(required = true)
    private String physicalCores;
    @XmlElement(required = true)
    private String clockSpeed;
    @XmlElement(required = true)
    private String ram;
    @XmlElement(required = true)
    private String discStorage;
    @XmlElement(required = true)
    private String discType;
    @XmlElement(required = true)
    private String graphicCard;
    @XmlElement(required = true)
    private String graphicCardMemory;
    @XmlElement(required = true)
    private String os;
    @XmlElement(required = true)
    private String discReader;


    public Laptop(String manufacturer, String screenSize, String screenResolution, String screenType, String touchScreen, String processor, String physicalCores, String clockSpeed, String ram, String discStorage, String discType, String graphicCard, String graphicCardMemory, String os, String discReader) {
        this.manufacturer = manufacturer;
        this.screenSize = screenSize;
        this.screenResolution = screenResolution;
        this.screenType = screenType;
        this.touchScreen = touchScreen;
        this.processor = processor;
        this.physicalCores = physicalCores;
        this.clockSpeed = clockSpeed;
        this.ram = ram;
        this.discStorage = discStorage;
        this.discType = discType;
        this.graphicCard = graphicCard;
        this.graphicCardMemory = graphicCardMemory;
        this.os = os;
        this.discReader = discReader;
    }


    public String getValueByField(String field) {
        if(field.equals("manufacturer")) {
            return this.getManufacturer();
        }
        else if(field.equals("screenSize")) {
            return this.getScreenSize();
        }
        else if(field.equals("screenResolution")) {
            return this.getScreenResolution();
        }
        else if(field.equals("screenType")) {
            return this.getScreenType();
        }
        else if(field.equals("processor")) {
            return this.getProcessor();
        }
        else if(field.equals("touchScreen")) {
            return this.getTouchScreen();
        }
        else if(field.equals("physicalCores")) {
            return this.getPhysicalCores();
        }
        else if(field.equals("clockSpeed")) {
            return this.getClockSpeed();
        }
        else if(field.equals("ram")) {
            return this.getRam();
        }
        else if(field.equals("discStorage")) {
            return this.getDiscStorage();
        }
        else if(field.equals("discType")) {
            return this.getDiscType();
        }
        else if(field.equals("graphicCard")) {
            return this.getGraphicCard();
        }
        else if(field.equals("graphicCardMemory")) {
            return this.getGraphicCardMemory();
        }
        else if(field.equals("os")) {
            return this.getOs();
        }
        else if(field.equals("discReader")) {
            return this.getDiscReader();
        }
        else return "";
    }

    public String getValue(String method) {
        if(method.equals("getManufacturer")) {
            return this.getManufacturer();
        }
        else if(method.equals("getScreenSize")) {
            return this.getScreenSize();
        }
        else if(method.equals("getScreenResolution")) {
            return this.getScreenResolution();
        }
        else if(method.equals("getScreenType")) {
            return this.getScreenType();
        }
        else if(method.equals("getProcessor")) {
            return this.getProcessor();
        }
        else if(method.equals("getTouchScreen")) {
            return this.getTouchScreen();
        }
        else if(method.equals("getPhysicalCores")) {
            return this.getPhysicalCores();
        }
        else if(method.equals("getClockSpeed")) {
            return this.getClockSpeed();
        }
        else if(method.equals("getRam")) {
            return this.getRam();
        }
        else if(method.equals("getDiscStorage")) {
            return this.getDiscStorage();
        }
        else if(method.equals("getDiscType")) {
            return this.getDiscType();
        }
        else if(method.equals("getGraphicCard")) {
            return this.getGraphicCard();
        }
        else if(method.equals("getGraphicCardMemory")) {
            return this.getGraphicCardMemory();
        }
        else if(method.equals("getOs")) {
            return this.getOs();
        }
        else if(method.equals("getDiscReader")) {
            return this.getDiscReader();
        }
        else return "";
    }

    public void setValue(String method, String value) {
        if(method.equals("setManufacturer")) {
            this.setManufacturer(value);
        }
        else if(method.equals("setScreenSize")) {
            this.setScreenSize(value);
        }
        else if(method.equals("setScreenResolution")) {
            this.setScreenResolution(value);
        }
        else if(method.equals("setScreenType")) {
            this.setScreenType(value);
        }
        else if(method.equals("setProcessor")) {
            this.setProcessor(value);
        }
        else if(method.equals("setTouchScreen")) {
            this.setTouchScreen(value);
        }
        else if(method.equals("setPhysicalCores")) {
            this.setPhysicalCores(value);
        }
        else if(method.equals("setClockSpeed")) {
            this.setClockSpeed(value);
        }
        else if(method.equals("setRam")) {
            this.setRam(value);
        }
        else if(method.equals("setDiscStorage")) {
            this.setDiscStorage(value);
        }
        else if(method.equals("setDiscType")) {
            this.setDiscType(value);
        }
        else if(method.equals("setGraphicCard")) {
            this.setGraphicCard(value);
        }
        else if(method.equals("setGraphicCardMemory")) {
            this.setGraphicCardMemory(value);
        }
        else if(method.equals("setOs")) {
            this.setOs(value);
        }
        else if(method.equals("setDiscReader")) {
            this.setDiscReader(value);
        }
    }

    @XmlElement
    public Long getId() {
        return id;
    }

    @XmlElement
    public String getManufacturer() {
        return manufacturer;
    }

    @XmlElement
    public String getScreenSize() {
        return screenSize;
    }

    @XmlElement
    public String getScreenResolution() {
        return screenResolution;
    }

    @XmlElement
    public String getScreenType() {
        return screenType;
    }

    @XmlElement
    public String getTouchScreen() {
        return touchScreen;
    }

    @XmlElement
    public String getProcessor() {
        return processor;
    }

    @XmlElement
    public String getPhysicalCores() {
        return physicalCores;
    }

    @XmlElement
    public String getClockSpeed() {
        return clockSpeed;
    }

    @XmlElement
    public String getRam() {
        return ram;
    }

    @XmlElement
    public String getDiscStorage() {
        return discStorage;
    }

    @XmlElement
    public String getDiscType() {
        return discType;
    }

    @XmlElement
    public String getGraphicCard() {
        return graphicCard;
    }

    @XmlElement
    public String getGraphicCardMemory() {
        return graphicCardMemory;
    }

    @XmlElement
    public String getOs() {
        return os;
    }

    @XmlElement
    public String getDiscReader() {
        return discReader;
    }
}
