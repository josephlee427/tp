package com.fdmgroup;

public enum Role {
	SHAREHOLDER, HYBRID, COMPANY, ADMIN, BROKER;

	public static int mapRole(Role role) {
		switch (role) {
		case ADMIN:
			return 1;
		case SHAREHOLDER:
			return 2;
		case COMPANY:
			return 3;
		case BROKER:
			return 4;
		case HYBRID:
			return 5;
		}
		return 0;
	}

	public static Role mapRole(int role) {
		switch (role) {
		case 1:
			return ADMIN;
		case 2:
			return SHAREHOLDER;
		case 3:
			return COMPANY;
		case 4:
			return BROKER;
		case 5:
			return HYBRID;
		}
		return null;
	}
}
