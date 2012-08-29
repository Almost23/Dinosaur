package com.github.HanzVu.Dinosaur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputListener extends Thread {
	private final Host parent;
	private final BufferedReader stream;
	
	public InputListener(Host parent, InputStream stream){
		this.parent = parent;
		this.stream = new BufferedReader(new InputStreamReader(stream));
	}
	
	public void run(){
		boolean running = true;
		while (running){
			String line = null;
			try {
				line = stream.readLine();
				if (line == null)
					running = false;
				else {
					System.out.println(line);
					
					IRCMessage mesg = InputParser.parse(line);
					if (mesg.getResponse() != IRCCommand.NONE){
						parent.respond(mesg.getResponse());
					} else {
						//TODO: Handle normal cases
					}
					
				}
			} catch (IOException e) {
				//TODO: Handle this
			} 
		}
	}
}
