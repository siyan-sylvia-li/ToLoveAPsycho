package com.example.sylviali.toloveapsycho;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainReader {
	public ArrayList<Page> bookPages = new ArrayList<>();
	
	public void extractor(String content) throws IOException {
		String page = "\"Page ";
		content = content.replaceAll("<br/>", "");
		content = content.replaceAll("<description>", "|1");
		content = content.replaceAll("</description>", "|2");
		String statement = "";
		while (content.contains(page)) {
			content = content.substring(content.indexOf(page));
			//System.out.println(content);
			String numStr = content.substring(6, content.indexOf("\">"));
			//System.out.println(pageNum);
			statement = content.substring(
					content.indexOf("|1") + 2, content.indexOf("|2"));
			if (statement.contains("CDATA")) {
				statement = statement.substring(
						9, statement.length() - 3);
			}
			//System.out.println(statement);
			content = content.substring(content.indexOf("</object>"));
			bookPages.add(fileWriter(statement, numStr));
		}
	}
	
	public Page fileWriter(String statement, String pageNum) throws IOException {
		Page thisPage = new Page();
		//File file = new File("Story.txt");
		//BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		String[] options = statement.split("Page ");
		Node mother = new Node(options[0], pageNum);
		thisPage.setMother(mother);
		for (String option : options) {
			//System.out.println(option);
			if ((Character.isDigit(option.charAt(0)))
					&& (option.contains(" "))) {
				String linkNum = option.substring(0, option.indexOf(" ")).replaceAll("\n", "");
				option = option.substring(option.indexOf(" ") + 1);
				String des = option.substring(option.indexOf(" ") + 1).replaceAll("\n", "");
				Node newNode = new Node(des, linkNum);
				thisPage.addOption(newNode);
			}
		}
		
		//System.out.println(thisPage);
		return thisPage;
		//writer.write(thisPage.toString());
	}


}
