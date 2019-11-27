package vista;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author FTA
 */
public class EstudiForm {
    
    private JFrame frame;
    
    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lCodi;
    private JTextField tCodi;
    private JLabel lNom;
    private JTextField tNom;
    private JLabel lAdreca;
    private JTextField tAdreca;

    private JButton bDesar;   
    private JButton bSortir;   

    public EstudiForm() {
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Formulari Estudi" i layout Grid d'una columna
            Heu de crear els controls del formulari (labels i textFields).
            Heu de crear els botons del formulari
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada que proposen les propietats d'aquest nom
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
       
        */
        this.frame = new JFrame("Formulari Estudi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(AMPLADA,ALCADA);
        
        GridLayout posicions = new GridLayout(8,1);
        
        this.lCodi = new JLabel("Codi");
        this.tCodi = new JTextField();
        this.lNom = new JLabel("Nom");
        this.tNom = new JTextField();
        this.lAdreca = new JLabel("Adreça");
        this.tAdreca = new JTextField();
        
        this.bDesar = new JButton("Desar");
        this.bSortir = new JButton("Sortir");
        
        Container contingut = frame.getContentPane();
        
        contingut.add(lCodi);
        contingut.add(tCodi);
        contingut.add(lNom);
        contingut.add(tNom);
        contingut.add(lAdreca);
        contingut.add(tAdreca);
        contingut.add(bDesar);
        contingut.add(bSortir);
        
        contingut.setLayout(posicions);
        frame.setVisible(true);
        
    }
    
    public EstudiForm(int codi, String nom, String adreca){
        this();
        tCodi.setText(String.valueOf(codi));
        tNom.setText(nom);
        tAdreca.setText(adreca);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField gettCodi() {
        return tCodi;
    }

    public void settCodi(JTextField tCodi) {
        this.tCodi = tCodi;
    }

    public JTextField gettNom() {
        return tNom;
    }

    public void settNom(JTextField tNom) {
        this.tNom = tNom;
    }

    public JTextField gettAdreca() {
        return tAdreca;
    }

    public void settAdreca(JTextField tAdreca) {
        this.tAdreca = tAdreca;
    }

    public JButton getbDesar() {
        return bDesar;
    }

    public void setbDesar(JButton bDesar) {
        this.bDesar = bDesar;
    }
    
    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }
    
}
