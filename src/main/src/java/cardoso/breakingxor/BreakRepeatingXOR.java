package main.src.java.cardoso.breakingxor;

import jdk.jshell.Snippet;
import main.src.java.cardoso.breakingxor.useful.BinUtils;
import main.src.java.cardoso.breakingxor.useful.StringUtils;
import main.src.java.cardoso.breakingxor.useful.UserInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.chrono.MinguoDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;


//O codigo desse exercicio não ficou nem um pouco sofisticado, devido ao tempo limitado. A prioridade minha era que funcionasse

public class BreakRepeatingXOR {
    public static void main(String [] args) {
        String entrada = UserInput.fileInput("INPUT-breaking_xor.txt");
        byte[] decoded = Base64.getDecoder().decode(entrada);
        float[][] keysizes = new float[39][39];
        for (int keysize = 2; keysize <= 40; keysize++){
            keysizes[keysize-2][0] = hamming(decoded, keysize);
            keysizes[keysize-2][1] = keysize;

        }
//        Arrays.sort(keysizes, (a, b) -> Double.compare(a[0], b[0]));
        float menor_ham = 100;
        float menor_index = 100;
        for (int i = 0; i < 39; i++) {
            if (menor_ham > keysizes[i][0]){
                menor_index = keysizes[i][1];
                menor_ham = keysizes[i][0];
            }


        }
        int provavel_keysize = (int) menor_index;
        System.out.println("Provavel keysize:");
        System.out.println(provavel_keysize);
        XORBySize(decoded, provavel_keysize);

    }

    public static float hamming(byte[] byte_txt, int keysize){
        int len = byte_txt.length;
        String decoded_string = new String(byte_txt);
        String[] blocks = new String[decoded_string.length() / keysize];
        float soma = 0;
        int divisor = 0;
        for(int i = 0; i < blocks.length; i++){ //Preenche os blocos do tamanho da keysize com bytes
            blocks[i] = decoded_string.substring((i * keysize), ((i + 1) * keysize));

        }

        for (int u = 0; u < len / keysize - 1; u++){
            for (int l = u + 1; l < len / keysize; l++){
                soma += StringUtils.stringDiffValueSameLenght(blocks[u], blocks[l]);
                divisor++;
            }
        }
        float media_normalizada = (soma/divisor)/keysize;


        return media_normalizada;
    }

    public static void XORBySize(byte[] byte_txt, int keysize) {
        HashMap pesos = StringUtils.CreateScoreSystem();
        HashMap<String, String> provavelmente_em_ingles = new HashMap<String, String>();

        String most_used_string = "eEtTaAiInNoOsShHrRdDlLuUcCmMfFwWyYgGpPbBvVkKqQjJxXzZ0123456789+/' :";
        char[] most_used_char = most_used_string.toCharArray();
        int block_quantity;
        if (byte_txt.length % keysize != 0) {
            block_quantity = (byte_txt.length / keysize) + 1; //MISSING ONE BLOCK
        } else {
            block_quantity = byte_txt.length / keysize;
        }
        String[] bin_blocos = new String[block_quantity];
        for (int i = 0; i < block_quantity; i++) {

            String binario = "";
            for (int l = (i * 29); l < ((i + 1) * 29); l++) {
                if (byte_txt.length > l) {
                    binario += String.format("%8s", Integer.toBinaryString(byte_txt[l] & 0xFF)).replace(' ', '0');

                }
            }
            bin_blocos[i] = binario;

        }
        while (bin_blocos[bin_blocos.length - 1].length() != bin_blocos[0].length()) {
            bin_blocos[bin_blocos.length - 1] += "0";
        }

        String[] transposed = new String[block_quantity];


        for (int b = 0; b < keysize; b++) {
            String sub_bin = "";
            for (int c = 0; c < block_quantity; c++) {
                sub_bin += bin_blocos[c].substring(b * 8, (b + 1) * 8);
            }
            transposed[b] = sub_bin;


        }
        System.out.println("\n");
        String[] desordenado_ASCII = new String[keysize];
        String ordem = "";
        String key = "";
        for (int g = 0; g < keysize; g++) {

            for (int i = 0; i != most_used_char.length; i++) {
                int ascii_code = (int) most_used_char[i];
                String binary_code = BinUtils.intToBin(ascii_code);
                String eightbit_bin = BinUtils.fixBinSize(binary_code, 8);
                String xorred = BinUtils.repeatingKeyXOR(transposed[g], eightbit_bin);

                xorred = BinUtils.binToManyAscii(xorred);

                double valor = StringUtils.EnglishScore(xorred, pesos);
                if ((22 < valor) && (valor < 29)) {
                   desordenado_ASCII[g] = xorred;
                   key += most_used_char[i];

                }

            }


        }
        for (int b = 0; b < desordenado_ASCII[0].length(); b++) {
            for (int c = 0; c < desordenado_ASCII.length; c++) {
                ordem += desordenado_ASCII[c].substring(b, b+1);
            }



        }
        System.out.println(ordem);
        System.out.println("Key:");
        System.out.println(key);
        System.out.println("Source code: https://github.com/Cardoz-0/BreakRepeatingXOR");
    }



}
