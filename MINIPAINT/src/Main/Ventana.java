/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import PatrondeDiseñoStrategy.Circulo;
import PatrondeDiseñoStrategy.Contexto;
import PatrondeDiseñoStrategy.Linea;
import PatrondeDiseñoStrategy.Rectangulo;
import javax.swing.JOptionPane;

/**
 *
 * @author JoseClavijo
 */
public final class Ventana extends JFrame implements ActionListener {

    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JPanel contenido1 = new javax.swing.JPanel();
    private JPanel contenido2 = new JPanel();
    private JButton Nuevo = new JButton();
    private JButton Informacion = new JButton();
    private JButton Deshacer = new JButton();
    private JButton Rehacer = new JButton();
    private JButton Abrir = new JButton();
    private JButton Comoguardar = new JButton();
    private JButton Salir = new JButton();
    private JButton Rectangulo = new JButton();
    private JButton Circulo = new JButton();
    private JButton Linea = new JButton();
    private JButton Lapiz = new JButton();
    private JButton colores = new JButton("COLORES");
    private JCheckBox relleno;
    private Dibujar dibujar;

    public Ventana() {

        inicio();

    }

    public void inicio() {

        dibujar = new Dibujar();
        this.getContentPane().add(dibujar);
        setMinimumSize(new Dimension(1200, 700));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 600));

        contenido1.setLayout(new java.awt.GridLayout());
        Nuevo.setText("NUEVO");
        contenido1.add(Nuevo);
        Abrir.setText("ABRIR");
        contenido1.add(Abrir);

        Comoguardar.setText("Guardar Como...");
        contenido1.add(Comoguardar);
        Informacion.setText("INFORMACIÓN");
        contenido1.add(Informacion);
        Salir.setText("SALIR");
        contenido1.add(Salir);

        jTabbedPane1.addTab("Archivo", contenido1);

        contenido2.setLayout(new java.awt.GridLayout());
        Deshacer.setText("DESHACER");
        contenido2.add(Deshacer);
        Rehacer.setText("REHACER");
        contenido2.add(Rehacer);
        Rectangulo.setText("RECTANGULO");
        contenido2.add(Rectangulo);

        Circulo.setText("CIRCULO");
        contenido2.add(Circulo);

        Linea.setText("LINEA");
        contenido2.add(Linea);

        Lapiz.setText("LAPIZ");
        contenido2.add(Lapiz);
        relleno = new JCheckBox("Relleno");
        contenido2.add(relleno);
        contenido2.add(colores);
        jTabbedPane1.addTab("Herramientas", contenido2);
        this.add(jTabbedPane1);
        this.add(dibujar, BorderLayout.CENTER);
        this.add(jTabbedPane1, BorderLayout.NORTH);
        colores.addActionListener(this);
        Informacion.addActionListener(this);
        Abrir.addActionListener(this);
        Comoguardar.addActionListener(this);
        Deshacer.addActionListener(this);
        Rehacer.addActionListener(this);
        Rectangulo.addActionListener(this);
        Circulo.addActionListener(this);
        relleno.addActionListener(this);
        Lapiz.addActionListener(this);
        Linea.addActionListener(this);
        Salir.addActionListener(this);
        Nuevo.addActionListener(this);
        pack();

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Nuevo) {
            dibujar.Nuevo();
        }
        if (ae.getSource() == Rehacer) {
            dibujar.Rehacer();
        }
        if (ae.getSource() == Deshacer) {
            dibujar.deshacer();

        }
        if (ae.getSource() == Informacion) {
            JOptionPane.showMessageDialog(this, "AUTOR: JOSE CLAVIJO -- UNIVERSIDAD NUR");
        }
        if (ae.getSource() == Abrir) {
            dibujar.abrir();
        }
        if (ae.getSource() == Comoguardar) {
            dibujar.guardar();
        }
        if (ae.getSource() == Salir) {
            System.exit(0);
        }
        if (ae.getSource() == colores) {
            java.awt.Color color = JColorChooser.showDialog(this, "Seleccione color", this.dibujar.getColorActual());
            System.out.println("es de color: " + color.toString());
            this.dibujar.setColorActual(color);
        }
        if (ae.getSource() == Lapiz) {
            dibujar.lapiz = true;
            dibujar.context = new Contexto(new Linea());
        }
        if (ae.getSource() == Rectangulo) {
            dibujar.lapiz = false;
            dibujar.context = new Contexto(new Rectangulo());

            System.out.println("golas");
        }
        if (ae.getSource() == Circulo) {
            dibujar.lapiz = false;
            dibujar.context = new Contexto(new Circulo());
        }
        if (ae.getSource() == Linea) {
            dibujar.lapiz = false;
            dibujar.context = new Contexto(new Linea());
        }

        if (ae.getSource() == relleno) {
            if (dibujar.relleno) {
                dibujar.relleno = false;
            } else {
                dibujar.relleno = true;
            }

        }
    }

}
