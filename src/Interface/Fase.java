package Interface;

import Objetos.Nave;
import Objetos.Missel;
import Objetos.Inimigo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//a classe fase herda a classe JPanel e implementa a interface ActionListener
public class Fase extends JPanel implements ActionListener {

    //declarando variáveis
    private Image fundo; //variável responsável por alterar a imagem do fundo
    private Nave nave; //varável que irá declarar a nave, criando-a na tela
    private Timer timer; //variável que será responsável pela atualização da tela

    //variáveis condicionais que ativarão uma tela específica para cada situação de jogo(menu, ganhar e perder)
    private boolean menu = true;
    private boolean perdeu = false;
    private boolean ganhou = false;
    public boolean emJogo = false;

    //variável responsável por criar os inimigos, por possuir vários, foi criado uma lista de inimigos
    private List<Inimigo> inimigos;

    //matriz responsável por dar a localização no eixo x e y para os inimigos
    //é possível utilizar o Math.random() para randomizar para cada jogo
    //porém, preferi separar os inimigos manualmente para uma proporção um pouco melhor
    private int[][] coordenadas = {{29, -1000}, {59, -900}, {89, -950},
    {109, -780}, {139, -580}, {400, -880}, {259, -790},
    {300, -760}, {240, -790}, {440, -980}, {3, -560}, {70, -510},
    {320, -930}, {430, -590}, {256, -530}, {49, -940}, {30, -990},
    {200, -920}, {259, -900}, {300, -660}, {320, -540}, {220, -810},
    {402, -860}, {180, -740}, {128, -820}, {170, -490}, {5, -700},
    {300, -920}, {420, -856}, {320, -456}};

    //construtor responsável por instanciar a fase
    public Fase() {

        setFocusable(true); //é responsável pela atualização da tela
        setDoubleBuffered(true); //também responsável pela atualização da tela, para suavizar
        addKeyListener(new TecladoAdapter()); //chamando o KeyListener que captará as teclas pressionadas e soltadas

        //pegando a imagem de fundo e guardando-a na variável fundo
        ImageIcon ref = new ImageIcon(getClass().getResource("/res/fundo.png"));
        fundo = ref.getImage();

        //criando o timer responsável pela atualização da tela, em que atualizará a cada 5ms
        timer = new Timer(5, this);
        timer.start();

    }

    //método responsável por adicionar os inimigos na tela
    public void inicializaInimigos() {
        //se tiver em jogo, ele irá inicializar os inimigos
        if (emJogo) {
            inimigos = new ArrayList<>(); //vai criar uma ArrayList para armazenar todos inimigos

            //criei um loop para criar cada inimigo definido por cada coordenada em que foi declarada lá em cima
            for (int i = 0; i < coordenadas.length; i++) {
                //aqui é criado cada inimigo, recebendo suas posições x e y (total de 30 inimigos)
                inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
            }
            //e se não tiver em jogo, ele apresentará uma mensagem no console avisando o ocorrido.
        } else {
            System.out.println("Não foi possível gerar os inimigos, não está em jogo!");
        }
    }

    //método responsável por checar se os objetos estão se colidindo
    //fazendo ser possível saber se o inimigo acerta a nave ou se o míssel acerta o inimigo
    public void checarColisoes() {
        //se tiver em jogo, ele criará um retangulo para cada objeto(nave, missel, inimigo)
        if (emJogo) {
            Rectangle formaNave = nave.getBounds(); //o retângulo assumirá a altura e largura da imagem estabelescida na classe nave
            Rectangle formaInimigo;
            Rectangle formaMissel;

            //para ser possível checar inimigo por inimigo, terá que criar um for com o tamanho da quantidade de inimigos
            //para que seja analizada um por um.
            for (int i = 0; i < inimigos.size(); i++) {

                //aqui pega o índice do inimigo
                Inimigo tempInimigo = inimigos.get(i);
                //aqui pega a altura e largura da imagem definida na classe inimigo.
                formaInimigo = tempInimigo.getBounds();

                //aqui é utilizado o método intersects, que através dele podemos saber se
                //um rectangle está sobrepondo ao outro.
                //no caso, aqui está analizando se a nave toca ao inimigo
                if (formaNave.intersects(formaInimigo)) {
                    //se verdadeiro, a nave deixa de ser visível
                    nave.setIsVisivel(false);
                    //os inimigos deixam de ser visíveis
                    tempInimigo.setIsVisivel(false);

                    //o jogo encerra e perdeu se torna verdadeiro.
                    emJogo = false;
                    perdeu = true;
                }
            }

            //agora iremos checar a colisão do missel com inimigos,
            //para isso eu instancio a lista dos mísseis que forem gerados
            List<Missel> misseis = nave.getMisseis();

            //como se trata de mais de 1 missel, crio um for para analisar missel por missel
            for (int i = 0; i < misseis.size(); i++) {
                //crio um missel temporário para cada índice de míssel gerado
                Missel tempMissel = misseis.get(i);
                //pego a altura e largura da imagem recebida na classe míssel e
                //defino como tamanho para o rectangle
                formaMissel = tempMissel.getBounds();

                //como se trata de mais de 1 inimigo, será necessário criar o for para a quantidade de inimigos
                for (int j = 0; j < inimigos.size(); j++) {

                    //crio um inimigo temporário para cada índice de inimigo
                    Inimigo tempInimigo = inimigos.get(j);
                    //pego a altura e largura da imagem recebida na classe inimigo e
                    //defino como tamanho para o rectangle
                    formaInimigo = tempInimigo.getBounds();

                    //se o míssel for visível
                    if (tempMissel.isIsVisivel()) {

                        //e se o míssel colidir com algum inimigo
                        if (formaMissel.intersects(formaInimigo)) {
                            //o inimigo acertado deixará de ser visível
                            tempInimigo.setIsVisivel(false);
                            //o míssel em que acertou, deixará de ser visível\
                            tempMissel.setIsVisivel(false);
                        }
                    }

                }
            }
        }
    }

