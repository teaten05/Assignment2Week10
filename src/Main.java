import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

    public static void main(String[] args) {

        /*Job[] jobs1 = {new Job(1, 4),
                new Job(2, 2),
                new Job(3, 8),
                new Job(4, 6),
                new Job(5, 1)};*/

        Job[] jobs1 = getTxtInput("lib/task1-input.txt");

        task1(jobs1);

        /*Job[] jobs2 = {new Job(1, 4, 1),
                new Job(2, 2, 2),
                new Job(3, 8, 2),
                new Job(4, 6, 1),
                new Job(5, 1, 2)}; */

        Job[] jobs2 = getTxtInput("lib/task2-input.txt");

        task2(jobs2);
        /*
        Job[] jobs3 = {new Job(1, 4, 0),
                new Job(2, 2, 1),
                new Job(3, 8, 2),
                new Job(4, 6, 3),
                new Job(5, 1, 5)}; */

        Job[] jobs3 = getTxtInput("lib/task3-input.txt");

        task3(jobs3);
    }

    public static void task1(Job[] jobs){

        MinPriorityQueue<Job> priorityQueue = new MinPriorityQueue<>();

        for(Job x: jobs){

            priorityQueue.insert(x);

        }

        int[] execution = new int[jobs.length];
        int procTime = 0;
        int time = 0;
        double avgTime;

        for(int i = 0; i < jobs.length; i++){

            Job job = priorityQueue.delMin();
            procTime += job.getCompletionTime();
            time += procTime;
            execution[i] = job.getJobId();

        }

        avgTime = (double) time/jobs.length;

        System.out.print("execution order: [");
        for(int i = 0; i < execution.length; i++){

            System.out.print(execution[i]);

            if(i< execution.length - 1){

                System.out.print(", ");

            }

        }
        System.out.println("]/nAverage completion time: "+ avgTime);

    }

    public static void task2(Job[] jobs){

        MinPriorityQueue<Job> priorityQueue = new MinPriorityQueue<>(Job.jobComparator);

        for(Job x : jobs){

            priorityQueue.insert(x);

        }

        int[] execution = new int[jobs.length];
        int procTime = 0;
        int time = 0;
        double avgTime;

        for(int i = 0; i < jobs.length; i++){

            if(!priorityQueue.isEmpty()){

                Job job = priorityQueue.delMin();
                procTime += job.getCompletionTime();
                time += procTime;
                execution[i] = job.getJobId();

            }

        }

        avgTime = (double) time/jobs.length;

        System.out.print("execution order: [");
        for(int i = 0; i < execution.length; i++){

            System.out.print(execution[i]);

            if(i< execution.length - 1){

                System.out.print(", ");

            }

        }
        System.out.println("]/nAverage completion time: "+ avgTime);

    }

    public static void task3(Job[] jobs) {

        MinPriorityQueue<Job> priorityQueue = new MinPriorityQueue<>(Job.arrivalComparator);

        int current = 0;
        int time = 0;
        int[] execution = new int[jobs.length];
        double avgTime;

        int index = 0;
        int j = 0;
        while(j < jobs.length){

            while (index < jobs.length && jobs[index].getPriority() <= current){

                priorityQueue.insert(jobs[index]);
                index++;

            }

            if(!priorityQueue.isEmpty()){

                Job job = priorityQueue.delMin();
                current += job.getCompletionTime();
                time += current;
                execution[j] = job.getJobId();
                j++;

            } else if (index < jobs.length){

                current = jobs[index].getPriority();

            }

        }

        avgTime = (double) time/jobs.length;

        System.out.print("execution order: [");
        for(int i = 0; i < execution.length; i++){

            System.out.print(execution[i]);

            if(i< execution.length - 1){

                System.out.print(", ");

            }

        }
        System.out.println("]/nAverage completion time: "+ avgTime);

    }

    public static Job[] getTxtInput(String file){

        List<Job> list = new ArrayList<>();

        try(BufferedReader read = new BufferedReader(new FileReader(file))){

            String line;
            while((line = read.readLine()) != null){

                String[] num = line.split(" ");

                if(num.length == 2){

                    int j = Integer.parseInt(num[0].trim());
                    int pt = Integer.parseInt(num[1].trim());
                    list.add(new Job(j, pt));

                } else if (num.length == 3){

                    int j = Integer.parseInt(num[0].trim());
                    int pt = Integer.parseInt(num[1].trim());
                    int at = Integer.parseInt(num[2].trim());

                    list.add(new Job(j, pt, at));

                } else {

                    System.out.println("Did not work.");

                }

            }

        } catch (IOException e){

            System.out.println("Error when reading file.");

        }

        return list.toArray(new Job[0]);

    }
}