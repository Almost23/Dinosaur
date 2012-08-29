package com.github.HanzVu.Dinosaur;

public class Main {
	public static final String version = "0.1.1";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Host freenode = new Host("irc.freenode.net", "Almondy1234");
		freenode.connect();
	}

}
