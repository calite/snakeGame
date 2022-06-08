
package paquete;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author daniel
 */
public class PanelFondo extends JPanel {
  
  Color colorFondo = Color.gray;
  int tamMax, tam, cant, res;
  
  public PanelFondo(int tanMax,int cant) {
    this.tamMax = tanMax;
    this.cant = cant;
    this.tam = tamMax / cant;
    this.res = tamMax % cant;
    
  }
  @Override
  public void paint(Graphics pintor) {
    //repitar lo que ya esta hecho.
    super.paint(pintor);
    pintor.setColor(colorFondo);
    for (int i = 0; i < cant; i++) {
      for (int j = 0; j < cant; j++) {
        pintor.fillRect(res / 2 + i * tam, res / 2 + j * tam, tam - 1, tam - 1);
      }
    }
  }
  
}
