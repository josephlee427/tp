package com.fdmgroup;

import java.io.IOException;

public class SessionView {

	private static String dispString;
	private static StringBuffer inBuff;
	
	public String getString() {
		return dispString;
	}

	public void setDispString(String dispString) {
		this.dispString = dispString;
	}

	public void update(String any) {
		
		inBuff = new StringBuffer();
		
		dispString = any;
		
		System.out.println(dispString);
	}

}
