package fr.auth.service;

import java.util.List;

import fr.auth.dto.UserDto;
import fr.auth.exception.GlobalException;

/**
 * 
 * @author hicham
 *
 */
public interface UserService {
	
	  /**
     * save or update user 
     * @param dto
     * @return userDto 
     */
    UserDto saveOrUpdateUser(UserDto dto) throws GlobalException ;
    /**
     * find an user by email
     * @param email
     * @return user found by email
     * @throws GlobalException
     */
    UserDto findByEmail(String email) throws GlobalException ;
    
    /**
     * delete user by email
     * @param email
     * @return list of rest users
     * @throws GlobalException
     */
    List<UserDto> deleteByEmail(String email) throws GlobalException ;
    
    /**
     * find all user 
     * @return list of users
     * @throws GlobalException
     */
    List<UserDto> findAllUsers() throws GlobalException ;

}
