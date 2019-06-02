/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Cursor;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import ArchivoImagenes.SeleccionarArchivo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import PatronDiseñomemento.CareTaker;
import PatronDiseñomemento.Memento;
import PatronDiseñomemento.Originator;
import PatrondeDiseñoStrategy.Contexto;
import PatrondeDiseñoStrategy.Figura;
import PatrondeDiseñoStrategy.Linea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.apache.log4j.LogManager;

/**
 *
 * @author JoseClavijo
 */
public class Dibujar extends JPanel implements MouseListener, MouseMotionListener {

    private static final org.apache.log4j.Logger logger = LogManager.getRootLogger();
    private Point Coordenada1;
    private Point Coordenada2;
    private ArrayList<Shape> figura;
    public Color coloractual;
    private BufferedImage Miimagen;
    private Graphics2D g2D;
    public boolean lapiz;
    public boolean relleno;
    private final Originator originator;
    private final CareTaker careTaker;
    public Contexto context;
    private ArrayList listado;

    private Figura ClasesFiguras;

    public Dibujar() {
        super();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setFocusable(true);
        lapiz = true;
        relleno = false;
        coloractual = Color.BLACK;
        context = new Contexto(new Linea());
        originator = new Originator();
        careTaker = new CareTaker();
        ClasesFiguras = new Figura() {
        };
        figura = new ArrayList<>();

    }

    public Color getColorActual() {
        return coloractual;
    }

    public void setColorActual(Color color) {
        coloractual = color;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //aqui cuando mi buffered esta nulo me crea o me lo inicializa
        //y tambien sive para el momento de abrir una imagen
        if (Miimagen == null) {
            g2D = crearGraphics2D();
        }
        //aqio donde digo si mi buffered tiene imagen me lo pinte en mi lienzo
        if (Miimagen != null && isShowing()) {
            g.drawImage(Miimagen, 0, 0, this);

        }
        g2D = (Graphics2D) g;

        listado = new ArrayList(careTaker.mementoPila);
        if (listado.size() > 0) {
            for (int i = 0; i < listado.size(); i++) {
                Memento meme = (Memento) listado.get(i);
                g2D.setColor(meme.getFigura().getColor());
                //empieza dibujar y almacena  las figuras  del origin
                if (meme.getFigura().isRelleno()) {
                    for (int j = 0; j < meme.getFigura().getShape().size(); j++) {
                        g2D.fill(meme.getFigura().getShape().get(j));
                    }
                }

                for (int j = 0; j < meme.getFigura().getShape().size(); j++) {
                    g2D.draw(meme.getFigura().getShape().get(j));
                }
            }
        }
        //aqui pinto el temporal
        if (Coordenada1 != null && Coordenada2 != null) {
            if (figura.size() > 0) {
                for (int Arrastrar = 0; Arrastrar < figura.size(); Arrastrar++) {
                    Shape shape = figura.get(Arrastrar);
                    g2D.setColor(coloractual);
                    g2D.draw(shape);
                }

            } else {
                Shape shape = context.Ejecutar(Coordenada1, Coordenada2);

                g2D.setColor(coloractual);
                g2D.draw(shape);
            }

        }

    }

