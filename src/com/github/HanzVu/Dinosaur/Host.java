package com.github.HanzVu.Dinosaur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Host {
	private final String hostName;
	private final int port;
	private String nick;
	
	private Socket socket;
	
	private InputListener input;
	private OutputWriter output;
	
	public Host(String hostName, int port, String nick){
		if (hostName.length() > 63); //Max host name length = 63 characters.
		this.hostName = hostName;
		this.port = port;
		this.nick = nick;
		socket = new Socket();
	}
	
	public Host(String hostName, String nick){
		this(hostName, 6667, nick);
	}
	
	public String getHostName(){
		return hostName;
	}
	
	public int getPort(){
		return port;
	}
	
	public String getNick(){
		return nick;
	}
	
	public void setNick(String nick){
		this.nick = nick;
		//TODO: send a message to change the nick.
		
	}
	
	public boolean connect(){
		try {
			socket = new Socket(hostName, port);
			
			input = new InputListener(this, socket.getInputStream());
			input.start();
			output = new OutputWriter(this, socket.getOutputStream());
			output.start();
					
			
			output.write("NICK " + nick + "\r\n");
			//output.flush();
			output.write("USER Dinosaur " + 0 + " * " + ":" + "Dinosaur" + Main.version + "\r\n");
			//output.flush();
			return true;
		} 
		
		catch (UnknownHostException e) {} 
		catch (IOException e) {}
		return false;
	}
	
	/**
	 * Gets the connected state of the host
	 * @return <code>true</code> if the connected to the host 
	 */
	public boolean isConnected(){
		return socket.isConnected();
	}
	
	
	public InputStream getInputStream(){
		try {
			return socket.getInputStream();
		} catch (IOException e) {}
		return null;
	}
	
	public OutputStream getOutputStream(){
		try {
			return socket.getOutputStream();
		} catch (IOException e){}
		return null;
	}
	
	public synchronized void respond(IRCCommand cmd){
		if (cmd == null || cmd == IRCCommand.NONE){
			return;
			
		}
		if(cmd == IRCCommand.PONG){
			String pong = "PONG " + "Dinosaur" + Main.version;
			output.write(pong + "\r\n");
			//output.flush();
			System.out.println(pong);
		
		}
	}
	
	@Override
	public boolean equals(Object other){
		if (other instanceof Host){
			Host o = (Host)other;
			return this.hostName.equals(o.getHostName());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return hostName.hashCode();
	}
}
