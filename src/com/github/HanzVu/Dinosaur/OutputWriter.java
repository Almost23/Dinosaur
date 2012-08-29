package com.github.HanzVu.Dinosaur;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class OutputWriter extends Thread{
	private final Host parent;
	private final BufferedWriter output;
	private Queue<String> outputQueue = new LinkedList<>();
		
	public OutputWriter(Host parent, OutputStream stream){
		this.parent = parent;
		this.output = new BufferedWriter(new OutputStreamWriter(stream));
	}
	
	
	
	public void run(){
		boolean running = true;
		while (running){
			synchronized(outputQueue){
				while (outputQueue.size() == 0){
					try {
						outputQueue.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				String line = outputQueue.remove();
				
				try {
					output.write(line);
					output.flush();
				} catch (IOException e) {
					//TODO: Handle this.
				}
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	public static String format(String line){
		String formattedLine = line;
		if (line.subSequence(0, 1).equals("/")){
			String[] words = line.split("\\s+");
			words[0] = words[0].substring(1,words[0].length()).toUpperCase();
			formattedLine = new String();
			for (String word: words){
				formattedLine += word + " ";
			}
		}
		
		System.out.println(formattedLine);
		return formattedLine;
	}
	
	public boolean write(String line){
		synchronized(outputQueue){
			outputQueue.add(format(line));
			outputQueue.notify();
		}
		return true;
	}
}
