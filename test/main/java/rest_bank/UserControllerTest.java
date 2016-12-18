package rest_bank;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    public void registrationSuccess() throws Exception {

		String username = "borja_test";
		String password = "11223344";
		
		performGet("/registration", buildParameters(username, password, password))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value(username));
    }

	@Test
	public void registrationError() throws Exception {
		String username = "borja_test";
		String password = "11223344";
		
		performGet("/registration", buildParameters(username, null, password))
        .andExpect(status().isBadRequest());
	}

	@Test
	public void loginSuccess() throws Exception {
		String username = "borja_login_success_test";
		String password = "11223344";
		
		// 1. Registrate User
		performGet("/registration", buildParameters(username, password, password));
		
		// 2. Login the User
		performGet("/login", buildParameters(username, password, null))
		.andExpect(status().isOk())
        .andExpect(content().string("OK"));
	}
	
	@Test
	public void loginError() throws Exception {
		String username = "borja_login_error_test";
		String password = "11223344";
		String wrongPassword = "44332211";
		
		// 1. Registrate User
		performGet("/registration", buildParameters(username, password, password));
		
		// 2. Login the User
		performGet("/login", buildParameters(username, wrongPassword, null))
		.andExpect(status().isForbidden())
		.andExpect(content().string(""));
	}
	
	private Map<String, String> buildParameters(String username, String password, String passwordConfirm) {
		Map<String, String> parameters = new HashMap<String, String>();
		
		if ( username != null && !username.isEmpty() ) {
			parameters.put("username", username);
		}
		
		if ( password != null && !password.isEmpty() ) {
			parameters.put("password", password);
		}
		
		if ( passwordConfirm != null && !passwordConfirm.isEmpty() ) {
			parameters.put("passwordConfirm", passwordConfirm);
		}
		
		return parameters;
	}
	
	private ResultActions performGet(String path, Map<String, String> parameters) throws Exception {
		MockHttpServletRequestBuilder toPerform = get(path);
		
		for ( Entry<String, String> param: parameters.entrySet() ) {
			toPerform.param(param.getKey(), param.getValue());
		}
		
		return this.mockMvc.perform(toPerform);
	}

}
