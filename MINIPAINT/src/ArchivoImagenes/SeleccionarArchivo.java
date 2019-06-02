/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoImagenes;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author JoseClavijo
 */
public class SeleccionarArchivo extends FileFilter {

    private final String extension;
    private final String description;

    public SeleccionarArchivo(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith("." + extension)
                || f.isDirectory();
    }

    public String getDescription() {
        return description;
    }

    public String getExtension() {
        return extension;
    }

}
