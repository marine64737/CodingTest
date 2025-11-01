//https://www.acmicpc.net/problem/14727
import java.io.*;

class Scratch {
    static class FastScanner{
        InputStream in;
        byte[] buffer = new byte[1<<16];
        int ptr=0, len=0;
        FastScanner(InputStream in){
            this.in = in;
        }
        int read() throws IOException{
            if (ptr >= len){
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int readInt() throws IOException {
            int c =0;
            int sign = 1;
            do { c = read(); } while (c <= 32);
            if (c == '-') { sign = -1; c = read(); }
            int val = 0;
            while (c > 32){
                val = val * 10 + (c-'0');
                c = read();
            }
            return val * sign;
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.readInt();
        int h = fs.readInt();
        int min_h = h;
        int highest = h;
        int w=1;
        int rect_L = w* min_h;
        for (int i=1; i<N; i++){
            h = fs.readInt();
            highest = Math.max(h, highest);
            w++;
            if (rect_L < h){
                highest = h;
            }
            else if (rect_L < w*Math.min(min_h, h)){
                min = Math.min(min_h, h);
                rect = w* min;
            }
        }
        System.out.print(N* h);
    }
}
//AI Recommend
import java.io.*;
        import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

class Scratch {
    static class FastScanner{
        InputStream in;
        byte[] buffer = new byte[1<<16];
        int ptr=0, len=0;
        FastScanner(InputStream in){
            this.in = in;
        }
        int read() throws IOException{
            if (ptr >= len){
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int readInt() throws IOException {
            int c =0;
            int sign = 1;
            do { c = read(); } while (c <= 32);
            if (c == '-') { sign = -1; c = read(); }
            int val = 0;
            while (c > 32){
                val = val * 10 + (c-'0');
                c = read();
            }
            return val * sign;
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.readInt();
        int[] arr = new int[N];
        Deque<Integer> dq = new ArrayDeque<>();
        long rect=0;
        for (int i=0; i<N; i++) arr[i] = fs.readInt();
        for (int i=0; i<=N; i++){
            int cur = (i==N)? 0 : arr[i];
            while (!dq.isEmpty() && arr[dq.peek()] >= cur){
                int top = dq.pop();
                int left = (dq.isEmpty())? 0 : dq.peek()+1;
                int right = i-1;
                int w = right - left + 1;
                long h = arr[top];
                rect = Math.max(rect, w * h);
            }
            dq.push(i);
        }
        System.out.print(rect);
    }
}