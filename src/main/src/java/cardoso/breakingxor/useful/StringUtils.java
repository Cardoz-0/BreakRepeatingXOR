package main.src.java.cardoso.breakingxor.useful;

import java.util.HashMap;

public class StringUtils {
    public static int stringDiffValueSameLenght(String string1, String string2){
        String bin1 = BinUtils.asciiToBinary(string1);
        String bin2 = BinUtils.asciiToBinary(string2);
        char[] char_bin1 = bin1.toCharArray();
        char[] char_bin2 = bin2.toCharArray();
        int diff = 0;
        for(int i = 0; i < bin1.length(); i++){
            if(char_bin1[i] != char_bin2[i]){
                diff++;
            }
        }
        return diff;
    }


    public static double EnglishScore(String phrase, HashMap hash_pesos){
        char[]frase = phrase.toCharArray();
        double soma = 0;

        for(int i=0; i < frase.length; i++){
            String str_double_value = (String) hash_pesos.get(String.valueOf(frase[i]));
            if (str_double_value != null && !str_double_value.isEmpty()){
                double valor = Double.parseDouble(str_double_value);
                soma = soma + valor;
            }

        }
        double media = soma/frase.length;
        return media; //Com base nos valores, a frase media em ingles teria o valor aproximado de 23;
    }


    public static HashMap CreateScoreSystem(){
        //baseado em https://www3.nd.edu/~busiforc/handouts/cryptography/letterfrequencies.html
        HashMap<String, String> hash_peso = new HashMap<String, String>();
        String most_used_string = "eEaArRiIoOtTnNsSlLcCuUdDpPmMhHgGbBfFyYwWkKvVxXzZjJqQ0123456789+/' ";
        char[] most_used = most_used_string.toCharArray();
        String[] pesos = {"56.88","36.88", "43.31","23.31", "38.64","18.64", "38.45", "18.45", "36.51","16.51", "35.43","15.43", "33.92","13.92", "29.23","19.23", "27.98","17.98", "23.13","21.13", "18.51","18.51",
                "17.25","17.25", "16.14","16.14", "15.36","15.36", "15.31","15.31", "12.59","12.59", "10.56","10.56", "9.24","9.24", "9.06", "9.06","6.57","6.57", "5.61", "5.61","5.13", "5.13","1.48", "1.48","1.39", "1.39", "1.00","1.00", "1.00","1.00",
                "6.8","6.8","6.8","6.8","6.8","6.8","6.8","6.8","6.8","6.8", "0.0", "0.0", "12", "20"};
        //Media aprimaxada e valores após Z são aproximados
        for(int i = 0; i < most_used_string.length(); i++){
            hash_peso.put(String.valueOf(most_used[i]), pesos[i]);
        }
        return hash_peso;
    }


}
