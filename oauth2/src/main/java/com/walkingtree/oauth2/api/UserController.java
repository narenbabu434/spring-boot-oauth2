package com.walkingtree.oauth2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkingtree.oauth2.models.UserTO;
import com.walkingtree.oauth2.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(path="/create")
	public UserTO createUser(@RequestBody UserTO userTo) {
		
		return userService.saveUser(userTo);
	}
	
	@RequestMapping(path="/get",method=RequestMethod.GET)
	public UserTO getUser(@RequestParam long id) {
		
		return userService.getUserById(id);
	}
}
