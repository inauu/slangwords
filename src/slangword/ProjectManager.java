/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;

import java.util.Scanner;

/**
 *
 * @author inau
 */
public class ProjectManager {
    public void Menu()
    {
        Scanner scanner = new Scanner(System.in);
        char y = 'y';
        while(y == 'y')
        {
            System.out.println("\n\n\n=============================================");
            System.out.println("\t\t   MENU");
            System.out.println("=============================================");
            System.out.print("\n1.Search by slang word");
            System.out.print("\n2.Search by definition");
            System.out.print("\n3.Show history");
            System.out.print("\n4.Add new slang word");
            System.out.print("\n5.Edit slang word");
            System.out.print("\n6.Delete slang word");
            System.out.print("\n7.Reset the original slang word list");
            System.out.print("\n8.Ramdom a slang word");
            System.out.print("\n9.Funny quiz with slang word");
            System.out.print("\n10.Funny quiz with definition");
            
            System.out.print("\n\nPlease enter the function you wanna choice: ");
            Integer sel = scanner.nextInt();
            SlangDictionary sld = new SlangDictionary();
            sld.GetData();
            
            switch(sel)
            {
                case 1:
                    sld.SearchBySlangWords();
                    break;
                case 2:
                    sld.SearchByDefinitions();
                    break;
                case 3:
                    sld.ShowHistory();
                    break;
                case 4:
                    sld.AddNewSlangWord();
                    break;
                case 5:
                    sld.EditSlangWord();
                    break;
                case 6:
                    sld.DeleteSlangWord();
                    break;
                case 7:
                    sld.ResetOriginSlangWord();
                    break;
                case 8:
                    sld.RamdomSlangWord();
                    break;
                case 9:
                    sld.FunnyQuizSlangWord();
                    break;
                case 10:
                    sld.FunnyQuizDefinition();
                    break;
                case 0:
                default:
                    System.out.println("Exit");
                    System.exit(1);
                    break;
            }
            System.out.print("\n\nTo continue please choose 'y' or choose any key to exit: ");
            y = scanner.next().charAt(0);
        }
    }
}
