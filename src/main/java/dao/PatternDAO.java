/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.DAO.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import object.Label;
import object.Pattern;

/**
 *
 * @author nguyenvannghiem
 */
public class PatternDAO extends DAO{
    public PatternDAO(){
        super();
    }
    
    public List<Pattern> getListPattern(String role1){
        List<Pattern> list = new ArrayList<>();
        String sql="";
        if(role1.equalsIgnoreCase("Product")){
            sql = "SELECT * FROM tblPatternProduct";
        } else if (role1.equalsIgnoreCase("Property")){
            sql = "SELECT * FROM tblPatternProperty";
        } else {
            sql = "SELECT * FROM tblPatternQuality";
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String content = rs.getString(2);
                String labelString = rs.getString(3);
                Pattern pattern = new Pattern(id, content, new Label(labelString,role1));
                list.add(pattern);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public boolean checkExistedPattern(Pattern pattern){
        boolean result = false;
        String sql="";
        if(pattern.getLabel().getRole().equalsIgnoreCase("Product")){
            sql = "SELECT * FROM tblPatternProduct WHERE content = ?";
        } else if (pattern.getLabel().getRole().equalsIgnoreCase("Property")){
            sql = "SELECT * FROM tblPatternProperty WHERE content = ?";
        } else {
            sql = "SELECT * FROM tblPatternQuality WHERE content = ?";
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pattern.getContent());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = true;
                int id1 = rs.getInt(1);
                if(id1==pattern.getId()) result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void saveNewPattern(Pattern pattern){
        String sql="";
        String role = pattern.getLabel().getRole();
        if(role.equalsIgnoreCase("Product")){
            sql = "INSERT INTO tblPatternProduct (content,label) VALUES (?,?)";
        } else if(role.equalsIgnoreCase("Property")){
            sql = "INSERT INTO tblPatternProperty (content,label) VALUES (?,?)";
        } else {
            sql = "INSERT INTO tblPatternQuality (content,label) VALUES (?,?)";
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pattern.getContent());
            ps.setString(2, pattern.getLabel().getName());
            ps.executeUpdate();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updatePattern(Pattern pattern){
        String sql="";
        if(pattern.getLabel().getRole().equalsIgnoreCase("Product")){
            sql = "UPDATE tblPatternProduct SET content = ? , label = ? WHERE id = ?";
        } else if (pattern.getLabel().getRole().equalsIgnoreCase("Property")){
            sql = "UPDATE tblPatternProperty SET content = ? , label = ? WHERE id = ?";
        } else {
            sql = "UPDATE tblPatternQuality SET content = ? , label = ? WHERE id = ?";
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pattern.getContent());
            ps.setString(2, pattern.getLabel().getName());
            ps.setInt(3, pattern.getId());
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deletePattern(Pattern pattern){
        String sql="";
        if(pattern.getLabel().getRole().equalsIgnoreCase("Product")){
            sql = "DELETE FROM tblPatternProduct WHERE id = ?";
        } else if (pattern.getLabel().getRole().equalsIgnoreCase("Property")){
            sql = "DELETE FROM tblPatternProperty WHERE id = ?";
        } else {
            sql = "DELETE FROM tblPatternQuality WHERE id = ?";
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, pattern.getId());
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