    public Graphics2D crearGraphics2D() {
        Graphics2D g2 = null;
        if (Miimagen == null || Miimagen.getWidth() != getSize().width
                || Miimagen.getHeight() != getSize().height) {
//            myImage = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
            Miimagen = (BufferedImage) createImage(getSize().width, getSize().height);
        }
        if (Miimagen != null) {
            g2 = Miimagen.createGraphics();
            g2.setColor(coloractual);
            g2.setBackground(getBackground());
        }

        g2.clearRect(0, 0, getSize().width, getSize().height);

        return g2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Dibujar.this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        Coordenada1 = e.getPoint();
        Coordenada2 = Coordenada1;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Dibujar.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        if (lapiz) {
            //lapiaz
            figura.add(context.Ejecutar(Coordenada1, Coordenada2));
            ClasesFiguras = new Figura();
            ClasesFiguras.setShape(figura);
            ClasesFiguras.setRelleno(relleno);
            ClasesFiguras.setColor(coloractual);
            //todo en una lista
            originator.setFigura(ClasesFiguras);
            //lo guardo en la pila originator
            careTaker.Añadir(originator.guardaEstadoEnMemento());
            ClasesFiguras = null;
            context.getContexto();
            figura = new ArrayList<>();
        } else {
            figura.add(context.Ejecutar(Coordenada1, Coordenada2));
            ClasesFiguras = new Figura();
            ClasesFiguras.setShape(figura);
            ClasesFiguras.setRelleno(relleno);
            ClasesFiguras.setColor(coloractual);
            originator.setFigura(ClasesFiguras);
            //empieza dibujar y añadir  las figuras  del originittor
            careTaker.Añadir(originator.guardaEstadoEnMemento());
            Coordenada1 = null;
            Coordenada2 = null;
            ClasesFiguras = null;
            figura = new ArrayList<>();
        }
        repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Coordenada2 = e.getPoint();
        if (lapiz) {
            //para hacer lapiz
            figura.add(context.Ejecutar(Coordenada1, Coordenada2));
            Coordenada1 = Coordenada2;
        }
        repaint();

    }

    public String[] getFormats() {
        String[] formats = javax.imageio.ImageIO.getWriterFormatNames();
        java.util.TreeSet<String> formatSet = new java.util.TreeSet<String>();
        for (String s : formats) {
            formatSet.add(s.toLowerCase());
        }
        return formatSet.toArray(new String[0]);
    }

    public JFileChooser createJFileChooser() {
        JFileChooser jfc = new JFileChooser();
        jfc.setAcceptAllFileFilterUsed(false);
        String[] fileTypes = getFormats();
        for (int i = 0; i < fileTypes.length; i++) {
            jfc.addChoosableFileFilter(new SeleccionarArchivo(fileTypes[i], fileTypes[i] + " file"));
        }
        return jfc;

    }

    public void ReproducirSonido(String nombreSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void abrir() {
        try {
            JFileChooser jfc = createJFileChooser();
            jfc.showOpenDialog(this);

            File file = jfc.getSelectedFile();
            if (file == null) {
                return;
            }

            Miimagen = javax.imageio.ImageIO.read(file);
            int w = Miimagen.getWidth(null);
            int h = Miimagen.getHeight(null);
            if (Miimagen.getType() != BufferedImage.TYPE_INT_ARGB) {
                BufferedImage bi2
                        = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                Graphics big = bi2.getGraphics();
                big.drawImage(Miimagen, 0, 0, null);
            }
            g2D = (Graphics2D) Miimagen.getGraphics();
            careTaker.borrarTodoPila();
            repaint();
        } catch (IOException e) {

            System.exit(1);
        }
    }

    public void guardar() {
        try {
            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(this);
            File file = jfc.getSelectedFile();
            if (file == null) {
                return;
            }
            Graphics g = Miimagen.createGraphics();
            this.paint(g);
//            g.dispose();
            javax.swing.filechooser.FileFilter ff = jfc.getFileFilter();
            String fileName = file.getName();
            String extension = "jpg";
            if (ff instanceof SeleccionarArchivo) {
                extension = ((SeleccionarArchivo) ff).getExtension();
            }

            fileName = fileName + "." + extension;
            file = new File(file.getParent(), fileName);
            javax.imageio.ImageIO.write(Miimagen, extension, file);
        } catch (Exception e) {
            System.out.println(e);
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void deshacer() {

        if (careTaker.deshacer()) {
            ReproducirSonido("ding.wav");
        };
        repaint();

    }

    public void Rehacer() {
        if (careTaker.rehacer()) {
            ReproducirSonido("ding.wav");
        };
        repaint();
    }

    public void Nuevo() {
        logger.info("reseteo correcto");
        Miimagen = null;
        careTaker.borrarTodoPila();
        repaint();
    }

}