    @Override
    //método responsável por "printar" os objetos na tela
    public void paintComponent(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);

        //se tiver em jogo
        if (emJogo) {
            //ele printará na tela a nave, pegando sua posição X e Y juntamente de sua imagem
            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this); //método responsável por printar na tela (drawImage)
            
            //aqui novamente pego a lista de misseis, só que agora será pra printar
            //cada míssel disparado na tela
            List<Missel> misseis = nave.getMisseis();

            //então criarei um for para que seja printado cada novo míssel
            for (int i = 0; i < misseis.size(); i++) {
                Missel m = (Missel) misseis.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }
            
            //farei o mesmo com os inimigos
            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo in = inimigos.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            
            //aqui eu crio a interface do canto superior esquerdo, onde contém a INIMIGOS
            //ela é responsável por dizer quantos inimigos faltam ser abatidos
            graficos.setColor(Color.WHITE); //aqui eu altero a cor da fonte para branco
            graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 15); //aqui eu crio de fato o texto
        }

        //se o menu for verdadeiro, eu puxo a imagem do menu
            if (emJogo) {

            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
            List<Missel> misseis = nave.getMisseis();

            for (int i = 0; i < misseis.size(); i++) {
                Missel m = (Missel) misseis.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo in = inimigos.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            graficos.setColor(Color.WHITE);
            graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 15);
        }

        if (menu) {
            ImageIcon menu = new ImageIcon(getClass().getResource("/res/capa.png"));
            graficos.drawImage(menu.getImage(), 0, 0, null);
        }

        if (perdeu) {
            ImageIcon fimJogo = new ImageIcon(getClass().getResource("/res/derrota.jpg"));
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        if (ganhou) {
            ImageIcon fimJogo = new ImageIcon(getClass().getResource("/res/vitoria.jpg"));
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    //esse método será responsável por algumas funcionalidades do joguinho
    public void actionPerformed(ActionEvent arg0) {

        //se tiver em jogo
        if (emJogo) {
            //e se a quantidade de inimigos for = 0
            if (inimigos.size() == 0) {
                //o jogo finaliza e o boolean ganhou passa para verdadeiro,
                //fazendo com que apareça a tela de vitória
                emJogo = false;
                ganhou = true;
            }

            //aqui novamente pego a lista de misseis, só que agora será pra movimentar
            //cada míssel disparado na tela
            List<Missel> misseis = nave.getMisseis();

            //novamente um for para movimentar cada missel
            for (int i = 0; i < misseis.size(); i++) {
                Missel m = (Missel) misseis.get(i);

                //se o míssel for visível
                if (m.isIsVisivel()) {
                    //o míssel puxa o método mexer da classe missel, fazendo o missel se movimentar
                    m.mexer();
                } else {
                    //e se o míssel não for visível, ele será removido
                    misseis.remove(i);
                }
            }

            //aqui ocorre a mesma coisa, porém com os inimigos
            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo in = inimigos.get(i);

                if (in.isIsVisivel()) {
                    in.mexer();
                } else {
                    inimigos.remove(i);
                }
            }

            //aqui puxa o método mexer da classe nave
            nave.mexer();
            //aqui puxa o método de checarColisões, feita anteriormente
            checarColisoes();
            //aqui serve para repintar os elementos
            repaint();
        }
    }

    //esse método serve pra analisar as teclas, que nem o da classe nave,
    //porém com algumas adaptações
    private class TecladoAdapter extends KeyAdapter {

        //aqui analisa a tecla ENTER, para que toda vez que seja precionada,
        //ele recomece o jogo
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                
                menu = false;
                emJogo = true;
                ganhou = false;
                perdeu = false;
                nave = new Nave();
                inicializaInimigos();
            }
            
            //e se tiver em jogo
            if (emJogo) {
                //ele pega o método KeyPressed da classe nave, que é responsável
                //pela movimentação e disparo da nave
                nave.keyPressed(e);
            }
        }

        @Override
        //nesse método ocorre a mesma coisa, porém ao soltar a tecla
        public void keyReleased(KeyEvent e) {
            if (emJogo) {
                nave.keyReleased(e);
            }
        }

    }

}
