//https://www.acmicpc.net/problem/1012
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
    static int[][] arr;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();
        int T = fs.readInt();
        for (int t=0; t<T; t++){
            int M = fs.readInt();
            int N = fs.readInt();
            int K = fs.readInt();
            arr = new int[N][M];
            int cnt = 0;
            visited = new boolean[N][M];
            for (int i=0; i<K; i++){
                int y = fs.readInt();
                int x = fs.readInt();
                arr[x][y] = 1;
            }
            for (int i=0; i<N; i++){
                for (int j=0; j<M; j++){
                    if (!visited[i][j] && arr[i][j] == 1) {
                        cnt++;
                        bfs(i, j);
                    }
                }
            }
            if (t > 0) sb.append("\n");
            sb.append(cnt);
        }
        System.out.print(sb);
    }
    static void bfs(int i, int j){
        visited[i][j] = true;
        for (int a=-1; a<=1; a++){
            for (int b=-1; b<=1; b++){
                if (Math.abs(a)+Math.abs(b) == 2 || Math.abs(a)+Math.abs(b) == 0) continue;
                if (i+a < 0 || i+a >= arr.length || j+b < 0 || j+b >= arr[0].length) continue;
                if (!visited[i+a][j+b] && arr[i+a][j+b]==1) bfs(i+a, j+b);
            }
        }
    }
}