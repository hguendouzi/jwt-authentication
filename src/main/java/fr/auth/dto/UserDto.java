package fr.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import fr.auth.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Size(min=6, message = "password must be contains min 6 character")
    private String password;
    private Role role;

}
