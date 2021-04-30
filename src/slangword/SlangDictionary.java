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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
    String dataPath;
    String historyPath;
    String backupPath;
    public static HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
    
    public SlangDictionary(String dataPath, String historyPath, String backupPath)
    {
        this.dataPath = dataPath;
        this.historyPath = historyPath;
        this.backupPath = backupPath;
    }
    

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
            File f = new File(this.dataPath);
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
        if (dictionary.get(s) != null) 
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
        int numOfDef = 0;

        long startTime = System.currentTimeMillis();
        for (Entry<String, ArrayList<String>> slword : dictionary.entrySet()) {
            for (String string : slword.getValue()) { //
                if(string.toUpperCase().contains(def.toUpperCase()))
                {
                    System.out.println(slword.getKey());
                    numOfDef ++;
                }
                   
            }
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("\nMethod took: " + (endTime - startTime) + "ms");
        
        if (numOfDef == 0) {
            System.out.println("ko co ");
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
            System.out.println("\nOverwrite it? (Y/N) ");
            String confirm = scanner.nextLine();
            if (confirm.equals("y") ||  confirm.equals("Y"))
            {
                dictionary.put(newsl, meaning);
                System.out.println("\nOverwritten");
            }
            else
            {
                ArrayList<String> arr = dictionary.get(newsl);
                for(String i:arr)
                {
                    meaning.add(i);
                }
                System.out.println("\nDuplicated");
            }
        }
        else
        {
            dictionary.put(newsl, meaning);
            System.out.println("\nAdded");
        }
    }
    
//    func5
    public void EditSlangWord()
    {
        System.out.print("\nEnter the slang word you want to edit: ");
        Scanner scanner = new Scanner(System.in);
        String edit = scanner.nextLine();
        if (dictionary.get(edit) == null) {
            System.out.println("It does not exist");
        }
        else
        {
            ArrayList<String> def = dictionary.get(edit);
            
            System.out.println("1. Add Definition ");
            System.out.println("2. Delete Definition ");
            System.out.print("\nWhat do you want with this slang word: ");
            int choice = scanner.nextInt();

            if (choice == 1)
            {
                System.out.print("What is the new definition: ");
                String newdef = scanner.nextLine();
                def.add(newdef);
                dictionary.put(edit,def);
            }
            else if (choice == 2)
            {
                for (int i = 0; i < def.size(); i++) {
                    System.out.println(def.get(i));
                }
                if (def.size()==1) 
                {
                    System.out.println("You can't delete this ");
                    return;
                }
                System.out.print("Number of item you want to remove: ");
                int number = scanner.nextInt();
                def.remove(number - 1);
                dictionary.put(edit,def);
                System.out.println("\nDeleted");
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
            File f = new File(this.backupPath);
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
    
    public ArrayList<String> GetDefinition(String key)
    {
        return dictionary.get(key);
    }
    
//     func8
    public String RandomSlangWord()
    {
        ArrayList<String> dictionaryKey = new ArrayList<>(dictionary.keySet()); 
        int rdindex = new Random().nextInt(dictionaryKey.size());
        String slangWord = dictionaryKey.get(rdindex);
        ArrayList<String> definitions = dictionary.get(slangWord);
        return slangWord;
    }
        
    
//    func9
    public void FunnyQuizSlangWord()
    {      
        //tao cau hoi
        String ans1 = RandomSlangWord();
        
        //tao cau tra loi
        int ansNumber = 4;
        ArrayList<String> ans = new ArrayList<>();
        
        //dap an
        String result = String.join(", ",  GetDefinition(ans1));
        ans.add(result);
        
        // tao them 3 cau tra loi
        while (ans.size() < ansNumber) {
            String keyAnswer = RandomSlangWord();
            String definition = String.join(", ",  GetDefinition(keyAnswer));
            
            // kiem tra co trung voi cau tra loi da co 
            if(ans.contains(definition)){
                continue;
            }
            ans.add(definition);
        }
        // xao mang
        Collections.shuffle(ans);
        //in ra man hinh
        String a1 = "A." + ans.get(0);
        String a2 = "B." + ans.get(1);
        String a3 = "C." + ans.get(2);
        String a4 = "D." + ans.get(3);
        
        System.out.println("\nChoose the slang word for the definition below: ");
        System.out.println(ans1);
        
        System.out.println("\n" + a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);
        //lay cau tra loi
        System.out.print("Your answer is: ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        answer = answer.toUpperCase();
        
        //kiem tra cau tra loi
        if (answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D")) {
            int index = 0;
            if (answer.equals("B")) {
                index = 1;
            }
            else if (answer.equals("C")) {
               index = 2;
            }
            else if(answer.equals("D")){
               index = 3;
            }
            
            if(ans.get(index).equals(result) ){
                System.out.println("Correct");
                return;
            }
        }
        System.out.println("Incorrect");
        System.out.println("The answer is: " + result);        
    }        

    
//    func10
    public void FunnyQuizDefinition()
    {
        //tao cau hoi
        String ans1 = RandomSlangWord();
        String Question = String.join(", ",  GetDefinition(ans1));
        
        //tao cau tra loi
        int ansNumber = 4;
        ArrayList<String> ans = new ArrayList<>();
        
        //dap an
        String result = ans1;
        ans.add(result);
        
        // tao them 3 cau tra loi
        while (ans.size() < ansNumber) {
            String keyAnswer = RandomSlangWord();
            
            // kiem tra co trung voi cau tra loi da co 
            if(ans.contains(keyAnswer)){
                continue;
            }
            ans.add(keyAnswer);
        }
        // xao mang
        Collections.shuffle(ans);
        
        //in ra man hinh
        String a1 = "A." + ans.get(0);
        String a2 = "B." + ans.get(1);
        String a3 = "C." + ans.get(2);
        String a4 = "D." + ans.get(3);
        
        System.out.println("\nChoose the slang word for the definition below: ");
        System.out.println(Question);
        
        System.out.println("\n\n" + a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);
        //lay cau tra loi
        System.out.print("Your answer is: ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        answer = answer.toUpperCase();
        
        //kiem tra cau tra loi
        if (answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D")) {
            
            int index = 0;
            if (answer.equals("B")) {
                index = 1;
            }
            else if (answer.equals("C")) {
               index = 2;
            }
            else if(answer.equals("D")){
               index = 3;
            }
            
            if(ans.get(index).equals(result) ){
                System.out.println("Correct");
                return;
            }
        }
        System.out.println("Incorrect");
        System.out.println("The answer is: " + result);        
    }        
    
    public void SaveCurrentDictionary() throws IOException{
//        key: defintions 

        // tao file this.data
        try {
            
            //empty file
            
            File f = new File(this.dataPath);
            new PrintWriter(f).close();

            FileOutputStream fos = new FileOutputStream(f,true);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
        // xoa het danh sach trong file data
            
        // bat dau ghi lai
        for (Entry<String, ArrayList<String>> slWord : dictionary.entrySet()) {
            String line = slWord.getKey() +"`" + String.join("|", slWord.getValue());
            bos.write((line + System.lineSeparator()).getBytes());
        }
         
        bos.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
    
    }
}
