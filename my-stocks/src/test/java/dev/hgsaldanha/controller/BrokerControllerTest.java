package dev.hgsaldanha.controller;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.hgsaldanha.dto.BrokerDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class BrokerControllerTest {

	@Autowired
	private MockMvc mvc;

	private String testLocation = "/api/brokers/2";

	private String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

// @formatter:off
 
	@Test
	@Order(1)
	void testGetBrokers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/brokers").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().json("[{name:'Clear'},{name:'Vitreo'},{name:'Rico'}]"));
	}
	
	@Test
	void testGetBroker() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/brokers/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().json("{name:'Clear',commission:{perTrade:0,perExercise:0.5}}"));
	}
	
	@Test
	void testCreateBroker() throws Exception {
		BrokerDTO broker = new BrokerDTO();
		broker.setName("Toro");
		broker.setCommissionPerTrade(0.5);
		broker.setCommissionPerExercise(0.7);
		testLocation = mvc.perform(MockMvcRequestBuilders.post("/api/brokers").content(asJsonString(broker))
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(201))
			.andExpect(MockMvcResultMatchers.header().string("Location", CoreMatchers.startsWith("/api/brokers/")))
			.andReturn().getResponse().getHeader("Location");
	}
	
	@Test
	void testUpdateBroker() throws Exception {
		BrokerDTO broker = new BrokerDTO();
		broker.setName("Ágora");
		broker.setCommissionPerTrade(0.1);
		broker.setCommissionPerExercise(0.2);
		mvc.perform(MockMvcRequestBuilders.put(testLocation).content(asJsonString(broker))
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
		
		mvc.perform(MockMvcRequestBuilders.get(testLocation).accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.content().json("{name:'Ágora',commission:{perTrade:0.1,perExercise:0.2}}"));
	}
	
	@Test
	void testDeleteBroker() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete(testLocation)
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
			
		mvc.perform(MockMvcRequestBuilders.get(testLocation).accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}

// @formatter:on

}
