package com.pristine.lombardo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class LombardoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetRegistrationPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
				.andExpect(status().isOk())
				.andExpect(view().name("registration"));
	}

	@Test
	public void testGetLoginPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}

	@Test
	public void testGetIndexPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	public void testGetClientsPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/client"))
				.andExpect(status().isOk())
				.andExpect(view().name("client-list"));
	}

	@Test
	public void testGetLoansPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/loan"))
				.andExpect(status().isOk())
				.andExpect(view().name("loan-list"));
	}

	@Test
	public void testGetPropertiesPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/property"))
				.andExpect(status().isOk())
				.andExpect(view().name("property-list"));
	}
}
