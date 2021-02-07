package main.src.java.cardoso.breakingxor.useful;

import java.util.HashMap;

public class BinUtils {
    public static String hexToBinary(String hex) {
        int i = hexToInt(hex);
        return Integer.toBinaryString(i);
    }

    public static String decToBinary(int dec){
        return Integer.toBinaryString(dec);
    }

    public static int binToDecimal(String bin){
        return  Integer.parseInt(bin,2);
    }
    public static String  decToHex(int decimal){
        return Integer.toString(decimal,16);
    }

    public static int hexToInt(String hex){
        return Integer.parseInt(hex, 16);
    }

    public static String binToHex(String bin){
        int decimal = Integer.parseInt(bin,2);
        return Integer.toString(decimal,16);
    }

    public static String intToBin(int inteiro){
        return Integer.toBinaryString(inteiro);
    }


    public static String fixBinSize(String bin, int lenght){
        String corrected_bin = bin;
        while(corrected_bin.length() != lenght){
            corrected_bin = "0" + corrected_bin;
        }
        return corrected_bin;
    }

    public static String binToManyHex(String str_bin){
        char[] bin = str_bin.toCharArray();
        String hex = "";
        for(int i = 0; i < bin.length; i++){
            if(((i+1) % 4 == 0) && (i > 0)){
                String hexa = String.valueOf(bin[i-3]) + String.valueOf(bin[i-2]) + String.valueOf(bin[i-1] + String.valueOf(bin[i]));
                int decimal = Integer.parseInt(hexa,2);
                String hexStr = Integer.toString(decimal,16);
                hex = hex + hexStr;

            }
        }
        return hex;

    }

    public static String binToManyAscii(String str_bin){
        char[] bin = str_bin.toCharArray();
        String ascii = "";
        for(int i = 0; i < bin.length; i++){
            if(((i+1) % 8 == 0) && (i > 0)){
                String bin8 = String.valueOf(bin[i-7]) + String.valueOf(bin[i-6]) + String.valueOf(bin[i-5]) +String.valueOf(bin[i-4]) +String.valueOf(bin[i-3]) + String.valueOf(bin[i-2]) + String.valueOf(bin[i-1] + String.valueOf(bin[i]));

                char charCode = (char)Integer.parseInt(bin8, 2);
                String letra = Character.toString((char) charCode);
                ascii = ascii + letra;
            }
        }
        return ascii;

    }

    public static String asciiToBinary(String asciiString){

        byte[] bytes = asciiString.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            // binary.append(' ');
        }
        return binary.toString();
    }



    public static String XOR(String hex1, String hex2){
        char[] char_hex1 = hex1.toCharArray();
        char[] char_hex2 = hex2.toCharArray();
        String xor_output = "";
        for(int i = 0; i < hex1.length(); i++){
            if (char_hex1[i] == char_hex2[i]){
                xor_output = xor_output + "0";
            }
            else{
                xor_output = xor_output + "1";
            }
        }
        return xor_output;
    }

    public static String repeatingKeyXOR (String hex_str, String one_byte_hex_str){
        char[] one_byte_hex_char = one_byte_hex_str.toCharArray();
        int hex_lenght = hex_str.length();
        String byte_same_len = "";
        int i = 0;
        while(byte_same_len.length() != hex_lenght){
            byte_same_len = byte_same_len + one_byte_hex_char[i];
            if (i == one_byte_hex_str.length()-1){
                i = 0;
            }
            else{
                i++;
            }
        }
        return XOR(hex_str, byte_same_len);
    }



    public static String hexToManyBin(String hex) {
        char[] char_arr_hex = hex.toCharArray();
        String binary = "";
        for (int i = 0; i < char_arr_hex.length; i++) {
            binary = binary + charToHex(char_arr_hex[i]);
        }
        return binary;
    }

    public static String charToHex(char entrada){
        int num = (Integer.parseInt(String.valueOf(entrada), 16));
        String binary = Integer.toBinaryString(num);

        while (binary.length() != 4) {
            binary = "0" + binary;
        }
        return binary;
    }




    public static byte[] binToManyBytes(String binaryString) {
        int splitSize = 8;

        if (binaryString.length() % splitSize == 0) {
            int index = 0;
            int position = 0;

            byte[] resultByteArray = new byte[binaryString.length() / splitSize];
            StringBuilder text = new StringBuilder(binaryString);

            while (index < text.length()) {
                String binaryStringChunk = text.substring(index, Math.min(index + splitSize, text.length()));
                Integer byteAsInt = Integer.parseInt(binaryStringChunk, 2);
                resultByteArray[position] = byteAsInt.byteValue();
                index += splitSize;
                position++;
            }
            return resultByteArray;
        } else {
            System.out.println("Cannot convert binary string to byte[], because of the input length. '" + binaryString + "' % 8 != 0");
            return null;
        }

    }
}