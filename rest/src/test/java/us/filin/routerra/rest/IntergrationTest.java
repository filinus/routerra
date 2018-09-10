package us.filin.routerra.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.filin.routerra.data.jpa.Device;
import us.filin.routerra.data.service.DeviceRepository;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class)
public class IntergrationTest {

    public class CityRepositoryIntegrationTests {

        @Autowired
        DeviceRepository repository;

        @Test
        public void devicesAreNotEmpty() {

            List<Device> result = this.repository.findAll();
            assertFalse(result.isEmpty());
        }
    }
}