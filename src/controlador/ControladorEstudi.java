package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Estudi;
import vista.EstudiForm;
import vista.EstudiLlista;
import vista.MenuEstudiVista;
import javax.swing.JButton;
import persistencia.GestorPersistencia;
import persistencia.GestorXML;
import principal.GestorEstudisException;
import persistencia.ProveedorPersistencia;
/**
 *
 * @author FTA
 */
public class ControladorEstudi implements ActionListener {

    private MenuEstudiVista menuEstudiVista;
    private EstudiForm estudiForm = null;
    private EstudiLlista estudiLlista = null;
    private int opcioSelec = 0;

    public ControladorEstudi() {

        /*
        TODO
        
        S'inicialitza l'atribut menuEstudiVista (això mostrarà el menú estudis)
        Es crida a afegirListenersMenu
        
         */
        this.menuEstudiVista = new MenuEstudiVista();
        afegirListenersMenu();

    }

    //El controlador com a listener dels controls de les finestres que gestionen els estudis
    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL MENU
    private void afegirListenersMenu() {
        /*
        TODO
        
        A cada botó del menú estudis, s'afegeix aquest mateix objecte (ControladorEstudi) com a listener
        
         */
        JButton[] botonsMenuEstudi =  menuEstudiVista.getMenuButtons();
        
        for(int i=0; i<botonsMenuEstudi.length; i++){
            botonsMenuEstudi[i].addActionListener(this);
        }

    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL FORMULARI
    private void afegirListenersForm() {
        /*
        TODO
        
        A cada botó del formulari de l'estudi, s'afegeix aquest mateix objecte (ControladorEstudi) com a listener
        
         */
        JButton desarEstudi = estudiForm.getbDesar();
        JButton sortirEstudi = estudiForm.getbSortir();
        desarEstudi.addActionListener(this);
        sortirEstudi.addActionListener(this);
                
 
    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DEL BOTO DE LA LLISTA
    private void afegirListenersLlista() {
        /*
        TODO
        
        Al botó de sortir de la llista d'estudis, s'afegeix aquest mateix objecte (ControladorEstudi) com a listener
        */
        JButton sortirLlista = estudiLlista.getbSortir();
        sortirLlista.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO
        
        Nota:
            Com ControladorEstudi és listener del menú d'estudis, del formulari i de la llista, llavors en aquest mètode
            actionPerformed heu de controlar si l'usuari ha premut algun botó de qualsevol dels esmentats frames.
            Ull! En el cas del formulari i de la llista, com provenen del menú (els llança el menú d'estudis), heu de verificar
            primer que els objectes estudiForm o estudiLlista no són nulls, per tal de saber si podeu comparar-los amb
            alguns dels botons d'aquests frames.
        
        Accions per al menú:
            S'ha de cridar a bifurcaOpcio segons l'opció premuda. Penseu que l'opció es correspon amb
            la posició que el botó ocupa a l'array de botons de menuEstudiVista
            També, heu d'actualitzar la propietat opcioSelec (amb l'opció que ha premut l'usuari)
        
        Accions per al formulari d'estudi:
            
            ---- DESAR ----
            Si el botó premut per l'usuari és el botó de desar del formulari d'estudi, llavors
                Si l'opció seleccionada (al menú d'estudis) és 1 (alta), llavors  
                        Es crea un nou objecte Estudi amb les dades del formulari
                        S'afegeix l'estudi creat a la llista de ControladorPrincipal
                        Es posa aquest estudi com estudiActual (de ControladorPrincipal) i es canvia l'atribut
                        opcioSelec a 2
                Si l'opció seleccionada (al menú d'estudis) és 3 (modificació), llavors
                    Nota: no es validen dades amb aquesta opció (per simplificar)
                    Es modifica l'objecte estudi amb les dades del formulari (penseu que és l'estudiActual de ControladorPrincipal)
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir del formulari d'estudis, llavors
                Heu de tornar al menú d'estudis (i amagar el formulari)
        
        Accions per a la llista d'estudis:
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir de la llista d'estudis, llavors
                Heu de tornar al menú d'estudis (i amagar la llista)
         
         */
        
        JButton[] menuButtons = menuEstudiVista.getMenuButtons();

            for(int i=0; i<menuButtons.length; i++){
                    if (menuButtons[i].equals((JButton) e.getSource())){
                        opcioSelec = i;
                        break;
                    }    
                }
            
         switch (opcioSelec){
        
            case 0:
                 bifurcaOpcio(opcioSelec);
                break;
            case 1:
                if (estudiForm == null){bifurcaOpcio(opcioSelec);}
                else{
                    estudiForm.getFrame().setVisible(true);
                    if(estudiForm.getbDesar()==e.getSource()){
                        String nom = (estudiForm.gettNom()).getText();
                        String adreca = (estudiForm.gettAdreca()).getText();
                        Estudi nouEstudi = new Estudi(nom,adreca);
                        ControladorPrincipal.getEstudis()[ControladorPrincipal.getPosicioEstudis()]= nouEstudi;
                        ControladorPrincipal.setPosicioEstudis();
                        ControladorPrincipal.setEstudiActual(nouEstudi);
                        
                        //opcioSelec = 2;
                    }
                    if(estudiForm.getbSortir()== e.getSource()){
                        menuEstudiVista.getFrame().setVisible(true);
                        estudiForm.getFrame().setVisible(false);
                       // Al sortir eliminem el formulari estudi,
                       estudiForm = null;
                    }
                }
                break;    
            case 2:
                 bifurcaOpcio(opcioSelec);
                break;
            case 3:
                
                if (estudiForm == null){bifurcaOpcio(opcioSelec);}
                else{
                    estudiForm.getFrame().setVisible(true);
                
                    if(estudiForm.getbDesar()==e.getSource()){
                        String nom = (estudiForm.gettNom()).getText();
                        String adreca = (estudiForm.gettAdreca()).getText();
                        ControladorPrincipal.getEstudiActual().setNom(nom);
                        ControladorPrincipal.getEstudiActual().setAdreca(adreca);
                        
                    }
                    if(estudiForm.getbSortir()== e.getSource()){
                        menuEstudiVista.getFrame().setVisible(true);
                        estudiForm.getFrame().setVisible(false);
                        //Al sortir eliminem el formulari estudi
                        estudiForm = null;
                    }
                }
                break;
            case 4:
                if (estudiLlista == null){bifurcaOpcio(opcioSelec);}
                else{
                    estudiLlista.getFrame().setVisible(true);
                    if(estudiLlista.getbSortir()== e.getSource()){
                        estudiLlista.getFrame().setVisible(false);
                        menuEstudiVista.getFrame().setVisible(true);
                        /* Elimino l' estudiLlista actual per que aixi quan el torni
                        a cridar em mostrará les dades actualitzades
                        */
                        estudiLlista = null;
                    }    
                }
                
                break;    
            case 5:
                 bifurcaOpcio(opcioSelec);
                break;
            case 6:
                 bifurcaOpcio(opcioSelec);
                break;
        }
    }
    

