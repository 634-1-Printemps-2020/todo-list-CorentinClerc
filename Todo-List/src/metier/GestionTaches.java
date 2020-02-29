package metier;

import dom.Status;
import dom.Task;
import exceptions.CantAddTaskException;
import exceptions.CantFindTaskException;

import java.security.InvalidParameterException;
import java.util.*;

public class GestionTaches {
    private List<Task> tasks;

    public GestionTaches(){
        tasks = new ArrayList<>();
    }
    public void add(Task t) throws CantAddTaskException {
        // Contains ici vérifie la personne, la date et la description, pas le status ni la résolution (on ne peut donc pas accepter une tache qu'on a déjà résolu par exemple)
        if(tasks.contains(t)){
            throw new CantAddTaskException("La tache a déjà été acceptée");
        }
        tasks.add(t);
    }

    public void cancelTask(Task t) throws CantFindTaskException {
        if(!getTasks().contains(t)){
            throw new CantFindTaskException("La tache est introuvable, annulation impossible");
        }
        t.cancelTask();
    }

    public void replanTask(Task t, Date d) throws CantFindTaskException, InvalidParameterException {
        if(!getTasks().contains(t)){
            throw new CantFindTaskException("La tache est introuvable, replanification impossible");
        }
        if(new Date().after(t.getDatePlanifiee())){
            throw new InvalidParameterException("La date donnée est antécdante à la date d'aujourd'hui");
        }
        t.replanTask(d);
    }

    // Je me demandais : Laquelle est la meilleure façon de gérer le polymorphisme ?
    // Version 1 : Une méthode centrale, avec la gestion pour tous les cas de figures,
    // et des méthodes supplémentaires faisant appel à la centrale avec des paramètres par défaut

    public List<Task> getTasks(){
        return tasks;
    }

    public List<Task> getTasks(Status status) throws InvalidParameterException{
        return getTasks(status, null);
    }

    public List<Task> getTasks(Date d) throws InvalidParameterException{
        return getTasks(null, d);
    }

    public List<Task> getTasks(Status s, Date d) throws InvalidParameterException {
        if(s == null && d == null){
            throw new InvalidParameterException("Le status et la date ne peuvent pas être nulls tous les deux");
        }
        List<Task> toReturn = new ArrayList<>();
            for(Task t : getTasks()){
                if((t.isOfStatus(s) || s == null) && (t.isAtDate(d) || d == null)) {
                    toReturn.add(t);
                }
            }
        return toReturn;
    }
    // Version 2 :
    // Chaque méthode incorpore sa propre logique :
    /*
    public List<Task> getTasks(){
        return tasks;
    }

    public List<Task> getTasks(Status status){
        List<Task> toReturn = new ArrayList<>();
        for(Task t : getTasks()){
            if(t.isOfStatus(status)){
                toReturn.add(t);
            }
        }
        return toReturn;
    }

    public List<Task> getTasks(Date d){
        List<Task> toReturn = new ArrayList<>();
        for(Task t : getTasks()){
            if(t.isAtDate(d)){
                toReturn.add(t);
            }
        }
        return toReturn;
    }

    public List<Task> getTasks(Status s, Date d){
        List<Task> toReturn = new ArrayList<>();
        for(Task t : getTasks()){
            if(t.isOfStatus(s) && t.isAtDate(d)){
                toReturn.add(t);
            }
        }
        return toReturn;
    }*/
}
