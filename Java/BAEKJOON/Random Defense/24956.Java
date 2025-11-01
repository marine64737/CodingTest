//https://www.acmicpc.net/problem/24956
//First try
import java.io.*;
import java.util.*;

class Main {
    static long ans=0;
    static long C = (long) 1e9+7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int W = 0;
        int H = 0;
        int E = 0;
        int N = Integer.parseInt(br.readLine());
        int[] Warr = new int[N];
        int[] Harr = new int[N];
        int[] Earr = new int[N];
        int[] dp = new int[N];
        String s = br.readLine();
        char[] arr = s.toCharArray();
        for (char c : arr){
            if (c == 'E') E++;
        }
        int index=0;
        while (index < N){
            Earr[index] = E;
            if (arr[index] == 'E') E--;
            index++;
        }
        for (int i=0; i<N; i++){
            if (arr[i] == 'W') {
                W++;
                H=0;
            }
            else if (arr[i] == 'H') {
                H++;
                if (i>0) {
                    long p = pow(Earr[i+1])-(Earr[i+1])-1;
                    ans+=W*((p > 0)? p : 0)%C;
                }
            }
        }
        System.out.print(ans);
    }
    static long pow(int n){
        if (n == 1) return 2;
        long num = pow(n/2)%C;
        if (n%2==0) return num*num%C;
        else return num*num*2%C;
    }
}
//Second try
import java.io.*;
        import java.util.*;

class Main {
    static long ans=0;
    static long C = (long) 1e9+7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int W = 0;
        int H = 0;
        int E = 0;
        int N = Integer.parseInt(br.readLine());
        int[] Warr = new int[N];
        int[] Harr = new int[N];
        int[] Earr = new int[N];
        int[] dp = new int[N];
        String s = br.readLine();
        char[] arr = s.toCharArray();
        for (char c : arr){
            if (c == 'E') E++;
        }
        int index=0;
        while (index < N){
            Earr[index] = E;
            if (arr[index] == 'E') E--;
            index++;
        }
        for (int i=0; i<N; i++){
            if (arr[i] == 'W') {
                W++;
                H=0;
            }
            else if (arr[i] == 'H') {
                H++;
                if (i>0 && i<N-1) {
                    long p = pow(Earr[i+1])-(Earr[i+1])-1;
                    ans+=W*((p > 0)? p : 0)%C;
                }
            }
        }
        System.out.print(ans);
    }
    static long pow(int n){
        if (n == 0) return 1;
        else if (n == 1) return 2;
        long num = pow(n/2)%C;
        long d = num*num%C;
        if (n%2==0) return d%C;
        else return d*2%C;
    }
}
//Last try
import java.io.*;

class Scratch {
    static long ans=0;
    static long C = (long) 1e9+7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int W = 0;
        int E = 0;
        int N = Integer.parseInt(br.readLine());
        int[] Earr = new int[N];
        String s = br.readLine();
        char[] arr = s.toCharArray();
        for (char c : arr) if (c == 'E') E++;
        for (int i=0; i<N; i++){
            Earr[i] = E;
            if (arr[i] == 'E') E--;
        }
        for (int i=0; i<N-2; i++){
            if (arr[i] == 'W') W++;
            else if (arr[i] == 'H') {
                long p = (pow(Earr[i+1])-(Earr[i+1])-1)%C;
                ans = (ans + W*((p > 0)? p : 0)%C)%C;
            }
        }
        System.out.print(ans);
    }
    static long pow(int n){
        if (n == 0) return 1;
        else if (n == 1) return 2;
        long num = pow(n/2)%C;
        if (n%2==0) return num*num%C;
        else return num*num*2%C;
    }
}