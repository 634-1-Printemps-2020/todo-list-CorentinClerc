package exceptions;

public class CantAddTaskException extends Exception {
    public CantAddTaskException(String message) {
        super("Impossible d'ajouter la tache : " + message);
    }
}
