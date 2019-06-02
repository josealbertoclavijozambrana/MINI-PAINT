/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronDiseñomemento;

import java.awt.Color;
import java.awt.Shape;
import PatrondeDiseñoStrategy.Figura;

/**
 *
 * @author JoseClavijo
 *
 */
//cambia los estados
public class Originator {

    private Figura figura;

    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }

    public Memento guardaEstadoEnMemento() {

        return new Memento(figura);
    }

}
