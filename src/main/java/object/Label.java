/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.Serializable;

/**
 *
 * @author nguyenvannghiem
 */
public class Label implements Serializable{
    private int id;
    private String name;
    private String des;
    private String role;

    public Label(String name, String des, String role) {
        this.name = name;
        this.des = des;
        this.role = role;
    }

    public Label(int id, String name, String des, String role) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.role = role;
    }

    public Label(String name, String role) {
        this.name = name;
        this.role = role;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public Object[] toObject(){
        return new Object[]{
            id,name,des,role
        };
    }
}
