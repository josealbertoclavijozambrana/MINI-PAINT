/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronDiseñomemento;

import PatrondeDiseñoStrategy.Figura;

/**
 *
 * @author JoseClavijo
 *
 */
//componete que agrega el orign
public class Memento {

    private Figura figura;

    public Memento(Figura figura) {
        this.figura = figura;

    }

    public Figura getFigura() {
        return figura;
    }

    @Override
    public String toString() {
        return "Memento{" + "figura=" + figura + '}';
    }

}
