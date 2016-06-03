package org.gameprototype.tool.impl;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorUtil {
	public static String decode(Throwable e){
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
