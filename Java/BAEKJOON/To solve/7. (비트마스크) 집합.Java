//https://www.acmicpc.net/problem/11723
//First try
import java.io.*;
import java.util.*;

class Scratch {
    static int bit;
    public static void main(String[] args) throws IOException{
        bit = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        for (int i=0; i<M; i++){
            String[] command = br.readLine().split(" ");
            if (command[0].equals("add")) {
                int num = Integer.parseInt(command[1]);
                add(num);
            }
            else if (command[0].equals("remove")) {
                int num = Integer.parseInt(command[1]);
                remove(num);
            }
            else if (command[0].equals("check")) {
                int num = Integer.parseInt(command[1]);
                check(num);
            }
            else if (command[0].equals("toggle")) {
                int num = Integer.parseInt(command[1]);
                toggle(num);
            }
            else if (command[0].equals("all")) all();
            else empty();
        }
    }
    public static void add(int num){ bit |= (1<<(num-1)); }
    public static void remove(int num){ bit &= ~(1<<(num-1)); }
    public static void check(int num){
        System.out.println("Check: "+num+" "+ ((bit & 1<<(num-1)) >> (num-1) == 1 ? "1" : "0"));
    }
    public static void toggle(int num){
        if ((bit & 1<<(num-1)) >> (num-1) == 1) remove(num);
        else add(num);
    }
    public static void all(){ for(int i=0; i<20; i++) bit |= 1<<(i); }
    public static void empty(){ bit = 0; }
}

//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    static int bit = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                bit |= (1<<(num-1));
            }
            else if (command.equals("remove")) {
                int num = Integer.parseInt(st.nextToken());
                bit &= ~(1<<(num-1));
            }
            else if (command.equals("check")) {
                int num = Integer.parseInt(st.nextToken());
                sb.append(((bit & 1<<(num-1)) != 0 ? 1 : 0)).append('\n');
            }
            else if (command.equals("toggle")) {
                int num = Integer.parseInt(st.nextToken());
                bit ^= (1<<(num-1));;
            }
            else if (command.equals("all")) bit = (1<<20)-1;
            else bit = 0;
        }
        System.out.print(sb);
    }
}