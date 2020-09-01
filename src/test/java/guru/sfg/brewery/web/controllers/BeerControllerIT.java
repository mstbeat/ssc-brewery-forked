package guru.sfg.brewery.web.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeerControllerIT extends BaseIT {

	@Test
	void initCreateionFormWithSpring() throws Exception {
		mockMvc.perform(get("/beers/new").with(httpBasic("spring", "guru")))
				.andExpect(status().isOk())
				.andExpect(view().name("beers/createBeer"))
				.andExpect(model().attributeExists("beer"));
	}
	
	@Test
	void initCreateionForm() throws Exception {
		mockMvc.perform(get("/beers/new").with(httpBasic("user", "password")))
				.andExpect(status().isOk())
				.andExpect(view().name("beers/createBeer"))
				.andExpect(model().attributeExists("beer"));
	}
	
	@Test
	void initCreateionFormWithScott() throws Exception {
		mockMvc.perform(get("/beers/new").with(httpBasic("scott", "tiger")))
				.andExpect(status().isOk())
				.andExpect(view().name("beers/createBeer"))
				.andExpect(model().attributeExists("beer"));
	}
	
	@Test
	void findBeers() throws Exception {
		mockMvc.perform(get("/beers/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("beers/findBeers"))
				.andExpect(model().attributeExists("beer"));
	}

	@Test
	void findBeersWithAnnonymous() throws Exception {
		mockMvc.perform(get("/beers/find").with(anonymous()))
				.andExpect(status().isOk())
				.andExpect(view().name("beers/findBeers"))
				.andExpect(model().attributeExists("beer"));
	}

}
