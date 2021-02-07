package main.src.java.cardoso.breakingxor.useful;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserInput {
    public static String consoleInput(){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();

    }

    public static String fileInput(String filepath) {
        String resultado = "";
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                resultado += data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return resultado;

    }

    public static HashMap CSVtoHash(String csvFile, boolean firstColumnIsKey){
        HashMap<String, String> hash = new HashMap<String, String>();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] coluna = line.split(splitBy);
                if (firstColumnIsKey) {
                    hash.put(coluna[0], coluna[1]);
                }
                else{
                    hash.put(coluna[1], coluna[0]);
                }

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return hash;
    }

}