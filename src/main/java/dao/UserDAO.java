/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import object.User;

/**
 *
 * @author nguyenvannghiem
 */
public class UserDAO extends DAO{
    public UserDAO(){
        super();
        
    }
    
    public boolean checkLogin(User user){
        boolean result = false;
        String sql = "SELECT * FROM tblUser WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString(4));
                user.setPosition(rs.getString(5));
                result = true;
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean checkExistedUser(User user){
        boolean result = false;
        String sql = "SELECT * FROM tblUser WHERE username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void saveNewUser(User user){
        String sql = "INSERT INTO tblUser(username,password,name,position) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getPosition());
            ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
