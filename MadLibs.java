// Hallie Dunham
// Assignment 5
// guessing game
// M750
// Version 1  
// 11/27/13

import java.util.*;
import java.io.*;

public class MadLibs
{
   
   /**this is the main method that calls the intro, then uses a while loop to 
     *continuously ask what the user wants to do and call methods for the requested action
     *@param args  the command line arguments that are passed into the program
     */
   public static void main (String args[])
   {
      //File foutput=null;
      intro();
      String action="";
      while(!action.equals("c")||!action.equals("v")||!action.equals("q"))
      {
         action=request("\n(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ").toLowerCase();
         if (action.equals("c"))
         {
            Scanner finscan=makeinputscanner();
            PrintStream fileprint=makeprinter();
            createmadlib(finscan,fileprint);
            action="";
         }
         if(action.equals("v"))
         {
            view();
            action="";
         }
         if(action.equals("q"))
         {
            System.out.print("\nThanks for playing!");
            System.exit(0);
            
         }
      }
   }
   
   /**this method prints the intro paragraph
     */
   public static void intro()
   {
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.\n");
   }
   
   /**this method requests input from the user
     *@param prompt  the prompt that is printed to the console
     *@return response  the user's response to the prompt
     */
   public static String request(String prompt)
   {
      Scanner user=new Scanner(System.in);
      System.out.print(prompt);
      if(user.hasNextLine())
      {
         String response=user.nextLine();
         return response;
      }
      return null;
   }
   
   /**this method gets the name of the input file from he user 
     *and creates the file object then puts a scanner on the file, 
     *but if there is a FileNotFoundException the user is reprompted
     *@return finscan  the Scanner on the input file
     */
   public static Scanner makeinputscanner()
   {
      String inname=request("Input file name: ");
      File finput=null;
      Scanner finscan=null;
      while (true)
      {
         finput=new File(inname);
         try
         {
            finscan=new Scanner(finput);
            return finscan;
         }
         catch(FileNotFoundException e)
         {
            inname=request("File not found. Try again: ");
         }
      }
   }
   
   /**this method gets the name of the output file from the user 
     *and creates the file object then puts a printstream on the file,
     *and if there is a FileNotFoundException it is caught
     *@return fileprint  the printstream to the output file
     */
   public static PrintStream makeprinter()
   {
      String outname=request("Output file name: ");
      File foutput=new File(outname);
      PrintStream fileprint=null;
      try
      {
         fileprint=new PrintStream(foutput);
      }
      catch (FileNotFoundException e)
      {
      }
      System.out.println("");
      return fileprint;
   }
   
   /**this method uses the scanner and printstream to read the input
     *file, ask the user for input when there is a <part-of-speech>, 
     *and rewrite the mad lib into another file with the replacenments
     *@param s  the scanner passed in to get lines of the input file
     *@param p  the printstream passed in to print the output madlib
     */
   public static void createmadlib(Scanner s, PrintStream p)
   {
      String line = "";
      String word = "";
      String article = "";
      String blank = "";
      Scanner linescan=null;
      while(s.hasNextLine())
      {
         line = s.nextLine();
         linescan=new Scanner(line);
         while(linescan.hasNext())
         {
            word=linescan.next();
            if(word.startsWith("<")&&word.endsWith(">"))
            {
               word=word.substring(1,word.length()-1);
               word=word.replace("-"," ");
               if(startswvowel(word))
               {
                  article="an ";
               }
               else
               {
                  article="a ";
               }
               word=request("Please type "+article+word+": ");
            }
            p.print(word+" ");
         }
         p.println("");
      }
      System.out.println("Your mad-lib has been created!");
   }
   
   /**this method determines whether a string starts with a vowel and returns true or false
     *@param s  the string that is passed in to be evaluated
     *@return 
     */
   public static boolean startswvowel(String s)
   {
      if(s.startsWith("a")||s.startsWith("e")||s.startsWith("i")||s.startsWith("o")||s.startsWith("u"))
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**this method gets the name of a file from the user, puts a 
     *scanner on it if it exists, and prints it to the console
     */
   public static void view()
   {
      String name=request("Input file name: ");
      File ftoview=null;
      Scanner fviewscan=null;
      while(true)
      {
         ftoview=new File(name);
         try
         {
            fviewscan=new Scanner(ftoview);
            break;
         }
         catch (FileNotFoundException e)
         {
            name=request("File not found. Try again: ");
         }
      }
      System.out.println("");
      String line="";
      while (fviewscan.hasNextLine())
      {
         line=fviewscan.nextLine();
         System.out.println(line);
      }
   }
}