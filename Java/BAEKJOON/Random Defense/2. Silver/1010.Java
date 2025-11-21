//https://www.acmicpc.net/problem/1010
//Answer
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
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();
        long[][] dp = new long[31][31];
        for (int i=0; i<=30; i++){
            for (int j=i; j<=30; j++){
                if (i == 0 || i == j) dp[i][j]=1;
                else if (i == 1 || j - i == 1) dp[i][j]=j;
                else dp[i][j] = dp[i][j-1]+dp[i-1][j-1];
            }
        }
        int T = fs.readInt();
        for (int t=0; t<T; t++){
            int a = fs.readInt();
            int b = fs.readInt();
            if (t > 0) sb.append("\n");
            sb.append(dp[a][b]);
        }
        System.out.print(sb);
    }
}