public class Task implements Runnable{
    private String[] msg;
    private String name;
    private Boolean exitPing;
    public Task(String name){
        this.msg = new String[1];
        this.name = name;
        this.exitPing=false;
    }
    public String nextMsg(){
        String returnMe = this.msg[0];
        if(returnMe==null)
            returnMe="";
        int l =this.msg.length;
        if(l==1)
        {
            this.msg = new String[1];
            return returnMe;
        }
        String[] newArr = new String[l-1];
        System.arraycopy(this.msg, 1,newArr, 0, l-1);
        this.msg=newArr;
        return returnMe;
    }
    public void addMsg(String text){
        synchronized (this) {
            addMsg_noNotify(text);
            //for(int i = 0; i< msg.length; i++)
              //  System.out.println(msg[i]);
            this.notify();
        }
    } public void addMsg_noNotify(String text){
            if(msg[0]==null){
                msg[0]=text;
                return;
            }
            int l = this.msg.length;
            String[] newArr = new String[l + 1];
            System.arraycopy(this.msg, 0, newArr, 0, l);
            newArr[l] = text;
            this.msg = newArr;
    }

    @Override
    public void run(){

            synchronized (this) {
                while(!exitPing) {
                    while (this.msg[0] == null) {
                        try {
                            this.wait();
                        }
                        catch (InterruptedException e) {}
                    }
                    while (this.msg[0] != null)
                        System.out.println(this.name + ": " + nextMsg());
                }
        }

    }
    public void stopGoing(){
        this.exitPing=true;
    }

    public String getName() {
        return name;
    }
}

