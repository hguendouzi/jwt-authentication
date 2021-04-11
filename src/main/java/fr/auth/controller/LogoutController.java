package fr.auth.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.auth.exception.GlobalException;

/**
 * @author GUENDOUZI Hicham
 */
@RestController
public class LogoutController {


    public @ResponseBody void logout() throws GlobalException {
         SecurityContextHolder.getContext().setAuthentication(null);
    }

}
