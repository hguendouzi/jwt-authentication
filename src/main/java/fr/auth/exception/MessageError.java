package fr.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author hicham
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageError {
	private String code;
	private String message;
}
