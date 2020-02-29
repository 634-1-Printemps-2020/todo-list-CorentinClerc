package dom;

import exceptions.CantAddTaskException;
import exceptions.CantFindTaskException;
import metier.GestionTaches;
import java.util.*;

public class Personne {
    private String nom, prenom;
    private GestionTaches taches;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        taches = new GestionTaches();
    }

    public void addTache(Task task) throws CantAddTaskException { taches.add(task); }

    public void cancelTask(Task t) throws CantFindTaskException { taches.cancelTask(t); }

    public void replanifierTache(Task t, Date d) throws CantFindTaskException { taches.replanTask(t, d); }

    public void consulterTaches(){
        System.out.println(taches.getTasks());
    }
    public void consulterTaches(Status s){
        System.out.println(taches.getTasks(s));
    }
    public void consulterTaches(Date d){
        System.out.println(taches.getTasks(d));
    }
    public void consulterTaches(Status s, Date d){
        System.out.println(taches.getTasks(s, d));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(nom, personne.nom) &&
                Objects.equals(prenom, personne.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom);
    }
}
