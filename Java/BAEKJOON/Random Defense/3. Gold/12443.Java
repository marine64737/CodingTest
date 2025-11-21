//https://www.acmicpc.net/problem/12443
import java.io.*;
import java.util.*;

class Main {
    static class FastScanner{
        InputStream in = System.in;
        byte[] buffer = new byte[1<<16];
        int ptr, len;
        int read() throws IOException{
            if (ptr >= len){
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int readInt() throws IOException{
            int c, s=1;
            do { c= read(); if (c == -1) return -1;} while (c <= 32);
            if (c=='-') {s=-1; c=read();}
            int val = 0;
            while (c>32){
                val = val*10 + (c-'0');
                c=read();
            }
            return val*s;
        }
    }
    static int K;
    static double sum;
    static int[] arr;
    static int[] seq;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();
        int T = fs.readInt();
        for (int t=0; t<T; t++){
            K = fs.readInt();
            arr = new int[K];
            seq = new int[K];
            visited = new boolean[K];
            sum = 0;
            for (int k=0; k<K; k++){
                arr[k] = fs.readInt();
            }
            dfs(0);
            if (t > 0) sb.append("\n");
            sb.append("Case #").append(t + 1).append(": ").append(sum);
        }
        System.out.print(sb);
    }
    static void dfs(int t){
        if (t == K) {
            double tmp = 0;
            for (int k=0; k<K; k++){
                if (k < K-1) tmp+=seq[k]*seq[k+1]*Math.sin(2*Math.PI/K)/2;
                else tmp+=seq[k]*seq[0]*Math.sin(2*Math.PI/K)/2;
            }
            sum = Math.max(sum, tmp);
            return;
        }
        for (int i=0; i<K; i++){
            if (!visited[i]) {
                visited[i] = true;
                seq[i] = arr[t];
                dfs(t+1);
                visited[i] = false;
            }
        }
    }
}