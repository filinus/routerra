/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package us.filin.routerra.data.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.filin.routerra.data.PlaceholderTestApplication;
import us.filin.routerra.data.jpa.Fleet;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Val Filin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlaceholderTestApplication.class)
public class FleetTest {
	@Autowired
	private FleetRepository fleetRepository;

	@Test
	public void executesQueryMethodsCorrectly() {
		List<Fleet> fleetList = fleetRepository.findAll();
		assertNotNull(fleetList);
		assertFalse(fleetList.isEmpty());
	}
}
