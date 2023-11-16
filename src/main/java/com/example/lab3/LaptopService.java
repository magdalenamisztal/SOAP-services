package com.example.lab3;

import com.example.lab3.laptop.Laptop;

import java.util.List;

public interface LaptopService {


    List<Laptop> getAllLaptops();

    Laptop getLaptopById(long id);

    void addLaptops(List<Laptop> laptops);

    int getNumberByManufacturer(String manufacturer);
}
