package com.Josh;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.System.out;

/**
 * This class contains standard message methods of various functionality.
 * @author Josh Jennings
 */
public class Message {

	/**
	 * This method simply adds a date to the console output.
	 */
	public static void consoleMessage(String message) {
		String timeStamp = new SimpleDateFormat("[yyyy/MM/dd|HH:mm:ss.SSS] ").format(Calendar.getInstance().getTime());
		out.println(timeStamp + message);
	}
}
