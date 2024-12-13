package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;

import java.util.ArrayList;
import java.util.List;

import com.parking.parkinglot.entities.Car;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.logging.Logger;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    private List<CarDto> copyCarstoDto(List<Car> cars) {
        List<CarDto> carDtos = new ArrayList<>();
        cars.forEach(car -> carDtos.add(new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getUsername())));
        return carDtos;
    }

    public List<CarDto> findAllCars(){
        LOG.info("Find all cars");
        try {
            TypedQuery<CarDto> query = entityManager.createQuery(
                    "SELECT new com.parking.parkinglot.common.CarDto(c.id, c.licensePlate, c.parkingSpot, u.username) " +
                            "FROM Car c JOIN c.owner u", CarDto.class);
            List<CarDto> carDtos = query.getResultList();
            return query.getResultList();
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}
