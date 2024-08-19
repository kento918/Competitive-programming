package com.kentotechblog.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class StringToHtmlService {
	public static String toHtml(String post) {
		Parser parser = Parser.builder().build();
		Node doc = parser.parse(post);
		HtmlRenderer render = HtmlRenderer.builder().build();
		return render.render(doc);
	}
	
	
	//type = 0 == title;
	//type = 1 == body;
	public static String createShortTitleOrBody(String body, int type) {
		int titleMessageLength=40;
		int bodyMessageLength = 100;
		String nbody = body.replaceAll("<[a-zA-Z0-9//]*>", "");
		int length = bodyMessageLength;
		if(type == 0)
			length = titleMessageLength;
		if(nbody.length() >= length) {
			return nbody.substring(0,length) + "...";
		}
		return nbody;
	}
}
