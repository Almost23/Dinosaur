package com.github.HanzVu.Dinosaur;

import java.util.List;

public class IRCMessage {
	private final String prefix;
	private final String command;
	private final List<String> params;
	
	private final IRCCommand response;
	
	public IRCMessage(String prefix, String command, List<String> params, IRCCommand response){
		this.prefix = prefix;
		this.command = command;
		this.params = params;
		
		this.response = response;
	}
	
	public IRCMessage(String prefix, String command, List<String> params){
		this(prefix, command, params, IRCCommand.NONE);
	}
	
	
	public boolean hasPrefix(){
		return prefix != null;
	}
	
	public String getPrefix(){
		return prefix;
	}
	
	public String getCommand(){
		return command;
	}
	
	public List<String> getParams(){
		return params;
	}
	
	public boolean responseRequired(){
		return response != IRCCommand.NONE;
	}
	
	public IRCCommand getResponse(){
		return response;
	}
}
