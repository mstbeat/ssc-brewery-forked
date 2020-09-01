package guru.sfg.brewery.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeerRestControllerIT extends BaseIT {

	@Test
	void deleteBeerUrl() throws Exception {
		mockMvc.perform(delete("/api/v1/beer/28ce1169-b108-4963-a6f2-953fb7b8b4be")
				.param("apiKey", "spring").param("apiSecret", "guru"))
				.andExpect(status().isOk());
	}
	
	@Test
	void deleteBeerBadCredsUrl() throws Exception {
		mockMvc.perform(delete("/api/v1/beer/28ce1169-b108-4963-a6f2-953fb7b8b4be")
				.param("apiKey", "spring").param("apiSecret", "guruXXXX"))
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	void deleteBeerBadCreds() throws Exception {
		mockMvc.perform(delete("/api/v1/beer/28ce1169-b108-4963-a6f2-953fb7b8b4be")
				.header("Api-Key", "spring").header("Api-Secret", "guruXXXX"))
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	void deleteBeer() throws Exception {
		mockMvc.perform(delete("/api/v1/beer/28ce1169-b108-4963-a6f2-953fb7b8b4be")
				.header("Api-Key", "spring").header("Api-Secret", "guru"))
				.andExpect(status().isOk());
	}

	@Test
	void findBeers() throws Exception {
		mockMvc.perform(get("/api/v1/beer/"))
				.andExpect(status().isOk());
	}

	@Test
	void findBeerById() throws Exception {
		mockMvc.perform(get("/api/v1/beer/28ce1169-b108-4963-a6f2-953fb7b8b4be"))
				.andExpect(status().isOk());
	}

	@Test
	void findBeerByUpc() throws Exception {
		mockMvc.perform(get("/api/v1/beerUpc/0631234200036"))
				.andExpect(status().isOk());
	}

}
