package fr.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author GUENDOUZI Hicham
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

	@NotBlank
	@Email(message = "adress email not valid")
	private String email;
	@NotBlank
	@Size(min=6, message = "password must be contains min 6 character")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
}
