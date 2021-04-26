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
import java.util.Iterator;
import java.util.Map;
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
        System.out.print("\nEnter the new slang word: ");
        Scanner scanner = new Scanner(System.in);
        String newsl = scanner.nextLine();
        newsl = newsl.toUpperCase();
        ArrayList<String> meaning = new ArrayList<String>();
        System.out.print("How many definitions of it: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= amount; i++) 
        {
            System.out.print("Enter the definition " + i + ": ");
            String newdef = scanner.nextLine();
            meaning.add(newdef);
        }
        if (dictionary.get(newsl) != null) {
            System.out.println("\nOverwrite it: (Y/N) ");
            String confirm = scanner.nextLine();
            if (confirm.equals("y") ||  confirm.equals("Y"))
            {
                dictionary.put(newsl, meaning);
                System.out.println("\nOverwrite success");
            }
            else
            {
                ArrayList<String> arr = dictionary.get(newsl);
                for(String i:arr)
                {
                    meaning.add(i);
                }
                System.out.println("\nDup success");
            }
        }
        else
        {
            dictionary.put(newsl, meaning);
            System.out.println("\nAdd Successfully");
        }
    }
    
//    func5
    public void EditSlangWord()
    {
        System.out.print("\nEnter slangword you want to edit: ");
        Scanner scanner = new Scanner(System.in);
        String edit = scanner.nextLine();
        if (dictionary.get(edit) == null) {
            System.out.println("Not exist");
        }
        else
        {
            ArrayList<String> def = dictionary.get(edit);

            int amount = 1;
            for(String i:def)
            {
                System.out.println(amount + "." + i);
                amount++;
            }
            
            System.out.println("What do u want: ");
            System.out.println("1. Delete Definition ");
            System.out.println("2. Add Definition ");
            System.out.print("YOUR CHOICE: ");
            int choice=scanner.nextInt();
            String pass=scanner.nextLine();

            if (choice==1)
            {
                if (def.size()==1) 
                {
                    System.out.println("You can't delete this ");
                    return;
                }
                System.out.print("Number of item you want to remove:");
                int number = scanner.nextInt();
                def.remove(number - 1);
                dictionary.put(edit,def);
            }
            else if (choice==3)
            {
                System.out.print("What is the new definition : ");
                String temp=scanner.nextLine();
                def.add(temp);
                dictionary.put(edit,def);
            }
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
    public void RandomSlangWord()
    {
        ArrayList<String> dictionaryKey = new ArrayList<>(dictionary.keySet()); 
        int rdindex = new Random().nextInt(dictionaryKey.size());
        String slangWord = dictionaryKey.get(rdindex);
        ArrayList<String> definitions = dictionary.get(slangWord);
        System.out.println("****** On this day slang word ******");
        System.out.println("\t" + slangWord + " : " + String.join(", ", definitions));
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
