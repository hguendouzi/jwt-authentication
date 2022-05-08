package fr.auth.controller;

import fr.auth.constants.GlobalConstants;
import fr.auth.dto.LoginDto;
import fr.auth.exception.GlobalException;
import fr.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 
 * @author GUENDOUZI Hicham
 *
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	

	@PostMapping(value = "/login")
	public @ResponseBody void  login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) throws GlobalException {
		response.setHeader(GlobalConstants.ACCESS_TOKEN,loginService.login(loginDto));
	}
	
	
}
