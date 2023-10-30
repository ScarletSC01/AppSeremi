package com.android2023.appseremi;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseTEA {
    public Connection conexionSQL(){
        Connection conexion = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://10.51.96.68;databaseName=BaseDeDatosTEA;user=Fabricio;password=1234567890;");
        }catch (Exception e){
            System.out.println("Eroor de conexio");
        }
        return conexion;
    }
}
