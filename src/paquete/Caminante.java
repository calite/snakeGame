package paquete;
/*
No funca
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
*/
/**
 *
 * @author danie
 */
public class Caminante implements Runnable {

  PanelSnake panel;
  boolean estado=true;

  public Caminante(PanelSnake panel) {
    this.panel = panel;
  }

  @Override
  public void run() {
    while (estado) {
      panel.avanzar();
      panel.repaint();
      try {
        Thread.sleep(500);
      } catch (InterruptedException ex) {

      }
    }

  }
  
  public void parar() {
    this.estado = false;
  }

}
