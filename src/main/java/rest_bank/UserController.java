package rest_bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
    private UserService userService;
    
    @Autowired
    private SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public User registration(@RequestParam(value="username") String username, 
    		@RequestParam(value="password") String password, 
    		@RequestParam(value="passwordConfirm") String passwordConfirm) {
    	
    	User newUser = new User();
    	newUser.setUsername(username);
    	newUser.setPassword(password);
    	newUser.setPasswordConfirm(passwordConfirm);
    	
    	userService.save(newUser);
        
        securityService.autologin(newUser.getUsername(), newUser.getPasswordConfirm());
        
        return newUser;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value="username") String username, 
    		@RequestParam(value="password") String password) {
    	
    	securityService.autologin(username, password);
    	
        return "OK";
    }
}
