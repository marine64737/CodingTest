//https://www.acmicpc.net/problem/1002
//Try
import java.io.*;
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

    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = fs.readInt();
        for (int i=0; i<T; i++){
            int x1 = fs.readInt();
            int y1 = fs.readInt();
            int r1 = fs.readInt();
            int x2 = fs.readInt();
            int y2 = fs.readInt();
            int r2 = fs.readInt();
            int x_gap = (x1-x2)*(x1-x2);
            int y_gap = (y1-y2)*(y1-y2);
            int r_gap1 = (r1+r2)*(r1+r2);
            int r_gap2 = (r1-r2)*(r1-r2);
            if (i > 0) sb.append("\n");
            if (x_gap+y_gap > 0) {
                if (x_gap + y_gap > r_gap1) sb.append(0);
                else if (x_gap + y_gap == r_gap1) sb.append(1);
                else if (x_gap + y_gap > r_gap2 && x_gap + y_gap < r_gap1) sb.append(2);
                else if (x_gap + y_gap == r_gap2) sb.append(1);
                else sb.append(0);
            }
            else {
                if (r1 == r2) sb.append(-1);
                else sb.append(0);
            }
        }
        System.out.print(sb);
    }
}