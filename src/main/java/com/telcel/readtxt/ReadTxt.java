/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telcel.readtxt;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaqueline
 */
public class ReadTxt {

    /**
     * @param args the command line arguments
     */
    private static SimpleDateFormat miformato;
    private  static String NombreFichero;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        miformato = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Date Ahora = new Date();
        NombreFichero = miformato.format(Ahora);

        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String fileName) {
                return fileName.endsWith("txt");
            }
        };

        File f = new File("/home/jaqueline/Desktop/archivos");
        String[] fileList = f.list(filter);
        for (int i = 0; i < fileList.length; i++) {
           
            String nombArch = fileList[i];
            System.out.println(fileList[i]);
            BufferedReader br = new BufferedReader(new FileReader("/home/jaqueline/Desktop/archivos/" + nombArch));
            String s1 = br.readLine();

            while ((s1 = br.readLine()) != null) {
                
                    if (s1.startsWith("TOTAL CATEGORIA:")||s1.startsWith("Cateogia")) {
                    System.out.println(s1 + "aaaaaaaaa");
                    escibeNuevoArch(s1);

                } else {
                    System.out.println(s1);
                }
                
                
            }

        }

    }

    private static void escibeNuevoArch(String s1) {
        PrintWriter pw = null; 
        FileWriter fichero =null;
        try {
             fichero = new FileWriter("/home/jaqueline/Desktop/nuevos/" + NombreFichero + ".txt",true);
            pw = new PrintWriter(fichero);
            pw.println(s1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    

}
