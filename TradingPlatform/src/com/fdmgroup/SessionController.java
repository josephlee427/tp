package com.fdmgroup;

public abstract class SessionController {

	protected static SessionView sv = new SessionView();
	
	public static SessionView getSv() {
		return sv;
	}

	public static void setSv(SessionView sv) {
		SessionController.sv = sv;
	}

	public abstract String giveDisplay();

	public abstract void updateView();

	public abstract void controlApp();
	
	public void exit(){
		sv.update("exiting program...");
		System.exit(0);
	}


	
	
}
