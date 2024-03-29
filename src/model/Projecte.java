/*
 * Classe que defineix un projecte. Un projecte es defineix per un codi, NIF del
 * del client que ha encarregat el projecte, si està finalitzat o no, el pressupost 
 * i el dissenyador encarregat en desenvolupar-lo. A més, contindrà un vector de 
 * jardiners encarregats d'implementar el projecte.
 */
package model;

import components.Dissenyador;
import components.Treballador;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import principal.Component;
import principal.GestorEstudisException;

/**
 *
 * @author root
 */
public class Projecte implements Component {

    private int codi;
    private static int properCodi = 1; //El proper codi a assignar
    private String nifClient;
    private boolean finalitzat;
    private double pressupost;
    private Map<String, Treballador> treballadors = new HashMap();

    /*
     TODO
     CONSTRUCTOR
     Paràmetres: valors per tots els atributs de la classe menys el vector, el
     codi i finalitzat.
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     - Assignar a l'atribut codi el valor de l'atribut properCodi i actualitzar
     properCodi amb el següent codi a assignar.
     - Quan es crea un projecte aquest mai està finalitzat, per tant, assignarem
     fals a l'atribut finalitzat.
     - Quan es crea un projecte no té cap dissenyador assignat.
     */
    public Projecte(String nifClient, double pressupost) {
        codi = properCodi;
        properCodi++;
        this.nifClient = nifClient;
        this.finalitzat = false;
        this.pressupost = pressupost;
    }

    public Projecte(int codi, String nifClient, double pressupost) {
        this.codi = codi;
        this.nifClient = nifClient;
        this.finalitzat = false;
        this.pressupost = pressupost;
    }

    /*
     TODO Mètodes accessors    
     */
    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public static int getProperCodi() {
        return properCodi;
    }

    public static void setProperCodi() {
        properCodi++;
    }

    public String getNifClient() {
        return nifClient;
    }

    public void setNifClient(String nifClient) {
        this.nifClient = nifClient;
    }

    public boolean isFinalitzat() {
        return finalitzat;
    }

    public void setFinalitzat(boolean finalitzat) {
        this.finalitzat = finalitzat;
    }

    public double getPressupost() {
        return pressupost;
    }

    public void setPressupost(double pressupost) {
        this.pressupost = pressupost;
    }

    public Map<String, Treballador> getTreballadors() {
        return treballadors;
    }

    public void setTreballadors(Map<String, Treballador> treballadors) {
        this.treballadors = treballadors;
    }

    /*
     TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari les dades per consola per crear un nou projecte. Les dades
     a demanar són les que passem per paràmetre en el constructor.
     Retorn: El nou projecte creat.
     */
    public static Projecte addProjecte() {

        String nif;
        double pressupost;

        System.out.println("NIF del client:");
        nif = DADES.next();
        System.out.println("Presupost del projecte:");
        pressupost = DADES.nextDouble();

        return new Projecte(nif, pressupost);
    }

    /*
    TODO
     Paràmetres: cap
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual
     i modificar els atributs corresponents d'aquest objecte. En aquest cas
     només es pot modificar el NIF del client, si està finalitzat o no i el pressupost.
     - En el cas de finalitzat o no, li heu de demanar a l'usuari que si el projecte està  
     finalitzat, introdueixi 1 i en cas contrari 0.
     - Li heu de mostrar a l'usuari el valor actual dels atributs de l'objecte actual, 
     abans de modificar-los. En el cas de l'atribut finalitzat, li heu de mostrar
     el missatge: "\nEl projecte està finalitzat", si el projecte s'ha finalitzat,
     i en cas contrari, el missatge "\nEl projecte no està finalitzat".
     Retorn: cap
     */
    public void updateComponent() {

        System.out.println("\nNIF del client: " + nifClient);
        System.out.println("\nEntra el nou NIF:");
        nifClient = DADES.nextLine();
        System.out.println("\nPresupost del projecte: " + pressupost);
        System.out.println("\nEntra el nou pressupost:");
        pressupost = DADES.nextDouble();

        if (finalitzat) {
            System.out.println("\nEl projecte està finalitzat");
        } else {
            System.out.println("\nEl projecte no està finalitzat");
        }

        System.out.println("\nEl projecte està finalitzat? (1 si està finalitzat o 0 en cas contrari):");

        int estat = DADES.nextInt();

        if (estat == 1) {
            finalitzat = true;
        } else {
            finalitzat = false;
        }
    }

    public void addTreballador(Treballador treballador) {
        if (treballador instanceof Dissenyador) {
            treballadors.put("dissenyador", treballador);
        } else {
            treballadors.put(treballador.getNif(), treballador);
        }
    }

    public void showComponent() throws GestorEstudisException {
        System.out.println("\nLes dades del projecte amb codi " + codi + " són:");
        System.out.println("\nNIF client: " + nifClient);
        System.out.println("\nPressupost: " + pressupost);

        if (finalitzat) {
            System.out.println("\nEl projecte està finalitzat");
        } else {
            System.out.println("\nEl projecte no està finalitzat");
        }

        Iterator<String> clau = treballadors.keySet().iterator();

        while (clau.hasNext()) { //Mentres la clau no sigui l'última...
            treballadors.get(clau.next()).showComponent();
        }

    }
}
