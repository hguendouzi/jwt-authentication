package fr.auth.service;

import fr.auth.dto.LoginDto;
import fr.auth.exception.GlobalException;

/**
 * 
 * @author GUENDOUZI Hicham
 *
 */
public interface LoginService {

	String login(LoginDto loginDto) throws GlobalException;
}
