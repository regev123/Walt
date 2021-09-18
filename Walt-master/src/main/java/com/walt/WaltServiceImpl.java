package com.walt;

import com.walt.dao.*;
import com.walt.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WaltServiceImpl implements WaltService {

    private static final Logger log = LoggerFactory.getLogger(WaltApplication.class);

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Delivery createOrderAndAssignDriver(Customer customer, Restaurant restaurant, Date deliveryTime) {
        Delivery delivery = null;
        //driver should be picked if he/she lives in the same city of the restaurant & customer
        //restaurant and customer must be in the same city
        if(customer.getCity().equals(restaurant.getCity())) {
            //get all Available Drivers in the same city
            List<Driver> availableDriversFromTheSameCity = availableDrivers(
                    driverRepository.findAllDriversByCity(customer.getCity())
                    ,customer.getCity(),deliveryTime);
            
            if(availableDriversFromTheSameCity.isEmpty())
                log.error("No Available Drivers!");

            else
                delivery = createDelivery(availableDriversFromTheSameCity,customer,restaurant,deliveryTime);

        }
        return delivery;
    }

    private Delivery createDelivery(List<Driver> availableDriversFromTheSameCity
                                    ,Customer customer, Restaurant restaurant, Date deliveryTime){
        Delivery delivery = null;

        //if 1 driver available make new delivery with the driver
        if(availableDriversFromTheSameCity.size()==1)
            delivery = new Delivery(availableDriversFromTheSameCity.get(0), restaurant, customer, deliveryTime);

        else
            //if there is more then one call leastBusyDriver function
            // to get the least busy driver according to the driver history
            delivery = new Delivery(leastBusyDriver(availableDriversFromTheSameCity)
                    , restaurant, customer, deliveryTime);

        driverRepository.save(delivery.getDriver());
        deliveryRepository.save(delivery);

        return delivery;
    }

    private List<Driver> availableDrivers(List<Driver> allDriversByCity,City deliverCity,Date deliveryTime) {
        //get all deliveries
        List<Delivery> allDeliveries = (List<Delivery>) this.deliveryRepository.findAll();

        //remove all deliveries from the other cities
        allDeliveries.removeIf(delivery -> !delivery.getDriver().getCity().equals(deliverCity));

        //remove driver from allDriversByCity List if he have already delivery in the same time of the new delivery
        allDeliveries.forEach(delivery -> {
            if (delivery.getDeliveryTime().compareTo(deliveryTime)==0)
                allDriversByCity.removeIf(driver -> driver.getId()==delivery.getDriver().getId());
        });

        return allDriversByCity;
    }

    private Driver leastBusyDriver(List<Driver> availableDriversFromTheSameCity) {
        //least Busy Driver filter by the driver that has the last deliveries count
        Driver leastBusyDriver = null;
        int minDeliveriesCount = Integer.MAX_VALUE;
        for(Driver d : availableDriversFromTheSameCity){
            if(d.getDeliveriesCount() < minDeliveriesCount) {
                minDeliveriesCount = d.getDeliveriesCount();
                leastBusyDriver = d;
            }
        }
        return leastBusyDriver;
    }

    @Override
    public List<DriverDistance> getDriverRankReport() {
        List<DriverDistance> sortDriverRankByTotalDistance = new ArrayList<>();
        sortDriverRankByTotalDistance.addAll((List<Driver>) this.driverRepository.findAll());
        Collections.sort(sortDriverRankByTotalDistance, new Sortbyroll() );
        return sortDriverRankByTotalDistance;
    }

    @Override
    public List<DriverDistance> getDriverRankReportByCity(City city) {
        List<DriverDistance> sortDriverRankByTotalDistanceFilterByCity = new ArrayList<>();
        sortDriverRankByTotalDistanceFilterByCity.addAll((List<Driver>) this.driverRepository.findAllDriversByCity(city));
        Collections.sort(sortDriverRankByTotalDistanceFilterByCity, new Sortbyroll() );
        return sortDriverRankByTotalDistanceFilterByCity;
    }

    class Sortbyroll implements Comparator<DriverDistance>
    {
        // Used for sorting in descending order of
        // roll totalDistance
        public int compare(DriverDistance a, DriverDistance b)
        {
            return Math.toIntExact(b.getTotalDistance() - a.getTotalDistance());
        }
    }
}
