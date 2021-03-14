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
	public static final String TOKEN_PREFIX = "Paris ";
	public static final String TOKEN_HEADER = "Authorization";
	public static final int REFRESH = 9000000;

}
