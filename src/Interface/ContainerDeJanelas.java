package Interface;
import javax.swing.JFrame;

public class ContainerDeJanelas extends JFrame {
    //criando o construtor
    public ContainerDeJanelas(){ 
        //Criando o JFrame base para o restante do jogo
        add(new Fase()); // instanciando a fase
        setTitle("Spacenhembi"); // adicionando o título do jogo na barra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setando a operação padrão ao clicar em sair
        setSize(500,400); // setando o tamanho da janela    
        setLocationRelativeTo(null); // setando a localização base em relação a algo (NULL = centralizado)
        setResizable(false); // dizendo se a janela pode ou não ser mudada de tamanho (FALSE = não pode ser alterada)
        setVisible(true); // informando se a janela é visivel ou não
    }
    //método principal
    public static void main(String[] args) {
        new ContainerDeJanelas(); //chamando o construtor no método principal
    }
}
