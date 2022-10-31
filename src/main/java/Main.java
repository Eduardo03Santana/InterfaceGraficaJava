import GUI.Cadastro;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Cadastro cadastro = new Cadastro();
        SwingUtilities.invokeLater(cadastro::criarTela);
    }
}