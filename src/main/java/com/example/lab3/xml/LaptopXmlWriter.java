package com.example.lab3.xml;

import com.example.lab3.laptop.Laptop;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LaptopXmlWriter {

    public void writeLaptopsToXml(List<Laptop> laptopList, String filePath) throws JAXBException {
        List<LaptopXml> laptops = getLaptopXmlList(laptopList);

        LaptopsList laptopsList = new LaptopsList();
        laptopsList.setLaptops(laptops);

        JAXBContext context = JAXBContext.newInstance(LaptopsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(laptopsList, new File(filePath));

        for(LaptopXml laptopXml : laptops) {
            System.out.println(laptopXml.toString());
        }

    }

    public List<LaptopXml> getLaptopXmlList(List<Laptop> laptops) {
        List<LaptopXml> laptopXmlList = new ArrayList<>();

        for(Laptop laptop : laptops) {
            LaptopXml laptopXml = fromLaptopToLaptopXml(laptop);
            laptopXmlList.add(laptopXml);
        }

        return laptopXmlList;
    }


    private LaptopXml fromLaptopToLaptopXml(Laptop laptop) {
        LaptopXml laptopXml = new LaptopXml();

        laptopXml.setManufacturer(laptop.getManufacturer());

        Screen screen = new Screen();
        screen.setResolution(laptop.getScreenResolution());
        screen.setSize(laptop.getScreenSize());
        screen.setType(laptop.getScreenType());
        screen.setTouchscreen(laptop.getTouchScreen());
        laptopXml.setScreen(screen);

        Processor processor = new Processor();
        processor.setName(laptop.getProcessor());
        processor.setPhysicalCores(laptop.getPhysicalCores());
        processor.setClockSpeed(laptop.getClockSpeed());
        laptopXml.setProcessor(processor);

        laptopXml.setRam(laptop.getRam());

        Disc disc = new Disc();
        disc.setStorage(laptop.getDiscStorage());
        disc.setType(laptop.getDiscType());
        laptopXml.setDisc(disc);

        GraphicCard graphicCard = new GraphicCard();
        graphicCard.setName(laptop.getGraphicCard());
        graphicCard.setMemory(laptop.getGraphicCardMemory());
        laptopXml.setGraphicCard(graphicCard);

        laptopXml.setOs(laptop.getOs());
        laptopXml.setDiscReader(laptop.getDiscReader());

        return laptopXml;
    }
}
