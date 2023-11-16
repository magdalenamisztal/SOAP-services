package com.example.lab3;

import com.example.lab3.laptop.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    @Query("SELECT COUNT(l) FROM Laptop l WHERE l.manufacturer = :manufacturer")
    int countByManufacturer(@Param("manufacturer") String manufacturer);

}
