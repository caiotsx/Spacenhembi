package Objetos;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

//A classe nave herda a classe abstrata Objetos
public class Nave extends Objetos {

    //criando variáveis
    private int dx, dy; //variáveis responsáveis por determinar a velocidade do personagem no eixo x e y;
    private List<Missel> misseis; //por haver mais de um missel, criei uma váriavel List misseis

    //criando o construtor Nave
    public Nave() {
        //variável que busca e armazena a imagem missel
        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/navezinha.png"));
        imagem = referencia.getImage();

        //armazenando a altura e largura da imagem
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);

        //criando a lista de misseis
        misseis = new ArrayList<Missel>();

        //declarando a posição inicial da nave na tela
        this.x = 200;
        this.y = 250;
    }

    //método mexer, tem como função movimentar a nave na tela, movimenta-se no eixo x e y
    public void mexer() {

        //acumuladores em que alteram a posição da nave. 
        x += dx; //caso acionado(é acionada ao apertar uma tecla de movimentação), a nave movimenta-se da posição x, para a posição x + dx.
        y += dy;//caso acionado(é acionada ao apertar uma tecla de movimentação), a nave movimenta-se da posição y, para a posição x + dy.

        //definindo os limites para que a nave não saia dos limites da tela
        if (this.x < 1) {
            x = 1;
        }

        if (this.x > 430) {
            x = 430;
        }

        if (this.y < 1) {
            y = 1;
        }

        if (this.y > 340) {
            y = 340;
        }
    }

    //método atirar, ao acionado, adiciona um novo míssel
    public void atirar() {
        this.misseis.add(new Missel(x + largura / 2 -10, y + altura / 2 -30));
    }

    
    //getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public boolean IsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public List<Missel> getMisseis() {
        return misseis;
    }
    
     public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);

    }

    //método em que identifica uma tecla em que foi pressionada
    public void keyPressed(KeyEvent tecla) {

        //através da tecla, ele volta um codigo inteiro
        int codigo = tecla.getKeyCode();

        //através desse código, podemos fazer uma condicional para reagir de acordo com
        //determinada tecla, como por exemplo, ao apertar W, ele subtrai 5 na movimentação fazendo com que
        // a nave a diminua o eixo x, consequentemente fazendo com que se movimente pra cima
        if (codigo == KeyEvent.VK_W) {
            dy = -5;
        }

        //caso apertar S, adicionará 5 ao eixo x, fazendo com a nave suba no eixo x, consequentemente
        //fazendo com que a nave desça
        if (codigo == KeyEvent.VK_S) {
            dy = 5;
        }

        //caso apertar A, subtrairá 5 ao eixo y, fazendo com a nave desça no eixo y, consequentemente
        //fazendo com que a nave vá para o lado esquerdo
        if (codigo == KeyEvent.VK_A) {
            dx = -5;
        }
        
        //caso apertar D, adicionará 5 ao eixo y, fazendo com a nave suba no eixo y, consequentemente
        //fazendo com que a nave vá para o lado direito
        if (codigo == KeyEvent.VK_D) {
            dx = 5;
        }

        //o espaço será a tecla responsável para atirar os mísseis, então caso precionada,
        //ela chamará o método atirar
        if (codigo == KeyEvent.VK_SPACE) {
            atirar();
        }
    }

    //método em que identifica uma tecla em que foi soltada
    public void keyReleased(KeyEvent tecla) {
        
        //ao soltar, também é gerado um código inteiro
        int codigo = tecla.getKeyCode();

        //com isso, podemos é possivel que estamos encerrando o movimento para cada tecla,
        //instanciando o dy e dx para 0.
        if (codigo == KeyEvent.VK_W) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_S) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_A) {
            dx = 0;
        }

        if (codigo == KeyEvent.VK_D) {
            dx = 0;
        }
    }

}
