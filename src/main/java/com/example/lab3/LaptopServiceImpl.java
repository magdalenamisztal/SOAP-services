package com.example.lab3;

import com.example.lab3.laptop.Laptop;
import com.example.lab3.xml.LaptopXmlWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LaptopServiceImpl implements LaptopService{

    private final LaptopRepository laptopRepository;

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Laptop getLaptopById(long id) {
        return laptopRepository.findById(id).get();
    }

    public void addLaptops(List<Laptop> laptops) {
        List<Laptop> savedAll = laptopRepository.saveAll(laptops);
    }

    public int getNumberByManufacturer(String manufacturer) {
        return laptopRepository.countByManufacturer(manufacturer);
    }

    public int countLaptopsByScreenResolution(String givenResolution) {

        String[] givenParts = givenResolution.split("x");
        int givenWidth = Integer.parseInt(givenParts[0]);
        int givenHeight = Integer.parseInt(givenParts[1]);
        float givenAspRatio = (float) givenWidth/givenHeight;

        int count = 0;
        String resolution = "";

        List<Laptop> laptops = laptopRepository.findAll();

        for (Laptop laptop: laptops) {
            resolution = laptop.getScreenResolution();
            if(resolution != null)
            {
                String[] parts = resolution.split("x");
                int width = Integer.parseInt(parts[0]);
                int height = Integer.parseInt(parts[1]);
                float laptopAspRatio = (float) width/height;

                if(givenAspRatio == laptopAspRatio) {
                    count++;
                }
            }
        }
        return count;
    }

    List<Laptop> getLaptopsWithQualities(Map<String, String> chosenQualities) {
        List<Laptop> laptopsBD = laptopRepository.findAll();
        List<Laptop> laptops = new ArrayList<>();

        Set<String> keys = chosenQualities.keySet();

        for(Laptop laptop: laptopsBD) {
            int i = 0;
            for(String key : keys) {
                String laptopValue = laptop.getValueByField(key);

                if(chosenQualities.get(key).equals(laptopValue)) {
                    i++;
                    if(i==5){
                        laptops.add(laptop);
                    }
                }
                else {
                    break;
                }
            }
        }

        return laptops;
    }

    public void saveToXML(List<Laptop> laptopList) {
        try {
            LaptopXmlWriter xmlWriter = new LaptopXmlWriter();
            String filePath = "laptops_5_Qualities.xml";
            xmlWriter.writeLaptopsToXml(laptopList, filePath);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
