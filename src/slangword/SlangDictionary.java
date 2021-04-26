/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author inau
 */
public class SlangDictionary {
    String filePath = "E:\\Nam4\\HK2\\LTUD - Java\\P1\\SlangWord\\slang.txt";
    String historyPath = "E:\\Nam4\\HK2\\LTUD - Java\\P1\\SlangWord\\history.txt";
    public static HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
    
    
    public void WriteToHistory(String keyword)
    {
        try {
            File f = new File(this.historyPath);
            FileOutputStream fout = new FileOutputStream(f, true);
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            
            String newLine = System.getProperty("line.separator");
            
            bout.write((keyword + newLine).getBytes());
            
            bout.close();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
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
    
//    func1
    public void SearchBySlangWords()
    {
        System.out.print("Search definition: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = s.toUpperCase();
        WriteToHistory(s);
        if (dictionary.containsKey(s)) 
        {
            ArrayList<String> meaning = dictionary.get(s);
            for (int i = 0; i < meaning.size(); i++) 
            {
            System.out.println(meaning.get(i));
            }  
        }
        else
        {
            System.out.println("This slang word does not exist");

        }
    }
    
//    func2
    public void SearchByDefinitions()
    {
        System.out.print("Search slang word: ");
        Scanner scanner = new Scanner(System.in);
        String def = scanner.nextLine();
        for (Entry<String, ArrayList<String>> slword : dictionary.entrySet()) {
            if (slword.getValue().contains(def)) {
                System.out.println(slword.getKey());
            }
        }
    }
    
//    func 3
    
    public void ShowHistory()
    {
        try {
            File f = new File(this.historyPath);
            FileInputStream fin = new FileInputStream(f);
            BufferedInputStream bin = new BufferedInputStream(fin);
 
            BufferedReader br = new BufferedReader(new InputStreamReader(bin, StandardCharsets.UTF_8));
            String line = br.readLine();
            
            while (line != null)
            {
                System.out.println(line);
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
    
//    func4
    public void AddNewSlangWord()
    {
        System.out.print("Enter new slang word: ");
        Scanner scanner = new Scanner(System.in);
        String newsl = scanner.nextLine();
        newsl = newsl.toUpperCase();
        System.out.print("Enter definition of new slang word: ");
        String newdef = scanner.nextLine();
        ArrayList<String> meaning = new ArrayList<String>();
        meaning.add(newdef);
        if (dictionary.get(newsl) != null) {
            System.out.println("Overwrite it: (Y/N) ");
            String confirm = scanner.nextLine();
            if (confirm.equals("y") ||  confirm.equals("Y"))
            {
                dictionary.put(newsl, meaning);
                System.out.println("Overwrite success");
            }
            else
            {
                ArrayList<String> arr = dictionary.get(newsl);
                for(String i:arr)
                {
                    meaning.add(i);
                }
                System.out.println("Dup success");
            }
        }
        else
        {
            dictionary.put(newsl, meaning);
            System.out.println("Success");
        }
    }
    
//    func5
    public void EditSlangWord()
    {
        System.out.println("Enter slangword you want to edit: ");
        Scanner scanner = new Scanner(System.in);
        String sledit = scanner.nextLine();
        if (dictionary.get(sledit) != null) {
            System.out.println("Not exist");
        }
        else
        {
            
        }
    }
    
//    func6
    public void DeleteSlangWord()
    {
        System.out.print("Enter slang word you want to delete: ");
        Scanner scanner = new Scanner(System.in);
        String del = scanner.nextLine();
        if (dictionary.get(del) != null) {
            System.out.println("Delete it? (Y/N) ");
            String confirm = scanner.nextLine();
            if (confirm.equals("y") ||  confirm.equals("Y"))
            {
                dictionary.remove(del);
                System.out.println("Delete success");
            }
        }
        else
        {
            System.out.println("This slang word does not exist");
        }
    }
    
//    func7
    public void ResetOriginSlangWord()
    {
        try 
        {
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
        catch (Exception ex) 
        {
            System.err.println(ex.getMessage());
        }
        System.out.println("Already reset");
    }
    
//     func8
    public void RamdomSlangWord()
    {
//        Random generator = new Random();
//        Object[] values = dictionary.values().toArray();
//        Object randomValue = values[generator.nextInt(values.length)];
//        System.out.println(randomValue);
        Object[] crunchifyKeys = dictionary.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        System.out.println("************ Random Value ************ \n" + key + " :: " + dictionary.get(key));
    }
    
//    func9
    public void FunnyQuizSlangWord()
    {
        
    }
    
//    func10
    public void FunnyQuizDefinition()
    {
        
    }
}
