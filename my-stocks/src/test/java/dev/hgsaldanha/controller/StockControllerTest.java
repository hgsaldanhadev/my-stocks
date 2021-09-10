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

import dev.hgsaldanha.dto.StockDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class StockControllerTest {

	private static final String API_STOCKS = "/api/stocks";

	private final String testLocation = API_STOCKS + "/2";
	
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
	void testGetStocks() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(API_STOCKS).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().json("[{name:'Petrobrás'},{name:'Méliuz'},{name:'Vale'}]"));
	}
	
	@Test
	@Order(2)
	void testGetStock() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(API_STOCKS + "/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().json("{name:'Petrobrás',ticker: 'PETR4'}"));
	}
	
	@Test
	@Order(3)
	void testCreateStock() throws Exception {
		StockDTO stock = new StockDTO();
		stock.setName("Natura");
		stock.setTicker("NATU");
		mvc.perform(MockMvcRequestBuilders.post(API_STOCKS).content(asJsonString(stock))
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(201))
			.andExpect(MockMvcResultMatchers.header().string("Location", CoreMatchers.startsWith("/api/stocks/")));
	}
	
	@Test
	@Order(4)
	void testUpdateStock() throws Exception {
		StockDTO stock = new StockDTO();
		stock.setName("O Boticário");
		stock.setTicker("NATU");
		mvc.perform(MockMvcRequestBuilders.put(testLocation).content(asJsonString(stock))
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
		
		mvc.perform(MockMvcRequestBuilders.get(testLocation).accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.content().json("{name:'O Boticário',ticker:'NATU'}"));
	}
	
	@Test
	@Order(5)
	void testDeleteStock() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete(testLocation)
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
			
		mvc.perform(MockMvcRequestBuilders.get(testLocation).accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404));
	}

// @formatter:on

}
