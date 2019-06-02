/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatrondeDiseñoStrategy;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author JoseClavijo
 */
public class Rectangulo extends Figura {
    //se realiza sobrescritura de metodo agregando Rectangulo

    @Override
    public Shape AgregandoFirgurasStrategy(Point Coordenada1, Point Coordenada2) {
        double IniciandoX = Math.min(Coordenada1.getX(), Coordenada2.getX());
        double Iniciandoy = Math.min(Coordenada1.getY(), Coordenada2.getY());
        double ancho = Math.abs(Coordenada2.getX() - Coordenada1.getX());
        double altura = Math.abs(Coordenada2.getY() - Coordenada1.getY());
        Shape nuevaFigura = new Rectangle2D.Double(IniciandoX, Iniciandoy, ancho, altura);
        return nuevaFigura;
    }

}
