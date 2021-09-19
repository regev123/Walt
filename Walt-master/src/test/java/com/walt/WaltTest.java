package com.walt;

import com.walt.dao.*;
import com.walt.model.*;
import org.assertj.core.util.Lists;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class WaltTest {

    @TestConfiguration
    static class WaltServiceImplTestContextConfiguration {

        @Bean
        public WaltService waltService() {
            return new WaltServiceImpl();
        }
    }

    @Autowired
    WaltService waltService;

    @Resource
    CityRepository cityRepository;

    @Resource
    CustomerRepository customerRepository;

    @Resource
    DriverRepository driverRepository;

    @Resource
    DeliveryRepository deliveryRepository;

    @Resource
    RestaurantRepository restaurantRepository;

    @BeforeEach()
    public void prepareData(){

        City jerusalem = new City("Jerusalem");
        City tlv = new City("Tel-Aviv");
        City bash = new City("Beer-Sheva");
        City haifa = new City("Haifa");

        cityRepository.save(jerusalem);
        cityRepository.save(tlv);
        cityRepository.save(bash);
        cityRepository.save(haifa);

        createDrivers(jerusalem, tlv, bash, haifa);

        createCustomers(jerusalem, tlv, haifa);

        createRestaurant(jerusalem, tlv);

        setDelivery();

    }

    private void createRestaurant(City jerusalem, City tlv) {
        Restaurant meat = new Restaurant("meat", jerusalem, "All meat restaurant");
        Restaurant vegan = new Restaurant("vegan", tlv, "Only vegan");
        Restaurant cafe = new Restaurant("cafe", tlv, "Coffee shop");
        Restaurant chinese = new Restaurant("chinese", tlv, "chinese restaurant");
        Restaurant mexican = new Restaurant("restaurant", tlv, "mexican restaurant ");

        restaurantRepository.saveAll(Lists.newArrayList(meat, vegan, cafe, chinese, mexican));
    }

    private void createCustomers(City jerusalem, City tlv, City haifa) {
        Customer beethoven = new Customer("Beethoven", tlv, "Ludwig van Beethoven");
        Customer mozart = new Customer("Mozart", jerusalem, "Wolfgang Amadeus Mozart");
        Customer chopin = new Customer("Chopin", haifa, "Frédéric François Chopin");
        Customer rachmaninoff = new Customer("Rachmaninoff", tlv, "Sergei Rachmaninoff");
        Customer bach = new Customer("Bach", tlv, "Sebastian Bach. Johann");

        customerRepository.saveAll(Lists.newArrayList(beethoven, mozart, chopin, rachmaninoff, bach));
    }

    private void createDrivers(City jerusalem, City tlv, City bash, City haifa) {
        Driver mary = new Driver("Mary", tlv);
        Driver patricia = new Driver("Patricia", tlv);
        Driver jennifer = new Driver("Jennifer", haifa);
        Driver james = new Driver("James", bash);
        Driver john = new Driver("John", bash);
        Driver robert = new Driver("Robert", jerusalem);
        Driver david = new Driver("David", jerusalem);
        Driver daniel = new Driver("Daniel", tlv);
        Driver noa = new Driver("Noa", haifa);
        Driver ofri = new Driver("Ofri", haifa);
        Driver nata = new Driver("Neta", jerusalem);

        driverRepository.saveAll(Lists.newArrayList(mary, patricia, jennifer, james, john, robert, david, daniel, noa, ofri, nata));
    }

    private void setDelivery() {

        Delivery delivery1 = new Delivery(  driverRepository.findByName("Robert")
                                            ,restaurantRepository.findByName("meat")
                                            ,customerRepository.findByName("Mozart")
                                            ,new Date(2021-1900,8,17,13,0));
        driverRepository.save(delivery1.getDriver());
        deliveryRepository.save(delivery1);

        Delivery delivery2 = new Delivery(  driverRepository.findByName("David")
                                            ,restaurantRepository.findByName("meat")
                                            ,customerRepository.findByName("Mozart")
                                            ,new Date(2021-1900,8,17,13,0));
        driverRepository.save(delivery2.getDriver());
        deliveryRepository.save(delivery2);

        Delivery delivery3 = new Delivery(  driverRepository.findByName("Neta")
                                            ,restaurantRepository.findByName("meat")
                                            ,customerRepository.findByName("Mozart")
                                            ,new Date(2021-1900,8,17,13,0));
        driverRepository.save(delivery3.getDriver());
        deliveryRepository.save(delivery3);

        Delivery delivery4 = new Delivery(  driverRepository.findByName("Robert")
                                            ,restaurantRepository.findByName("meat")
                                            ,customerRepository.findByName("Mozart")
                                            ,new Date(2021-1900,8,17,14,0));
        driverRepository.save(delivery4.getDriver());
        deliveryRepository.save(delivery4);

        Delivery delivery5 = new Delivery(  driverRepository.findByName("David")
                                            ,restaurantRepository.findByName("meat")
                                            ,customerRepository.findByName("Mozart")
                                            ,new Date(2021-1900,8,17,14,0));
        driverRepository.save(delivery5.getDriver());
        deliveryRepository.save(delivery5);

        Delivery delivery6 = new Delivery(  driverRepository.findByName("Robert")
                                            ,restaurantRepository.findByName("meat")
                                            ,customerRepository.findByName("Mozart")
                                            ,new Date(2021-1900,8,17,15,0));
        driverRepository.save(delivery6.getDriver());
        deliveryRepository.save(delivery6);

        Delivery delivery7 = new Delivery(  driverRepository.findByName("David")
                                            ,restaurantRepository.findByName("meat")
                                            ,customerRepository.findByName("Mozart")
                                            ,new Date(2021-1900,8,17,15,0));
        driverRepository.save(delivery7.getDriver());
        deliveryRepository.save(delivery7);

        Delivery delivery8 = new Delivery(  driverRepository.findByName("Robert")
                                            ,restaurantRepository.findByName("meat")
                                            ,customerRepository.findByName("Mozart")
                                            ,new Date(2021-1900,8,17,16,0));
        driverRepository.save(delivery8.getDriver());
        deliveryRepository.save(delivery8);

        Delivery delivery9 = new Delivery(  driverRepository.findByName("Mary")
                                            ,restaurantRepository.findByName("vegan")
                                            ,customerRepository.findByName("Beethoven")
                                            ,new Date(2021-1900,8,17,13,0));
        driverRepository.save(delivery9.getDriver());
        deliveryRepository.save(delivery9);

        Delivery delivery10 = new Delivery(  driverRepository.findByName("Mary")
                                            ,restaurantRepository.findByName("vegan")
                                            ,customerRepository.findByName("Beethoven")
                                            ,new Date(2021-1900,8,17,14,0));
        driverRepository.save(delivery10.getDriver());
        deliveryRepository.save(delivery10);

        Delivery delivery11 = new Delivery(  driverRepository.findByName("Patricia")
                                            ,restaurantRepository.findByName("vegan")
                                            ,customerRepository.findByName("Beethoven")
                                            ,new Date(2021-1900,8,17,14,0));
        driverRepository.save(delivery11.getDriver());
        deliveryRepository.save(delivery11);

        Delivery delivery12 = new Delivery(  driverRepository.findByName("Patricia")
                                            ,restaurantRepository.findByName("vegan")
                                            ,customerRepository.findByName("Beethoven")
                                            ,new Date(2021-1900,8,17,15,0));
        driverRepository.save(delivery12.getDriver());
        deliveryRepository.save(delivery12);
    }

    @Test
    public void testBasics(){

        assertEquals(((List<City>) cityRepository.findAll()).size(),4);
        assertEquals((driverRepository.findAllDriversByCity(cityRepository.findByName("Beer-Sheva")).size()), 2);

        //customer&restaurant city are not equal ==> return null
        assertEquals(waltService.createOrderAndAssignDriver(customerRepository.findByName("Mozart")
                                                            ,restaurantRepository.findByName("vegan")
                                                            ,new Date(2021-1900,9,17,13,0))
                                                            ,null);

        //no driver available in the restaurant&customer city ==> return null
        assertEquals(waltService.createOrderAndAssignDriver(customerRepository.findByName("Mozart")
                                                            ,restaurantRepository.findByName("meat")
                                                            ,new Date(2021-1900,8,17,13,0))
                                                            ,null);

        //only 1 driver is available and assign him to the delivery ==> return Delivery
        Delivery availableDriver1 = new Delivery(driverRepository.findByName("Neta")
                                                ,restaurantRepository.findByName("meat")
                                                ,customerRepository.findByName("Mozart")
                                                ,new Date(2021-1900,8,17,14,0));
        assertEquals(waltService.createOrderAndAssignDriver(customerRepository.findByName("Mozart")
                                                            ,restaurantRepository.findByName("meat")
                                                            ,new Date(2021-1900,8,17,14,0))
                                                            ,availableDriver1);

        //more then one is available and assign the delivery to the least busy driver ==> return Delivery
        Delivery availableDriver2 = new Delivery(driverRepository.findByName("Neta")
                                                ,restaurantRepository.findByName("meat")
                                                ,customerRepository.findByName("Mozart")
                                                ,new Date(2021-1900,8,17,16,0));
        assertEquals(waltService.createOrderAndAssignDriver(customerRepository.findByName("Mozart")
                                                            ,restaurantRepository.findByName("meat")
                                                            ,new Date(2021-1900,8,17,16,0))
                                                            ,availableDriver2);

        MatcherAssert.assertThat(waltService.getDriverRankReport(), isInDescendingOrdering());
        MatcherAssert.assertThat(waltService.getDriverRankReportByCity(cityRepository.findByName("Jerusalem")), isInDescendingOrdering());
        MatcherAssert.assertThat(waltService.getDriverRankReportByCity(cityRepository.findByName("Tel-Aviv")), isInDescendingOrdering());
    }

    private Matcher<? super List<DriverDistance>> isInDescendingOrdering()
    {
        return new TypeSafeMatcher<List<DriverDistance>>()
        {
            @Override
            public void describeTo (Description description)
            {
                description.appendText("Rank Report is not sorted!");
            }

            @Override
            protected boolean matchesSafely (List<DriverDistance> item)
            {
                for(int i = 0 ; i < item.size() -1; i++) {
                    if(item.get(i).getTotalDistance() < item.get(i+1).getTotalDistance()) return false;
                }
                return true;
            }
        };
    }
}
