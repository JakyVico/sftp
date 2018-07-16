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

    private static final String USERNAME = "po";
    private static final String HOST = "10.";
    private static final int PORT = 22;
    private static final String PASSWORD = "v";
    private static final String PATHORIGEN = "/postpago/ftpfile/MetricasAjustes/";
    private static final String PATHDESTINO = "/postpago/procesos/Metricas/Ajustes/";

    public static void main(String[] args) {

        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(USERNAME, HOST, PORT);
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
            System.out.println("Listado de archivos" + sftp.ls(PATHORIGEN));
            System.out.println("DESTINO" + PATHDESTINO);
            //sftp.cd(PATHDESTINO);
            //System.out.println("estoy en "+sftp.pwd());
            try {
                sftp.rename("/postpago/procesos/Metricas/prueba/newArch.txt","/postpago/procesos/Metricas/Ajustes/nA.txt");
                //sftp.get("/postpago/ftpfile/MetricasAjustes/prueba.txt", "/postpago/procesos/Metricas/Ajustes/copia1.txt");
            } catch (Exception e) {
                 e.printStackTrace();
            }
            
           // sftp.rename(PATHORIGEN+"prueba.txt","../../procesos/Metricas/Ajustes/copiaprueba.txt");
            //sftp.rename("/postpago/ftpfile/MetricasAjustes/prueba.txt","../../procesos/Metricas/Ajustes/copiaprueba.txt");
            //sftp.cd("../../procesos/Metricas/Ajustes");
            //System.out.println("Archivo copiado");
            
            System.out.println(sftp.ls(PATHDESTINO));
            sftp.pwd();
        } catch (JSchException e) {
            System.out.println("No se pudo realizar la conexión");
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

}
