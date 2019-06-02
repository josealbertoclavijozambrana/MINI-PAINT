/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronDiseñomemento;

import Main.MiniPaint;
import java.util.Stack;

/**
 *
 * @author JoseClavijo
 */
public class CareTaker {
//registra los cambios del originator

    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CareTaker.class);
    public Stack<Memento> mementoPila = new Stack<Memento>();
    private Stack<Memento> mementoPilaAuxiliar = new Stack<Memento>();

    public void Añadir(Memento meme) {

        // estado de la figura
        mementoPila.push(meme);
        //limpiar la pila auxiliar
        mementoPilaAuxiliar.clear();

    }

    public boolean deshacer() {
        if (!mementoPila.empty()) {
            //agrega la pila auxiliar
            mementoPilaAuxiliar.push(/*Elimina la pila principal*/mementoPila.pop());
            log.info("mostrar los arreglos que fuero eliminados: " + mementoPila.toString());

            return false;
        } else {
            return true;
//            System.out.println("no se puede retroceder mas");
        }
    }

    public void borrarTodoPila() {
        mementoPila.clear();
        mementoPilaAuxiliar.clear();
    }

    public boolean rehacer() {
        if (!mementoPilaAuxiliar.empty()) {
            mementoPila.push(/*elimina la pila auxiliar para  agregar la pila principal*/mementoPilaAuxiliar.pop());
            log.info("mostrar los arreglos que fueron restaurados: " + mementoPila.toString());

            return false;
        } else {
            return true;

//            System.out.println("no se puede rehacer mas");
        }
    }

}
