/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatrondeDiseñoStrategy;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 *
 * @author JoseClavijo
 */
public class Linea extends Figura {

    //se realiza sobrescritura de metodo agregando Linea
    @Override
    public Shape AgregandoFirgurasStrategy(Point Coordenada1, Point Coordenada2) {
        Shape nuevaFigura = new Line2D.Double(Coordenada1.getX(), Coordenada1.getY(), Coordenada2.getX(), Coordenada2.getY());
        return nuevaFigura;
    }

}
