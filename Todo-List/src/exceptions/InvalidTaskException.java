package exceptions;

public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super("Tache invalide : " + message);
    }
}
