/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nguyenvannghiem
 */
public class DAO {
    public static Connection conn;
    
    public DAO(){
        if(conn==null){
            String URL = "jdbc:mysql://localhost:3306/BTL_HTTM_2023";
            String username = "root";
            String password = "190401";
            try {
                conn = DriverManager.getConnection(URL,username,password);
            } catch (SQLException e) {
                System.out.println(e);
            }
            
        }
    }
}
