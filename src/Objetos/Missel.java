package Objetos;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

//A classe Missel herda a classe abstrata Objetos
public class Missel extends Objetos {

    //variáveis
    public static final int ALTURA_TELA = 400; //variável em que diz a ALTURA da tela criada
    public static final int VELOCIDADE = 10; //variável que determina a velocidade do projétil

    //construtor
    public Missel(int x, int y) {
        //recebe e guarda as posições x e y
        this.x = x;
        this.y = y;

        //variável que busca e armazena a imagem missel
        ImageIcon ref = new ImageIcon(getClass().getResource("/res/missel.png"));
        imagem = ref.getImage();

        //armazenando a altura e largura da imagem
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        //determinando que o míssel será visível
        isVisivel = true;
    }

    //método mexer, tem como função movimentar o missel na tela, movimenta-se apenas no eixo y
    public void mexer() {
        //se o míssel for visivel
        if (isVisivel) {
            this.y -= VELOCIDADE; //ele subtrai o valor atual de y com a velocidade e acumula,
            //fazendo com que o missel suba, porque quanto menor o eixo y, mais ele sobe na tela.
            if (this.y > ALTURA_TELA) {
                isVisivel = false; //se o eixo y do míssel for maior que a altura da tela, ele deixará de ser
                //visível

            }
        }
    }

    //getters e setters
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
