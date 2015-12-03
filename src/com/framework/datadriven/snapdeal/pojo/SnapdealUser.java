package com.framework.datadriven.snapdeal.pojo;

public class SnapdealUser {
	String email;
	String password;
	String confirmPassword;
	String mobileNumb;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getMobileNumb() {
		return mobileNumb;
	}
	public void setMobileNumb(String mobileNumb) {
		this.mobileNumb = mobileNumb;
	}
	
	@Override
	public String toString() {
		return "UserInfo [email=" + email + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", mobileNumb="
				+ mobileNumb + "]";
	}
	
}
