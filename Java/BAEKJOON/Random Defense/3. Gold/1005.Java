//https://www.acmicpc.net/problem/1005
//Try
import java.io.*;
import java.util.*;

class Scratch {
    static class FastScanner{
        InputStream in;
        byte[] buffer = new byte[1<<16];
        int ptr, len;
        FastScanner(InputStream in){
            this.in = in;
        }
        int read() throws IOException{
            if (ptr >= len){
                len = in.read(buffer);
                ptr=0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int readInt() throws IOException{
            int c=0, s=1;
            do { c= read(); } while (c <= 32);
            if (c=='-') {
                s = -1;
                c = read();
            }
            int val = 0;
            while (c > 32){
                val = val*10 + c - '0';
                c = read();
            }
            return val*s;
        }
    }
    static int[] time;
    static int[] dp;
    static int[] seq_to;
    static int[] seq_next;
    static int[] seq_head;
    static int[] indeg;
    static int index=0;
    static int W=0;
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = fs.readInt();
        for (int i=0; i<T; i++){
            index = 0;
            int N = fs.readInt();
            int K = fs.readInt();
            seq_to = new int[N+K+1];
            seq_next = new int[N+K+1];
            seq_head = new int[N+1];
            Arrays.fill(seq_head, -1);
            time = new int[N+1];
            dp = new int[N+1];
            indeg = new int[N+1];
            for (int j = 1; j <=N; j++) {
                time[j] = fs.readInt();
                dp[j] = time[j];
            }
            for (int j = 0; j < K; j++) {
                int start = fs.readInt();;
                int end = fs.readInt();;
                addLine(start, end);
                indeg[end]++;
            }
            Queue<Integer> q = new ArrayDeque<>();
            for (int j = 1; j <=N; j++) if (indeg[j] == 0) q.add(j);
            W = fs.readInt();
            while (!q.isEmpty()){
                int top = q.poll();
                for (int next = seq_head[top]; next != -1; next = seq_next[next]){
                    int c = seq_to[next];
                    dp[c] = Math.max(dp[c], dp[top]+time[c]);
                    if (--indeg[c] == 0) q.add(c);
                }
            }
            if (i > 0) sb.append("\n");
            sb.append(dp[W]);
        }
        System.out.print(sb);
    }
    static void addLine(int a, int b){
        seq_to[index] = b;
        seq_next[index] = seq_head[a];
        seq_head[a] = index++;
    }
}