package com.github.HanzVu.Dinosaur;

public enum IRCCommand {
	NONE,
	PASS,
	NICK,
	USER,
	OPER,
	MODE,
	SERVICE,
	QUIT,
	SQUIT,
	JOIN,
	PART,
	TOPIC,
	NAMES,
	LIST,
	INVITE,
	KICK,
	PRIVMESG,
	NOTICE,
	MOTD,
	LUSERS,
	VERSION,
	STATS,
	LINKS,
	TIME,
	CONNECT,
	TRACE,
	ADMIN,
	INFO,
	SERVLIST,
	SQUERY,
	WHO,
	WHOIS,
	WHOWAS,
	KILL,
	PONG,
	PING (IRCCommand.PONG),
	ERROR,
	AWAY,
	REHASH,
	DIE,
	RESTART,
	SUMMON,
	USERS,
	WALLOPS,
	USERHOST,
	ISON;
	
	private final IRCCommand response;
	
	IRCCommand (IRCCommand response){
		this.response = response;
	}
	
	IRCCommand (){
		this.response = null;
	}
	
	public static boolean isKnown(String command){
		try {
			IRCCommand.valueOf(command.toUpperCase());
		} catch (IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	public static boolean requiresResponse(String command){
		if (isKnown(command)){
			try {
				if (IRCCommand.valueOf(command.toUpperCase()).response != null)
					return true;
			} catch (IllegalArgumentException e){};
		}
		return false;
	}
	
	public static IRCCommand getResponse(String command){
		if (requiresResponse(command)){
			IRCCommand cmd;
			try {
				cmd = IRCCommand.valueOf(command.toUpperCase()).response;
				return cmd;
			} catch (IllegalArgumentException e){System.out.println("Unkown command: " + command);}
		}
		return null;
	}
}
