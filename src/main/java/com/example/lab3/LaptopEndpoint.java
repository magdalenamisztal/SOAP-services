package com.example.lab3;

import com.example.lab3.laptop.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Endpoint
public class LaptopEndpoint {

    private static final String NAMESPACE_URI ="http://lab3.pl/soap-example";


    private final LaptopServiceImpl laptopServiceImpl;
    public LaptopEndpoint(LaptopServiceImpl laptopServiceImpl) {
        this.laptopServiceImpl = laptopServiceImpl;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNumberByManufacturerRequest")
    @ResponsePayload
    public GetNumberByManufacturerResponse getNumberByManufacturer(@RequestPayload GetNumberByManufacturerRequest request) {
        int number = laptopServiceImpl.getNumberByManufacturer(request.getManufacturer());
        GetNumberByManufacturerResponse response = new GetNumberByManufacturerResponse();
        response.setNumber(number);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNumberByResolutionRequest")
    @ResponsePayload
    public GetNumberByResolutionResponse getNumberByResolution(@RequestPayload GetNumberByResolutionRequest request) {
        int number = laptopServiceImpl.countLaptopsByScreenResolution(request.getResolution());
        GetNumberByResolutionResponse response = new GetNumberByResolutionResponse();
        response.setNumber(number);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveByQualitiesRequest")
    @ResponsePayload
    public SaveByQualitiesResponse saveByQualitiesResponse(@RequestPayload SaveByQualitiesRequest request) {
        Map<String, String> qualitiesMap = new HashMap<>();
        qualitiesMap.put(request.getParam1(), request.getValue1());
        qualitiesMap.put(request.getParam2(), request.getValue2());
        qualitiesMap.put(request.getParam3(), request.getValue3());
        qualitiesMap.put(request.getParam4(), request.getValue4());
        qualitiesMap.put(request.getParam5(), request.getValue5());

        List<Laptop> laptopsWithQualities = laptopServiceImpl.getLaptopsWithQualities(qualitiesMap);
        laptopServiceImpl.saveToXML(laptopsWithQualities);

        SaveByQualitiesResponse response = new SaveByQualitiesResponse();
        response.setNumber(1);
        return response;
    }





}


/*



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLaptop")
    @ResponsePayload
    public GetResponse getLaptopById(@RequestPayload GetLaptop getLaptop) {
        Laptop laptopById = laptopServiceImpl.getLaptopById(getLaptop.getId());
        System.out.println("id: "+getLaptop.getId());
        System.out.println("laptopById: "+laptopById);
        GetResponse getResponse = new GetResponse();
        getResponse.setLaptop(laptopById);
        System.out.println("getResponse: "+getResponse);
        return getResponse;
    }*/