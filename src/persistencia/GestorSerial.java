package persistencia;

import java.io.File;
import principal.GestorEstudisException;
import model.Estudi;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author FTA
 */
public class GestorSerial implements ProveedorPersistencia{
    
    private Estudi estudi;

    public Estudi getEstudi() {
        return estudi;
    }

    public void setEstudi(Estudi estudi) {
        this.estudi = estudi;
    }

    @Override
    public void desarEstudi(String nomFitxer, Estudi estudi) throws GestorEstudisException {
        /*
         *TODO
         *
         *Paràmetres: nom del fitxer i desti
         *
         *Acció:
         * - Ha de desar l'objecte Estudi serialitzat sobre un fitxer del sistema 
         *   operatiu amb nom nomFitxer i extensió ".ser".
         * - Heu de controlar excepcions d'entrada/sortida i en cas de produïrse alguna, 
         *   llavors llançar GestorEstudisException amb codi GestorSerial.desar 
         *
         *Nota: podeu comprovar que la classe Estudi implementa Serializable  
         *
         *Retorn: cap
         */
        try{
            File f = new File(nomFitxer + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(f));
            oos.writeObject(estudi);
            oos.close();
        }catch(Exception ex){throw new GestorEstudisException("GestorSerial.desar");}
    }

    @Override
    public void carregarEstudi(String nomFitxer) throws GestorEstudisException {
        /*
         *TODO
         *
         *Paràmetres: nom del fitxer
         *
         *Acció:
         * - Ha de carregar el fitxer del sistema operatiu amb nom nomFitxer i extensió 
         *   ".ser" sobre un nou objecte Estudi que es retornarà com a resultat.               
         * - Heu de controlar excepcions d'entrada/sortida i en cas de produïrse alguna, 
         *   llavors llançar GestorEstudisException amb codi GestorSerial.carrega 
         *
         *Retorn: cap
         */
        try{
            File f = new File(nomFitxer + ".ser");
            ObjectInputStream ois = new ObjectInputStream (new FileInputStream(f));
            estudi = (Estudi)ois.readObject();
            ois.close();
        }catch(Exception ex){throw new GestorEstudisException ("GestorSerial.carrega");}
              
    }
}