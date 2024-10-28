import java.util.Comparator;

public class Job implements Comparable<Job>{
    private int jobId = 0;
    private int completionTime = 0;
    private int arrivalTime = 0;
    int priority = 0;

    public Job(int j, int c){

        jobId = j;
        completionTime = c;

    }

    public Job(int j, int c, int p){

        jobId = j;
        completionTime = c;
        priority = p;

    }


    public int getJobId(){

        return this.jobId;

    }

    public int getCompletionTime(){

        return this.completionTime;

    }

    public int getPriority(){

        return this.priority;

    }

    public static Comparator<Job> jobComparator = new Comparator<Job>(){

        public int compare(Job a, Job b){

            if (a.getPriority() != b.getPriority()){

                return Integer.compare(a.getPriority(), b.getPriority());

            } else {

                return Integer.compare(a.completionTime, b.completionTime);

            }

        }

    };

    public static Comparator<Job> arrivalComparator = new Comparator<Job>() {
        @Override
        public int compare(Job a, Job b) {

            if(a.completionTime != b.completionTime){

                return Integer.compare(a.completionTime, b.completionTime);

            }

            return Integer.compare(a.priority, b.priority);

        }

    };

    public int compareTo(Job j){

        return Integer.compare(this.completionTime, j.completionTime);

    }

}
