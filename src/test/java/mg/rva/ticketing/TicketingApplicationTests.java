package mg.rva.ticketing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TicketingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void createUser() throws Exception {
		String requestBody = "{\n" +
				"    \"username\":\"userA\",\n" +
				"    \"email\":\"emailA\"\n" +
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/users")
				.accept(MediaType.APPLICATION_JSON).content(requestBody)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
	}

	@Test
	public void updateUser() throws Exception {
		String requestBody = "{\n" +
				"    \"username\":\"userB\",\n" +
				"    \"email\":\"emailB\"\n" +
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/users/1")
				.accept(MediaType.APPLICATION_JSON).content(requestBody)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
	}

	@Test
	public void getUserById() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/users/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
	}

	@Test
	public void getUsers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void createTicket() throws Exception {
		// Ticket 1
		String requestBody = "{\n" +
				"    \"title\":\"titleA\",\n" +
				"    \"description\":\"descriptionA\",\n" +
				"    \"status\":\"IN_PROGRESS\"\n" +
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/tickets")
				.accept(MediaType.APPLICATION_JSON).content(requestBody)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());

		// Ticket 2
		String requestBody2 = "{\n" +
				"    \"title\":\"title2\",\n" +
				"    \"description\":\"description2\",\n" +
				"    \"status\":\"IN_PROGRESS\"\n" +
				"}";
		RequestBuilder requestBuilder2 = MockMvcRequestBuilders
				.post("/tickets")
				.accept(MediaType.APPLICATION_JSON).content(requestBody2)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();
		MockHttpServletResponse response2 = result2.getResponse();
		System.out.println(response2.getStatus());
	}

	@Test
	public void updateTicket() throws Exception {
		String requestBody = "{\n" +
				"    \"title\":\"titleB\",\n" +
				"    \"description\":\"descriptionB\",\n" +
				"    \"status\":\"COMPLETED\"\n" +
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/tickets/1")
				.accept(MediaType.APPLICATION_JSON).content(requestBody)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
	}

	@Test
	public void getTicketById() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/tickets/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
	}

	@Test
	public void getTickets() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/tickets")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void assignTicketToUser() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/tickets/1/assign/1")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
	}

	@Test
	public void getUserTickets() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/users/1/ticket")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
	}

	@Test
	public void deleteTicket() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/tickets/2")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getStatus());
	}

}
