package com.walkingtree.oauth2.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.walkingtree.oauth2.entity.User;
import com.walkingtree.oauth2.models.UserTO;
import com.walkingtree.oauth2.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	
	
	public UserTO saveUser(UserTO userTo){
		BCryptPasswordEncoder bcryptPassword=new BCryptPasswordEncoder();
	User user=	modelMapper.map(userTo, User.class);
	user.setPassword(bcryptPassword.encode(user.getPassword()));
	User savedUser=userRepository.save(user);
		return modelMapper.map(savedUser, UserTO.class);
		
	}

	public UserTO getUserByEmailId(String userName) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(userName);
		modelMapper.getConfiguration().setFieldMatchingEnabled(true);
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PROTECTED);
		modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
		return modelMapper.map(user, UserTO.class);
	}

	public UserTO getUserByUsername(String userName) {
		// TODO Auto-generated method stub
		User user= userRepository.findByUsername(userName);
		UserTO userto= modelMapper.map(user, UserTO.class);
		return userto;
	}
	
	public UserTO getUserById(long userId){
		User user=  userRepository.findById(userId);
		UserTO userto= modelMapper.map(user, UserTO.class);
		return userto;
	}
	
	/*public UserTO getUser(String authtoken){
		
	}*/
}
