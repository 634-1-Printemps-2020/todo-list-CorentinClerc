package exceptions;

public class CantFindTaskException extends Throwable {
    public CantFindTaskException(String message) {
        super("Tache introuvable : " + message);
    }
}
