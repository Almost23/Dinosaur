package com.github.HanzVu.Dinosaur.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.HanzVu.Dinosaur.OutputWriter;

public class OutputWriterTest {
	@Test
	public void testFormat(){
		String format = OutputWriter.format("/pong me");
		assertTrue(format.equals("PONG me "));
	}
}
