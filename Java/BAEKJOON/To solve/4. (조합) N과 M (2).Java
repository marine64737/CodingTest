//https://www.acmicpc.net/problem/15650
//First try
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Scratch {
    static int N, M;
    static long cnt;
    static int[] pick;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pick = new int[M];
        dfs(0, 0);
        System.out.print(sb);
        return;
    }
    public static void dfs(int depth, int index){
        if (depth == M) {
            for (int i=0; i<M; i++){
                sb.append(pick[i]).append(i == M-1 ? '\n': ' ');
            }
            return;
        }
        for (int i = index; i<N; i++){
            pick[depth] = i+1;
            dfs(depth+1, i+1);
        }
    }
}

//Second try
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Scratch {
    static int N, M;
    static long cnt;
    static int[] pick;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pick = new int[M];
        dfs(0);
        System.out.print(sb);
        return;
    }
    public static void dfs(int depth){
        if (depth == M) {
            for (int i=0; i<M; i++){
                sb.append(pick[i]).append(i == M-1 ? '\n': ' ');
            }
            return;
        }
        int index = depth == 0 ? 0 : pick[depth-1];
        for (int i = index; i<N; i++){
            pick[depth] = i+1;
            dfs(depth+1);
        }
    }
}