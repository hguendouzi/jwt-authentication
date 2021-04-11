package fr.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.auth.dto.LoginDto;
import fr.auth.exception.GlobalException;
import fr.auth.service.LoginService;

/**
 * 
 * @author GUENDOUZI Hicham
 *
 */
@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;
	

	@PostMapping(value = "/login")
	public @ResponseBody String  login(@Valid @RequestBody LoginDto loginDto) throws GlobalException {
		return loginService.login(loginDto);
	}
	
	
}
