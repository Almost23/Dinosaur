package com.github.HanzVu.Dinosaur;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
	public static IRCMessage parse(String message){
		String[] tokens = message.split("\\s+");
		String prefix = new String();
		List<String> params = new ArrayList<>();
		IRCCommand response = IRCCommand.NONE;
		
		int i = 0;
		if (tokens[0].subSequence(0, 1).equals(":")){
			prefix = tokens[0];
			i++;
		} else {
			prefix = null;
		}
		
		String command = tokens[i];
		i++;
		
		for (; i < tokens.length; i++)
			params.add(tokens[i]);
		
		
		if (IRCCommand.isKnown(command)){
			response = IRCCommand.getResponse(command);
		}
		
		
		int responseCode = 0;
		
		try {
			responseCode = Integer.parseInt(command);
		} catch (NumberFormatException e) {
			try {
				responseCode = IRCResponse.valueOf(command.toUpperCase()).getNumeric();
			} catch (IllegalArgumentException iae){
				
			}
		}
		
		return new IRCMessage(prefix, command, params, response);
	}
	
	
}
