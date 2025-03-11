import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StudentWithClock implements Learner {
    private Learner learner;

    public StudentWithClock(Learner learner){
        this.learner = learner;
    }

    @Override
    public void learn() {

        learner.learn();
        LocalTime time = LocalTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Текущее время: " + time.format(dtf));
    }
}
