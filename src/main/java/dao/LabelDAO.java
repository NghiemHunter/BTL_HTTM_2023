/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.DAO.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import object.Label;

/**
 *
 * @author nguyenvannghiem
 */
public class LabelDAO extends DAO{
    public LabelDAO(){
        super();
    }
    
    public List<Label> getListLabel(String role1){
        List<Label> list = new ArrayList<>();
        String sql="";
        if(role1.equalsIgnoreCase("Product")){
            sql = "SELECT * FROM tblLabelProduct";
        } else if (role1.equalsIgnoreCase("Property")){
            sql = "SELECT * FROM tblLabelProperty";
        } else {
            sql = "SELECT * FROM tblLabelQuality";
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String des = rs.getString(3);
                String role = rs.getString(4);
                Label label = new Label(id, name, des, role);
                list.add(label);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    
    public boolean checkExistedLabel(Label label){
        boolean result = false;
        String sql="";
        if(label.getRole().equalsIgnoreCase("Product")){
            sql = "SELECT * FROM tblLabelProduct WHERE name = ?";
        } else if (label.getRole().equalsIgnoreCase("Property")){
            sql = "SELECT * FROM tblLabelProperty WHERE name = ?";
        } else {
            sql = "SELECT * FROM tblLabelQuality WHERE name = ?";
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, label.getName());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = true;
                int id1 = rs.getInt(1);
                if(id1==label.getId()) result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void saveNewLabel(Label label){
        String sql="";
        if(label.getRole().equalsIgnoreCase("Product")){
            sql = "INSERT INTO tblLabelProduct (name,des,role) VALUES(?,?,?)";
        } else if (label.getRole().equalsIgnoreCase("Property")){
            sql = "INSERT INTO tblLabelProperty (name,des,role) VALUES(?,?,?)";
        } else {
            sql = "INSERT INTO tblLabelQuality (name,des,role) VALUES(?,?,?)";
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, label.getName());
            ps.setString(2, label.getDes());
            ps.setString(3, label.getRole());
            ps.executeUpdate();
            
//            Lấy id của Label mới thêm vào
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                label.setId(rs.getInt(1));
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateLabel(Label label){
        String sql="";
        if(label.getRole().equalsIgnoreCase("Product")){
            sql = "UPDATE tblLabelProduct SET name = ? , des = ? WHERE id = ?";
        } else if (label.getRole().equalsIgnoreCase("Property")){
            sql = "UPDATE tblLabelProperty SET name = ? , des = ? WHERE id = ?";
        } else {
            sql = "UPDATE tblLabelQuality SET name = ? , des = ? WHERE id = ?";
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setString(1, label.getName());
            ps.setString(2, label.getDes());
            ps.setInt(3, label.getId());
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteLabel(Label label){
        String sql="";
        if(label.getRole().equalsIgnoreCase("Product")){
            sql = "DELETE FROM tblLabelProduct WHERE id = ?";
        } else if (label.getRole().equalsIgnoreCase("Property")){
            sql = "DELETE FROM tblLabelProperty WHERE id = ?";
        } else {
            sql = "DELETE FROM tblLabelQuality WHERE id = ?";
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, label.getId());
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
