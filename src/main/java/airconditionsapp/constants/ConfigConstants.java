package airconditionsapp.constants;

public interface ConfigConstants {
	
	//Web Security Configuration constants
    String FormLoginPage = "/login";
    String FormLogoutPage = "/logout";
    String UsernameParameter = "username";
    String PasswordParameter = "password";
    String AccessDeniedPage = "/error/403";
    String AuthEntryPoint = "/user/login";
    String LogoutUrl ="/";
    String RememberMeKey="rememberMeKey";
    String RememberMeParameter="rememberMe";
    String CookieName="cookie";
    int TokenValidation = 10000;
    String MailApiKey = "930A4A2708ACBE31904602690A2EE748ECB74DC0E5DB92ECF33ED8FC83F9CDC494F12A71C971F47DFE3149D81EDDFDCE";
}
