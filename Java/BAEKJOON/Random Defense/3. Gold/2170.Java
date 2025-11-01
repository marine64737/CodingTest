//https://www.acmicpc.net/problem/2170
//Try
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

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
        int ans = 0;
        int N = fs.readInt();
        int[][] lines = new int[N][2];
        for (int i=0; i<N; i++){
            lines[i][0] = fs.readInt();
            lines[i][1] = fs.readInt();
        }
        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));
        int min_l = lines[0][0];
        int max_r = lines[0][1];
        for (int i=1; i<N; i++){
            int l = lines[i][0];
            int r = lines[i][1];
            if (l > max_r){
                ans += max_r - min_l;
                min_l = l;
                max_r = r;
            }
            else if (r > max_r) max_r = r;
        }
        ans += max_r-min_l;
        System.out.print(ans);
    }
}