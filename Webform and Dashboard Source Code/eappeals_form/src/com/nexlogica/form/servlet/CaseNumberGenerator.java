package com.nexlogica.form.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.nexlogica.form.server.configreader.propertyFileReader;

public class CaseNumberGenerator {

	private static int caseNumber = 1;

	// Case number is retrieved from a file called number.txt that is located
	// at the filepath in config.properties
	// it will create the file if it does not exist
	public static int getNewCaseNumber() throws IOException {

		String filePathPrefix = new propertyFileReader().getLocationProperty();
		String caseNumberFilePath = filePathPrefix + "number.txt";
		File caseNumberFile = new File(caseNumberFilePath);

		// if the file does not exist, create a new one with caseNumber
		if(!caseNumberFile.exists()){
			
			caseNumberFile.createNewFile();

			PrintWriter w = new PrintWriter(caseNumberFile);

			w.print(Integer.toString(caseNumber));
			
			w.close();

		} else {
			
			FileReader fr = new FileReader(caseNumberFile);
			BufferedReader br = new BufferedReader(fr);

			// read number from text file
			String line = br.readLine();
			
			br.close();

			// string -> int
			caseNumber = Integer.parseInt(line);
			
			caseNumber++;
			
			// output stream to overwrite file
			FileWriter fo = new FileWriter(caseNumberFile, false);
			
			// rewrite new number to file
			fo.write(Integer.toString(caseNumber));
			
			fo.close();

		}

		return caseNumber;
	}
	
	public static int getCurrentCaseNumber() throws IOException {
		
		String filePathPrefix = new propertyFileReader().getLocationProperty();
		String caseNumberFilePath = filePathPrefix + "number.txt";
		File caseNumberFile = new File(caseNumberFilePath);
		
		FileReader fr = new FileReader(caseNumberFile);
		BufferedReader br = new BufferedReader(fr);

		// read number from text file
		String line = br.readLine();
		
		br.close();

		// string -> int
		caseNumber = Integer.parseInt(line);
		
		return caseNumber;
	}



}
