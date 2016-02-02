package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HTMLCreator {

	public static void main(String[] args) throws Exception {
		StringBuffer html = new StringBuffer();
		BufferedReader r = new BufferedReader(new InputStreamReader(HTMLCreator.class.getResourceAsStream("/html.txt")));
		String line = null;
		while ((line=r.readLine())!=null) {
			line = line.replaceAll("\\\"", "\\\\\"");
			html.append("\""+line+"\"\n+");
		}
		System.out.println(html);
	}
	
}
