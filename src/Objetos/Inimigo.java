package Objetos;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

//A classe inimigo herda a classe abstrata Objetos
public class Inimigo extends Objetos {

    //variáveis
    public static final int ALTURA_TELA = 400; //variável em que diz a ALTURA da tela criada
    public static final int VELOCIDADE = 1; //variável que determina a velocidade do inimigo
    
    private static int contador = 0;

    //construtor
    public Inimigo(int x, int y) {
        //recebe e guarda as posições x e y
        this.x = x;
        this.y = y;

        //variável que busca e armazena a imagem inimigo
        ImageIcon ref;
        if(contador++ % 3 == 0) {
            ref = new ImageIcon(getClass().getResource("/res/iniovni.png"));
        } else {
            ref = new ImageIcon(getClass().getResource("/res/inimeteoro.png"));
        }
        imagem = ref.getImage();
        
        //armazenando a altura e largura da imagem
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        //determinando que o inimigo será visível
        isVisivel = true;
    }

    //método mexer, tem como função movimentar o inimigo na tela, movimenta-se apenas pelo eixo y.
    public void mexer() {
        // se o eixo x for menor que 0:
        if(this.x < 0) {
            this.y = ALTURA_TELA; //VERDADEIRO: o y será igual a altura da tela
        } else {
            this.y += VELOCIDADE; //FALSO: vai acumular o y juntamente de sua velocidade,
            // fazendo com que o inimigo se movimente para baixo, vindo em direção a nave.
        }
    }

    //getters and setters
    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
    
    public Rectangle getBounds() {
        
        return new Rectangle(x, y, largura, altura);
        
    }

}
