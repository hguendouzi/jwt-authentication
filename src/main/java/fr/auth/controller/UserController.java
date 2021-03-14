/**
 * 
 */
package fr.auth.controller;



import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.auth.dto.UserDto;
import fr.auth.exception.GlobalException;
import fr.auth.service.UserService;

/**
 * @author hicham
 * controller class
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired private UserService service;
    
    /**
     *  create or update user 
     * @param userDto
     * @return user with id
     * @throws GlobalException
     */
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public  @ResponseBody UserDto saveOrUpdateUser(@Valid @RequestBody UserDto userDto) throws GlobalException {
	return service.saveOrUpdateUser(userDto);
    }	
    
    
    
    /**
     * get all users 
     * @return list of user
     */
    @GetMapping(value = "/all")
	public  @ResponseBody List<UserDto> findAllUsers() throws GlobalException  {
		return service.findAllUsers();
	}
    
    /**
     * find user by email
     * @param email
     * @return userDto
     */
    @GetMapping(value = "/{email}")
	public  @ResponseBody UserDto findUserByUserame(@Valid @Email @PathVariable String email ) throws GlobalException   {
		return service.findByEmail(email);
	}
    
    /**
     * delete user by email
     * @param email
     * @return list of user 
     */
    @DeleteMapping(value = "/delete/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody List<UserDto> deleteUser(@Valid @Email @PathVariable String email) throws GlobalException {
	return service.deleteByEmail(email);
    }

}
