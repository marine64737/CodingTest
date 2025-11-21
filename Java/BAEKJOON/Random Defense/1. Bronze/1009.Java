//https://www.acmicpc.net/problem/1009
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
        int T = fs.readInt();
        for (int t=0; t<T; t++){
            int a = fs.readInt();
            int b = fs.readInt();
            int ans = multiply(a, b);
            if (t > 0) sb.append("\n");
            sb.append((ans == 0)? 10 : ans);
        }
        System.out.print(sb);
    }
    static int multiply(int a, int b){
        if (b == 1) return a%10;
        int half = multiply(a, b/2)%10;
        if (b%2==0) return (half*half)%10;
        else return (half*half*a)%10;
    }
}