package Objetos;

import java.awt.Image;

//classe abstrata para definir um padrão para os outros objetos
public abstract class Objetos {
    //variáveis presentes em todos os objetos
    protected Image imagem; //variável para guardar a imagem do objeto
    protected int x, y; //variáveis responsáveis por mostrar as posições do objeto na tela
    protected int largura, altura; //variáveis responsáveis por mostrar a largura e altura do objeto
    protected boolean isVisivel; //variável que diz se o objeto está ou não visível

    public abstract void mexer(); //método presente em todos os objetos, cujo o objetivo é se movimentar na tela
    

}
