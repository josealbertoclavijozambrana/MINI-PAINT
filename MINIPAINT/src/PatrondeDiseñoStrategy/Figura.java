/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatrondeDiseñoStrategy;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

/**
 *
 * @author JoseClavijo
 */
public class Figura {

    public ArrayList<Shape> shape;
    public Color color;
    public boolean relleno;

    public Figura() {
    }

    public Figura(ArrayList<Shape> shape, Color color, boolean relleno) {
        ////se realiza los dibujos de las figuras
        this.shape = shape;
        this.color = color;
        this.relleno = relleno;
    }

    public Shape AgregandoFirgurasStrategy(Point Coordenada1, Point Coordenada2) {

        return null;
    }

    public ArrayList<Shape> getShape() {
        return shape;
    }

    public void setShape(ArrayList<Shape> shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isRelleno() {
        return relleno;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    @Override
    public String toString() {
        return "Figura{" + "shape=" + shape + ", color=" + color + ", relleno=" + relleno + '}';
//        return "shape" + shape;
    }

}
