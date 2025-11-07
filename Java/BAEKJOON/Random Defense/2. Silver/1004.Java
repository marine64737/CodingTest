//https://www.acmicpc.net/problem/1004
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
            int ans=0;
            int x1 = fs.readInt();
            int y1 = fs.readInt();
            int x2 = fs.readInt();
            int y2 = fs.readInt();
            int n = fs.readInt();
            for (int j=0; j<n; j++) {
                int cx = fs.readInt();
                int cy = fs.readInt();
                int cr = fs.readInt();
                if ((x1-cx)*(x1-cx) + (y1-cy)*(y1-cy) < cr * cr &&
                        (x2-cx)*(x2-cx) + (y2-cy)*(y2-cy) > cr * cr) ans++;
                else if ((x1-cx)*(x1-cx) + (y1-cy)*(y1-cy) > cr * cr &&
                        (x2-cx)*(x2-cx) + (y2-cy)*(y2-cy) < cr * cr) ans++;
            }
            if (i>0) sb.append("\n");
            sb.append(ans);
        }
        System.out.print(sb);
    }
}