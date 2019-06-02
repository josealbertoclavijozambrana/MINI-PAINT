/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatrondeDiseñoStrategy;

import java.awt.Point;
import java.awt.Shape;

/**
 *
 * @author JoseClavijo
 */
public class Contexto {

    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Contexto.class);
    private Figura strategy;

    public Contexto(Figura strategy) {
        //en este metodo puedo elegir la figura que se va hacer creada
        this.strategy = strategy;
    }

    public Figura getContexto() {
        return this.strategy;
    }

    public Shape Ejecutar(Point punto1, Point punto2) {
        //aca empieza ejecutar mandar coordenadas agregar figuras
        log.info("Agregando Figuras");
        return strategy.AgregandoFirgurasStrategy(punto1, punto2);

    }
}
