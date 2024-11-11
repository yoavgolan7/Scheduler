import java.util.Random;
import java.util.Scanner;

public class Scheduler implements Runnable{
    private static Scheduler instance=null;
    private static Task[] threads=null;
    private static String[] names=null;
    public static Scheduler getInstance(Task[] t){
        if (instance!=null)
            return instance;
        instance=new Scheduler(t);
        return instance;
    }
    public static Scheduler getInstance(){
        if (instance!=null)
            return instance;
        instance=new Scheduler(null);
        return instance;
    }
    private static Boolean exit=false;
    private Scheduler(Task[] t)
    {

        threads=t;
        names = new String[threads.length];
        for(int i=0;i< names.length;i++)
            names[i]=threads[i].getName();
    }
    public static void setThreads(Task[] t){
        threads=t;
    }
    public void transferMsg(String tName, String msg, Boolean noti, int i){
        if(noti)
            threads[i].addMsg(msg);
        else threads[i].addMsg_noNotify(msg);
    }
    public int findTask(String tName){
        int i=0;
        for(; i< names.length;i++){
        if(names[i]!=tName)
            return i;
        }
        return -1;
    }
    @Override
    public void run(){
        Scanner scanner = new Scanner(System.in);
        Random rnd = new Random();
        while(!exit){
            String msg=""; String name; String noti;
            System.out.println("Task name:");
            name = scanner.next();
            int index =findTask(name);
            if(name.toLowerCase()=="exit"){
                exit=true;
                for(int i =0;i<threads.length;i++)
                    threads[i].stopGoing();
                continue;
            }
            else if(index==-1)
                continue;
            for(int i=0;i<5;i++)
                msg+=(char)((int)'A'+ rnd.nextInt(26));
            System.out.println("msg="+msg);
            System.out.println("notify now?");
            noti = scanner.next();
            if(noti.toLowerCase() =="no"||noti.toLowerCase()=="n"||noti=="0")
                transferMsg(name,msg,false,index);
            else
                transferMsg(name,msg,true,index);

        }
    }

}
