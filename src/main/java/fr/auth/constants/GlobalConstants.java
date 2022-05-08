package fr.auth.constants;

/***
 * 
 * @author GUENDOUZI Hicham
 *
 */
public final class GlobalConstants {

	private GlobalConstants() {
	}

	public static final String SECRET = "iledefranceparisjwtsecret";
	public static final int EXPIRATION = 360000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String ACCESS_TOKEN="ACCESS-TOKEN";
	public static final String REFRESH_TOKEN ="REFRESH_TOKEN";
	public static final String TOKEN_HEADER = "Authorization";
	public static final int REFRESH = 9000000;

}
