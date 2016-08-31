package ru.portalPFR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 048ChubakovaEL on 29.08.2016.
 */
public class test {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            try {
                Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
//            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("объявление класса "+ e);
        }

//        try
//        {
////            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
//            Class theDriver = sun.odbc.JdbcOdbcDriver.class;
//        }
//        catch(ClassNotFoundException e)
//        {
//            System.err.println("Драйвер JDBC/ODBC не найден");
//        }
//
//        jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC


        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://10.48.0.184:3306/sspedo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("БД не доступна " + e);
        }
        if (null != connection) {
            System.out.println("------- Подключение установлено -------");
        } else {
            System.out.println("------- Подключение НЕ установлено -------");
        }
    }
}