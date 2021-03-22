package com.example.demoJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController


public class MonWebService {
    ArrayList<Car> cars = new ArrayList<Car>();
    CarRepository carRepository;

    @Autowired

    public MonWebService(CarRepository carRepository){
        System.out.println(carRepository);
        this.carRepository = carRepository;
        Car car = new Car();
        car.setPlateNumber("11AA22");
        carRepository.save(car);
    }


    @PostMapping("/cars")
    public void addCar(@RequestBody Car car){
        carRepository.save(car);
    }

    @GetMapping("/cars")
    ArrayList<Car> getListOfCars() {
        return cars;
    }

    @GetMapping("/cars/{plaque}")
    Car getCar(@PathVariable(value = "plaque") String immatriculation) {
        System.out.println(immatriculation);
        return null;
    }
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Car not found")  // 404
    @ExceptionHandler(Exception.class)
    public void erreur() {
        System.out.println("ici");
    }

    @GetMapping("/cars/{plateNumber}")
    public String liste(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent", required = true) boolean rent) throws Exception {
        System.out.println(plateNumber);
        System.out.println(rent);
        for(Car car: cars){
            if(car.getPlateNumber().equals(plateNumber)){
                return "zregrze";
            }
        }
        throw new Exception();
    }
}