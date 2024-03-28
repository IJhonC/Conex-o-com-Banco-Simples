/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_classes;

/**
 *
 * @author joao_cristofolini
 */
public class Produto {

    private int cod;
    private String descr;
    private String unidade;
    private float qtd;
    private float preco;
    
//    public Produto(String cod, String desc, String unidade, float qtd, float preco){
//        this.cod = cod;
//        this.desc = desc;
//        this.unidade = unidade;
//        this.qtd = qtd;
//        this.preco = preco;
//    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
   
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public float getQtd() {
        return qtd;
    }

    public void setQtd(float qtd) {
        this.qtd = qtd;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }      
}
