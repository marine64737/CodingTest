//https://www.acmicpc.net/problem/1722
//Try
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        long readLong() throws IOException {
            long c =0;
            int sign = 1;
            do { c = read(); } while (c <= 32);
            if (c == '-') { sign = -1; c = read(); }
            long val = 0;
            while (c > 32){
                val = val * 10 + (c-'0');
                c = read();
            }
            return val * sign;
        }
        int readInt() throws IOException{
            return (int) readLong();
        }
    }
    static int[] arr;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        N = fs.readInt();
        List<Integer> arr = new ArrayList<>();
        long ans=0;
        int select = fs.readInt();
        for (int i=0; i<N+1; i++) arr.add(i);
        if (select == 1){
            long n = fs.readLong();
            for (int i = N-1; i >= 0; i--){
                long f = fact(i);
                if (i == N-1) n--;
                else if (i < N-1) sb.append(" ");
                int index = (int) (n/f) + 1;
                sb.append(arr.get(index));
                arr.remove(index);
                n = n % f;
            }
            System.out.print(sb);
        }
        else if (select == 2){
            for (int i = N-1; i >= 0; i--){
                int index = fs.readInt();
                int idx = arr.indexOf(index)-1;
                if (idx > 0) {
                    long f = fact(i);
                    ans+= idx*f;
                }
                arr.remove(idx+1);
            }
            System.out.print(ans+1);
        }
    }
    static long fact(int n){
        if (n <= 1) return 1;
        return fact(n-1)*n;
    }
}