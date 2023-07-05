package model.user;

public class UserVo {
	private String user_email;
    private String user_password;
    private String user_name;
    
	public UserVo(String user_email, String user_password, String user_name) {
		super();
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public String getUser_name() {
		return user_name;
	}
	
}