    private void bifurcaOpcio(Integer opcio) {

        switch (opcio) {

            case 0: //sortir
                ControladorPrincipal.getMenuPrincipalVista().getFrame().setVisible(true);
                break;

            case 1: // alta
                if (ControladorPrincipal.getPosicioEstudis()< ControladorPrincipal.getMAXESTUDIS()) {
                    estudiForm = new EstudiForm();
                    estudiForm.gettCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuEstudiVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Màxim nombre d'estudis assolits.");
                }
                break;

            case 2: //seleccionar
                menuEstudiVista.getFrame().setVisible(true);
                if (ControladorPrincipal.getEstudis()[0] != null) {
                    seleccionarEstudi();
                } else {
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Abans s'ha de crear al menys un estudi");
                }
                break;

            case 3: //modificar
                if (ControladorPrincipal.getEstudis()[0] != null) {
                    seleccionarEstudi();
                    estudiForm = new EstudiForm(ControladorPrincipal.getEstudiActual().getCodi(), ControladorPrincipal.getEstudiActual().getNom(), ControladorPrincipal.getEstudiActual().getAdreca());
                    estudiForm.gettCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuEstudiVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Abans s'ha de crear al menys un estudi");
                }
                break;

            case 4: // llistar
                if (ControladorPrincipal.getEstudis()[0] != null) {
                    estudiLlista = new EstudiLlista();
                    afegirListenersLlista();
                } else {
                    menuEstudiVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuEstudiVista.getFrame(), "Abans s'ha de crear al menys un estudi");
                }
                break;

            case 5: //carregar
            /*
            TODO
                
            Es mostra un dialog (JOptionPane.showOptionDialog) amb botons, on cadascun d'ells és un mètode de càrrega 
            (propietat a Controlador Principal: ara XML i Serial)
            Un cop seleccionat el mètode, amb un altre dialog, es demana el codi de l'estudi a carregar 
            (recordeu que el nom del fitxer és el codi de l'estudi i l'extensió)
            Un cop l'usuari ha entrat el codi i ha premut OK,
                Es crea un objecte estudi (nouEstudi) com retorn de cridar a carregarEstudi del gestor de persistència. Penseu que la
                carrega pots ser d'un fitxer XML o bé d'un fitxer serial.
                Es comprova si el codi del nouEstudi ja existeix al vector d'estudis (això donarà la posició on s'ha trobat a la llista). Penseu
                que en aquesta classe teniu un mètode per fer la comprovació.
                Si existeix,
                    es mostra un dialog notificant a l'usuari si vol substituir l'estudi del vector pel que es carregarà des de el fitxer (JOptionPane.showOptionDialog)
                    Si l'usuari cancela, no es fa res
                    Si l'usuari accepta, llavors es posa el nouEstudi al vector a la mateixa posició on s'havia trobat aquest codi
                Si no existeix,
                    S'afegeix el nouEstudi al vector d'estudis a la darrera posició
                    Es mostra un missatge confirmant l'addició (JOptionPane.showMessageDialog)
            
            */
                Integer seleccio = JOptionPane.showOptionDialog(
                        menuEstudiVista.getFrame(),
                        "Selecciona un mètode",
                        "Carregar Estudi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        ControladorPrincipal.getMETODESPERSISTENCIA(),
                        "XML");  
                
                String codiEstudi = JOptionPane.showInputDialog(
                            menuEstudiVista.getFrame(),
                            "Quin és el codi de l'estudi que vols carregar ?" ,
                            "Entrada",
                            JOptionPane.QUESTION_MESSAGE); 
                
                    //seleccio 0 "XML" seleccio 1 "Serial"
                Estudi nouEstudi = new Estudi(null,null);
                
                if (codiEstudi != null){
                    if(seleccio == 0){ 
                        try{
                            ControladorPrincipal.getGp().carregarEstudi("XML", codiEstudi);
                            GestorXML gxml = (GestorXML) ControladorPrincipal.getGp().getGestor();
                            nouEstudi = gxml.getEstudi();
                        }catch(Exception e){System.out.println("No s'ha pogut carregar l'estudi a causa d'error d'entrada/sortida");}
                    }else if(seleccio == 1){
                        try{
                        ControladorPrincipal.getGp().carregarEstudi("Serial", codiEstudi);
                        }catch(Exception e){System.out.println("No s'ha pogut carregar l'estudi a causa d'error d'entrada/sortida");}
                    }
                }
                
                ControladorPrincipal.setEstudiActual(nouEstudi);
                int codEstudi = nouEstudi.getCodi();
                
                // Comprovem si aquest estudi es troba al vector estudis.
                int posEstudis = comprovarEstudi(codEstudi);
                
                if (posEstudis != -1){
                    seleccio = JOptionPane.showOptionDialog(
                        menuEstudiVista.getFrame(),
                        "Premeu OK per substituir-lo",
                        "Estudi ja existent",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        new String [] {"OK", "Cancel·lar"},
                        "OK");
                    if (seleccio == 0){
                        ControladorPrincipal.getEstudis()[posEstudis] = nouEstudi ;
                    }
                }else{
                   ControladorPrincipal.getEstudis()[ControladorPrincipal.getPosicioEstudis()] = nouEstudi;  
                   JOptionPane.showMessageDialog(
                           menuEstudiVista.getFrame(),
                           "S'ha afegit el nou Estudi",
                           "Missatge",
                           JOptionPane.INFORMATION_MESSAGE);
                }
                
                break;

            case 6: //desar
                /*
                TODO
                
                Es comprova si s'ha seleccionat l'estudi, mostrant, si correspon, missatges d'error (JOptionPane.showMessageDialog)
                Si s'ha sseleccionat l'estudi, 
                    Es mostra un dialog (JOptionPane.showOptionDialog) amb botons, on cadascun d'ells és un mètode de càrrega
                    (propietat a Controlador Principal: ara XML i Serial)
                    Un cop escollit el mètode, es desa l'estudi cridant a desarEstudi del gestor de persistència
                 */
                if (ControladorPrincipal.getEstudiActual()!= null){
                    seleccio = JOptionPane.showOptionDialog(
                        menuEstudiVista.getFrame(),
                        "Selecciona un mètode",
                        "Desar Estudi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        ControladorPrincipal.getMETODESPERSISTENCIA(),
                        "XML");
                    if (seleccio == 0){
                        Estudi estudiGuardar = ControladorPrincipal.getEstudiActual();
                        String nomArxiu = String.valueOf(estudiGuardar.getCodi()); 
                        try{
                        ControladorPrincipal.getGp().desarEstudi("XML", nomArxiu, estudiGuardar);
                        }catch(Exception e){}
                    }else if (seleccio == 1){
                        Estudi estudiGuardar = ControladorPrincipal.getEstudiActual();
                        String nomArxiu = String.valueOf(estudiGuardar.getCodi()) +"Serial"; 
                        try{
                        ControladorPrincipal.getGp().desarEstudi("Serial", nomArxiu, estudiGuardar);
                        }catch(Exception e){}
                    }
                    
                    
                
                }else{
                    JOptionPane.showMessageDialog(
                            menuEstudiVista.getFrame(),
                            "Primer has de seleccionar un estudi",
                            "Missatge Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                break;

        }

    }

    private void seleccionarEstudi() {

        String[] nomEstudi = new String[ControladorPrincipal.getPosicioEstudis()];

        int i = 0;

        for (Estudi estudi : ControladorPrincipal.getEstudis()) {

            if (estudi != null) {
                nomEstudi[i] = estudi.getNom();
            }

            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(null, "Selecciona un estudi", "Selecció d'estudi", 0, messageType, null, nomEstudi, "A");
        
        if (code != JOptionPane.CLOSED_OPTION) {
            ControladorPrincipal.setEstudiActual(ControladorPrincipal.getEstudis()[code]);
        }

    }

    private Integer comprovarEstudi(int codi) {

        boolean trobat = false;

        int pos = -1;

        for (int i = 0; i < ControladorPrincipal.getEstudis().length && !trobat; i++) {

            if (ControladorPrincipal.getEstudis()[i] != null) {
                if (ControladorPrincipal.getEstudis()[i].getCodi() == codi) {
                    pos = i;
                    trobat = true;
                }
            }
        }

        return pos;
    }

}