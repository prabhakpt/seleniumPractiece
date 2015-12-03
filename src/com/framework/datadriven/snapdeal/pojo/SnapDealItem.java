package com.framework.datadriven.snapdeal.pojo;

public class SnapDealItem {
	public static class BuyItem{
		public static String BROWSER = "firefox";
		public static String URL = "http://www.snapdeal.com/";
		public static String XPATH_IDENTIFY = "xpath";
		public static String ID_IDENTIFY = "id";
		public static String LINK_IDENTIFY = "LINK";
		public static String TAG_NAME = "tagname";
		
		// xpaths
		public static String COMPUTERS_GAMES = "//div[@id='leftNavNemu']/div/div/ul/li[3]/a/span";
		public static String STORAGE= "//div[@id='leftNavNemu']/div/div/ul/li[3]/div/ul/li[3]/a/span";
		public static String TB_DEVICE = "//div[@id='2025234329']/div/div[2]/div/div/a/img";
		public static String CLOSE_CART = "//div[2]/div/div/div[2]/span/i";
		
		// links
		public static String TB_LINK = "2 TB";
		
		//ids
		public static String PIN_CHECK ="pincode-check";
		public static String PIN_CHECK_BUTTON = "pincode-check-bttn";
		public static String ADD_TO_CART = "add-cart-button-id";
		public static String BUY_BUTTON = "buy-button-id";
		public static String USERNAME = "username";
		public static String LOGIN_CONTINUE = "login-continue";
		public static String PASSWORD = "w_password";
		public static String CONFIRM_PASSWORD = "w_password-repeat";
		public static String REGISTER = "register-done";
	}
}
