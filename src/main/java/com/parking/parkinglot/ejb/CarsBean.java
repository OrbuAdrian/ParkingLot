package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
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

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> carDto = new ArrayList<>();
        cars.forEach(car -> carDto.add(new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getUsername())));
        return carDto;
    }

    public List<CarDto> findAllCars(){
        LOG.info("Find all cars");
        try {
            TypedQuery<CarDto> query = entityManager.createQuery(
                    "SELECT new com.parking.parkinglot.common.CarDto(c.id, c.licensePlate, c.parkingSpot, u.username) " +
                            "FROM Car c JOIN c.owner u", CarDto.class);
            List<CarDto> carDto = query.getResultList();
            return query.getResultList();
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);
    }

    public void deleteCarsByIds(Collection<Long> carIds) {
        LOG.info("deleteCarsByIds");

        for (Long carId : carIds) {
            Car car = entityManager.find(Car.class, carId);
            entityManager.remove(car);
        }
    }

    public CarDto findById(Long id){
        LOG.info("findById");

        Car car = entityManager.find(Car.class, id);
        if (car == null) {
            throw new EJBException("Car not found for ID: " + id);
        }

        return new CarDto(
                car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot(),
                car.getUsername()
        );
    }
}
