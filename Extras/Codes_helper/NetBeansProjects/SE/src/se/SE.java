/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

/**
 *
 * @author Placements 2018
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class SE {

	private static final String FILENAME = "C:\\Users\\Placements 2018\\Desktop\\a.txt";

	public static void main(String[] args) {

		BufferedReader br = null;
		FileReader fr = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
                        int cnt=0;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
                                if(Pattern.matches("[#]{1}/.*",sCurrentLine)){
                                  cnt++;  
                                } 
			}
                        System.out.println(cnt);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}