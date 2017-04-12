package bean;

public class Login {

	public static boolean checkLogin(Credentials obj){
		if(obj.getUsername()=="fred"){
			return true;
		}
		else{
			return false;
		}
	}
}