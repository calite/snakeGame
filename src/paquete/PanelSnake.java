package paquete;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author daniel
 */
public class PanelSnake extends JPanel {

  //declaramos variables
  Color colorSnake = Color.green;
  Color colorComida = Color.red;
  int tamMax, tam, cant, res;
  //la serpiente
  List<int[]> snake = new ArrayList<>();
  int[] comida = new int[2];
  String direccion = "der";
  String direccionProxima = "der";
  
  //creamos un nuevo hilo
  Thread hilo;
  Caminante camino;

  public PanelSnake(int tanMax, int cant) {
    //inicializamos
    this.tamMax = tanMax;
    this.cant = cant;
    this.tam = tamMax / cant;
    this.res = tamMax % cant;
    //se crea una primera serpiente
    int[] a = {cant / 2 - 1, cant / 2 - 1};
    int[] b = {cant / 2, cant / 2 - 1};
    //se aniaden a la serpiente
    snake.add(a);
    snake.add(b);
    generarComida();
    
    camino = new Caminante(this);
    hilo=new Thread(camino);
    hilo.start();
  }

  @Override
  public void paint(Graphics pintor) {
    //repitar lo que ya esta hecho.
    super.paint(pintor);
    pintor.setColor(colorSnake);

//    for (int i = 0; i < snake.size(); i++) {
//      pintor.fillRect(res / 2 + snake.get(i)[0] * tam, res / 2 + snake.get(i)[1] * tam, tam - 1, tam - 1);
//    }
    //pintando serpiente
    for (int[] par : snake) {
      pintor.fillRect(res / 2 + par[0] * tam, res / 2 + par[1] * tam, tam - 1, tam - 1);
    }
    //pintando comida
    pintor.setColor(colorComida);
    pintor.fillRect(res / 2 + comida[0] * tam, res / 2 + comida[1] * tam, tam - 1, tam - 1);

  }

  public void avanzar() {
    igualarDireccion();
    int[] ultimo = snake.get(snake.size() - 1);
    int agregarx = 0;
    int agregary = 0;
    switch (direccion) {
      case "der":
        agregarx = 1;
        break;
      case "izq":
        agregarx = -1;
        break;
      case "arr":
        agregary = -1;
        break;
      case "aba":
        agregary = 1;
        break;
    }

    int[] nuevo = {Math.floorMod(ultimo[0] + agregarx, cant), Math.floorMod(ultimo[1] + agregary, cant)};
    boolean existe = false;
    for (int i = 0; i < snake.size(); i++) {
      if (nuevo[0] == snake.get(i)[0] && nuevo[1] == snake.get(i)[1]) {
        existe = true;
        break;
      }
    }
    if (existe) {
      JOptionPane.showMessageDialog(this, "has perdido");
    } else {
      if (nuevo[0] == comida[0] && nuevo[1] == comida[1]) {
        snake.add(nuevo);
        generarComida();
      } else {
        snake.add(nuevo);
        snake.remove(0);
      }
    }

  }

  public void generarComida() {
    boolean existe = false;
    int a = (int) (Math.random() * cant);
    int b = (int) (Math.random() * cant);
    for (int[] par : snake) {
      if (par[0] == a && par[1] == b) {
        existe = true;
        generarComida();
        break;
      }
    }
    if (!existe) {
      this.comida[0] = a;
      this.comida[1] = b;
    }
  
  }
  
  public void cambiarDireccion(String dir){
    //esto evitara que salte el error si escogemos una direccion erronea
    if((this.direccion.equals("der") || this.direccion.equals("izq"))
            && (dir.equals("arr") || dir.equals("aba"))){
      this.direccionProxima=dir;
    }
    if((this.direccion.equals("arr") || this.direccion.equals("aba"))
            && (dir.equals("izq") || dir.equals("der"))){
      this.direccionProxima=dir;
    }
    
  }
  
    public void igualarDireccion(){
    this.direccion=this.direccionProxima;
  }
}
