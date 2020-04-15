package com.example.gotproject;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeerFicheroCSV {



    public static List<List<String>> leer() {
        List<List<String>> arrayFichero;
        arrayFichero =  new ArrayList<>();

        String csvFile = "C:/Users/prial/Desktop/musca/src/main/java/com/example/gotproject/inicio.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] linea = line.split(cvsSplitBy);
                arrayFichero.add(Arrays.asList(linea));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayFichero;
    }

    public static void main(String[] args) {
    }
}
