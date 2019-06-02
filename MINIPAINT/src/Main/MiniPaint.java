/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.logging.Logger;

/**
 *
 * @author JoseClavijo
 */
public class MiniPaint {

    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MiniPaint.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        log.info("Bienvenido Al Mini Paint");
        Ventana ventanita = new Ventana();
        ventanita.setVisible(true);
    }

}
