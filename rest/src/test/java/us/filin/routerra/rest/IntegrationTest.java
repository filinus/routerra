package us.filin.routerra.rest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.filin.routerra.data.jpa.Device;
import us.filin.routerra.data.service.Repositories;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class)
@Ignore
public class IntegrationTest {
    @Autowired
    Repositories repositories;

    @Test
    public void devicesAreNotEmpty() {

        List<Device> result = this.repositories.getDevice().findAll();
        assertFalse(result.isEmpty());
    }
}