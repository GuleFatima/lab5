/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spider;
import java.io.*;
import java.util.*;
import java.io.File;
/**
 *
 * @author GUL E FATIMA
 */
public class Spider {

    /**
     * @param args the command line arguments
     */
    public static Map <String,String> indexmap = new HashMap <>(); 
    
                   
    // This recursive "helper" method prints the given file/directory and any
    // files/directories inside it at the given level of indentation.
    // Precondition: f != null and f exists
    private static void crawl(File f, String indent) throws IOException{
         
    	System.out.println(indent + f.getName());
        System.out.println(indent + f.getAbsolutePath());
        indexmap.put(f.getName(),f.getAbsolutePath());
        String[] pathSplit=f.getAbsolutePath().split("\\\\");
        for(int i=0; i<((pathSplit.length)-1);i++)
        {
            System.out.println(pathSplit[i]);
        indexmap.put(pathSplit[i],f.getAbsolutePath());
        }
        
        if(f.getName().endsWith(".txt"))
        {
            BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        String[] contentSplit=line.split("\\s+");
        for(int i=0;i<contentSplit.length;i++)
            
        {
            
            indexmap.put(contentSplit[i],f.getAbsolutePath());
            System.out.print(contentSplit[i]+"\n");
            
        
        }

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }//end of while
        String fileContent= sb.toString();
       // System.out.println(fileContent);
    } catch(IOException e){
        br.close();
    }
        }
    	if (f.isDirectory()) {
        	// recursive case: directory
        	// print everything in the directory
        	File[] subFiles = f.listFiles();
    		indent += "    ";
        	for (int i = 0; i < subFiles.length; i++) {
              	crawl(subFiles[i], indent);
        	}
        }
    }
    public static void search(String key){
    String var=indexmap.get(key);
    System.out.println(var);
    }
     public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.print("Directory to crawl? ");
        String directoryName = console.nextLine();
        
        File f = new File(directoryName);
        crawl(f);
    }
    
    // Prints the given file/directory and any files/directories inside it,
    // starting with zero indentation.
    // Precondition: f != null and f exists
    public static void crawl(File f) throws IOException {
    	crawl(f, "");
    }
}