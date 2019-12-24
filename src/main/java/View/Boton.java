package View;

import java.awt.event.ActionListener;

import javax.swing.JButton;

class Boton extends JButton {
    /**
     *
     */
    private static final long serialVersionUID = -217174663848968418L;

    public Boton(String etiqueta, ActionListener escuchador) {
        super(etiqueta);
        addActionListener(escuchador);
    }
}


