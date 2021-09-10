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

	private final String API_BROKERS = "/api/brokers";
	
	private final String testLocation = API_BROKERS + "/2";

	@Autowired
	private MockMvc mvc;
	

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
		mvc.perform(MockMvcRequestBuilders.get(API_BROKERS).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().json("[{name:'Clear'},{name:'Vitreo'},{name:'Rico'}]"));
	}
	
	@Test
	@Order(2)
	void testGetBroker() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/brokers/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().json("{name:'Clear',commission:{perTrade:0,perExercise:0.5}}"));
	}
	
	@Test
	@Order(3)
	void testCreateBroker() throws Exception {
		BrokerDTO broker = new BrokerDTO();
		broker.setName("Toro");
		broker.setCommissionPerTrade(0.5);
		broker.setCommissionPerExercise(0.7);
		mvc.perform(MockMvcRequestBuilders.post(API_BROKERS).content(asJsonString(broker))
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(201))
			.andExpect(MockMvcResultMatchers.header().string("Location", CoreMatchers.startsWith("/api/brokers/")));
	}
	
	@Test
	@Order(4)
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
	@Order(5)
	void testDeleteBroker() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete(testLocation)
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
			
		mvc.perform(MockMvcRequestBuilders.get(testLocation).accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}

// @formatter:on

}
