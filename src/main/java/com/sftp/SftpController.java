/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sftp;

/**
 *
 * @author jaqueline
 */
import com.jcraft.jsch.*;

public class SftpController {
    
    
    private static final String USERNAME = "serv";
    private static final String HOST = "159.2";
    private static final int PORT = 2;
    private static final String PASSWORD = "V";
    private static final String PATHORIGEN = "public_html/test";
    private static final String PATHDESTINO = "../test2";

    public static void main(String[] args) {

        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(USERNAME,HOST,PORT);
            session.setConfig("PreferredAuthentications", "password");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(PASSWORD);
            session.connect();
            Channel channel = session.openChannel("sftp");
            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.connect();
            System.out.println("Conectado en " + sftp.pwd());
            sftp.cd(PATHORIGEN);
            System.out.println("Me movi a " + sftp.pwd());
            sftp.rename("prueba.txt",PATHDESTINO+"copiaPrueba2.txt");
            sftp.cd("../test2");
            System.out.println("Archivo copiado");
            sftp.pwd();
        } catch (JSchException e) {
            System.out.println("No se pudo realizar la conexión");
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
    
}
