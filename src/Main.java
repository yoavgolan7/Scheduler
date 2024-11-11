// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        Task[] tasks = new Task[5];
        for(int i=1;i<=5;i++) {
            tasks[i-1] = new Task("t" + i);
            threads[i-1]= new Thread(tasks[i-1]);
            threads[i-1].start();
        }
        Scheduler scheduler = Scheduler.getInstance(tasks);
        Thread schThread = new Thread(scheduler);
        schThread.run();

    }
}