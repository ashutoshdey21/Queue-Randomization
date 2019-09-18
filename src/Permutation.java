import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){


        int k=Integer.parseInt(args[0]);
        RandomizedQueue<String> queue=new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            queue.enqueue(str);
        }
        if(k>queue.size())StdOut.println("insufficient elements in the queue");
        else{
            while(k>0){
                StdOut.print(queue.dequeue()+" ");
                --k;
            }
        }



    }


}