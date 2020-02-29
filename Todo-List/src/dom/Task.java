package dom;

import exceptions.InvalidTaskException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Task    {
    private Personne createur;
    private String desc;
    private Date datePlanifiee;
    private Status currentStatus;
    private Resolution resolution;

    public Task(Personne createur, String desc, Date datePlanifiee) throws InvalidTaskException {
        if(datePlanifiee.before(new Date())){
            throw new InvalidTaskException("La tache est planifiée pour une date passée.");
        }
        this.createur = createur; this.desc = desc; this.datePlanifiee = datePlanifiee;
        currentStatus = Status.OPEN;
        resolution = Resolution.TODO;
    }

    public void replanTask(Date nouvelleDate){ datePlanifiee = nouvelleDate; }
    public void cancelTask(){ currentStatus = Status.CANCELED; resolution = Resolution.IGNORED; }
    public void validerTache(){ currentStatus = Status.CLOSED; resolution = Resolution.DONE; }

    public boolean isOfStatus(Status s){
        return s == currentStatus;
    }

    public boolean isAtDate(Date d){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(d).equals(fmt.format(datePlanifiee));
    }

    @Override
    public String toString() {
        return "Créée par : " + createur.toString() + "\nDescription : " + desc + "\nDate : " + datePlanifiee.toString() + "\nStatus : " + currentStatus.toString() + "\nResolution : " + resolution.toString();
    }
    // On ne prend pas en comptre le status ni la résolution d'une tâche
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(createur, task.createur) &&
                Objects.equals(desc, task.desc) &&
                Objects.equals(datePlanifiee, task.datePlanifiee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createur, desc, datePlanifiee);
    }

    public Date getDatePlanifiee() {
        return datePlanifiee;
    }
}
