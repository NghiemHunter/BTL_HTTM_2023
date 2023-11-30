/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.LabelDAO;
import dao.UserDAO;
import object.Label;
import object.User;

/**
 *
 * @author nguyenvannghiem
 */
public class test {
    public static void main(String[] args) {
       UserDAO userDAO = new UserDAO();
       User user = new User("abc", "123");
       
       if(userDAO.checkLogin(user)==true){
           System.out.println(user.getName()+"---"+user.getId());
       } else{
           System.out.println("nooooo");
       }
    }
}
