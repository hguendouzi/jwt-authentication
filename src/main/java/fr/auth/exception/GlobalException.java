package fr.auth.exception;

/**
 * 
 * @author hicham
 *
 */
public class GlobalException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 7880281795900053044L;


	private  String field;
	private  String message;
	
	
	public GlobalException() {
		super();
	}
	
	public GlobalException(String message) {
		super(message);
		this.message = message;
	}
	
	
	public GlobalException(String field,String message) {
		super(message);
		this.field = field;
		this.message = message;
		
	}



	public String getField() {
		return field;
	}



	public void setField(String field) {
		this.field = field;
	}


   
	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}
	


}
