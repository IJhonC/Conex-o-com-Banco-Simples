/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_classes;

/**
 *
 * @author joao_cristofolini
 */
public class Usuario {

    private int iduser;
    private String nmUser;
    private String pwUser;
    private String tpuser;
    private char pvUser;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getPwUser() {
        return pwUser;
    }

    public void setPwUser(String pwUser) {
        this.pwUser = pwUser;
    }

    public String getTpuser() {
        return tpuser;
    }

    public void setTpuser(String tpuser) {
        this.tpuser = tpuser;
    }

    public char getPvUser() {
        return pvUser;
    }

    public void setPvUser(char pvUser) {
        this.pvUser = pvUser;
    }
}
