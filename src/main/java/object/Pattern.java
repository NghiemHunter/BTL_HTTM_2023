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
public class Pattern implements Serializable{
    private int id;
    private String content;
    private Label label;

    public Pattern(String content, Label label) {
        this.content = content;
        this.label = label;
    }

    public Pattern(int id, String content, Label label) {
        this.id = id;
        this.content = content;
        this.label = label;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
    
    public Object[] toObject(){
        return new Object[]{
            id,content,label.getName()
        };
    }
}
