/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sig_classes;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sig_telas.FormLogin;

/**
 *
 * @author joao_cristofolini
 */
public class SIG_TecDESI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Selecioanr o Look & Feel desejado
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException exc) {
            exc.printStackTrace();
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        } catch (InstantiationException exc) {
            exc.printStackTrace();
        } catch (IllegalAccessException exc) {
            exc.printStackTrace();
        }
        
        //Listar os Look & Feel
        for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
            System.out.println(info.getName());
        }
        
        FormLogin fLogin = new FormLogin();
        
        fLogin.setVisible(true);
    }

}
