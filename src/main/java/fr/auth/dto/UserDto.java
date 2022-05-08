package fr.auth.dto;

import fr.auth.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @author hicham
 *
 */
@Data
@NoArgsConstructor @AllArgsConstructor 
public class UserDto {
    
    private Integer id;
    private String lastName;
    private String firstName;
    @NotBlank
    @Email(message = "adress mail not valid")
    private String email;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min=6, message = "password must contain at least 6 characters")
    private String password;
    private Role role;

}
