/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author Alex
 */
public class DirElement {
    private int id;
    private String naim;

    public DirElement(int id, String naim) {
        this.id = id;
        this.naim = naim;
    }

    public DirElement() {
        naim = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaim() {
        return naim;
    }

    public void setNaim(String naim) {
        this.naim = naim;
    }
    
    
    
}
