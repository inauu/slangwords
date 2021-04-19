/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author inau
 */
public class SlangDictionary {
    String filePath = "E:\\Nam4\\HK2\\LTUD - Java\\P1\\SlangWord\\slang.txt";
    public static HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
    
    public void GetData()
    {
        try {
            File f = new File(this.filePath);
            FileInputStream fin = new FileInputStream(f);
            BufferedInputStream bin = new BufferedInputStream(fin);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(bin, StandardCharsets.UTF_8));
            String line = br.readLine();
            
            while (line != null)
            {
                if(line.contains("`"))
                {
                    String [] str = line.split("`");
                    String [] mean = str[1].trim().split("\\s*\\|\\s*");
                    dictionary.put(str[0], new ArrayList<String>(Arrays.asList(mean)));
                }
                line = br.readLine();
            }
            fin.close();
            bin.close();
            
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    public void SearchBySlangWords()
    {
        System.out.print("What is the word you want to find? ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = s.toUpperCase();
        ArrayList<String> meaning = dictionary.get(s);
        for (int i = 0; i < meaning.size(); i++) {
            System.out.println(meaning.get(i));
        }
    }
    
    public void SearchByDefinition()
    {
        System.out.print("What is the slang word you want to find? ");
        Scanner scanner = new Scanner(System.in);
        String def = scanner.nextLine();
        for (Entry<String, ArrayList<String>> slword : dictionary.entrySet()) {
            if (slword.getValue().contains(def)) {
                System.out.println(slword.getKey());
            }
        }
    }
    
    
        
}
