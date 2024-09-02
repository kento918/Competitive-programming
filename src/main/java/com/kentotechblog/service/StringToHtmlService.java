package com.kentotechblog.service;

import org.commonmark.node.Document;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.markdown.MarkdownRenderer;

public class StringToHtmlService {
	
	public static String toHtml(String postString) {
		Parser parser = Parser.builder().build();
		Node doc = parser.parse(postString);
		HtmlRenderer render = HtmlRenderer.builder().build();
		return render.render(doc);
	}
	//マークダウンへの変換は後日やることにする。今のところ直でHTMLタグのついている
	//文章で返ってくるので変えたい。
	public static String toMarkdown(String str) {
		MarkdownRenderer ernderer = MarkdownRenderer.builder().build();
		Node docu = new Document();
		Heading heading = new Heading();
		return str;
	}
	
	//type = 0 == title;
	//type = 1 == body;
	public static String cutHtmlMark(String body, int type) {
		int titleMessageLength=40;
		int bodyMessageLength = 100;
		int length = bodyMessageLength;
		String nbody = body.replaceAll("<[a-zA-Z0-9//]*>", "");
		nbody = nbody.replaceAll("&quot;", "");
		
		if(type == 0)
			length = titleMessageLength;
		if(nbody.length() >= length) {
			return nbody.substring(0,length) + "...";
		}
		return nbody;
	}
}
