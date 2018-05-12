package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

// Test some of the basics ...
public class jdbcTest {
    public static void main( String[] args )
    {
        System.out.println( "Basic jdbc test ..........." );

//        String jdbcUrl="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        String jdbcUrl="jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
        String user="hbstudent";
        String pass="hbstudent";

        // Attempt to make initial connection
        try{
            System.out.println("Attempting connection to "+jdbcUrl);
            Connection myConn=DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connected");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
