/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author inau
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String dataPath = "./data/data.txt";
        String historyPath = "./data/history.txt";
        String backupPath = "./data/backup.txt";
        SlangDictionary sld = new SlangDictionary(dataPath,historyPath,backupPath);
        ProjectManager pm = new ProjectManager();
        pm.Menu(sld);
    }
    
}
