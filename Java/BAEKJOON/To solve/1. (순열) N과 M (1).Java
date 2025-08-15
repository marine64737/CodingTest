//https://www.acmicpc.net/problem/15649
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = array[0];
        int M = array[1];
        int[] pick = new int[M];
        boolean[] visited = new boolean[N];
        for (int i=0; i<N; i++){
            dfs(N, M, i+1, 0, pick, visited);
        }
        return;
    }
    public static void dfs(int N, int M, int number, int depth, int[] pick, boolean[] visited){
        pick[depth] = number;
        visited[number-1] = true;
        if (depth + 1 == pick.length){
            for (int i: pick){
                System.out.print(i+" ");
            }
            System.out.println();
        }
        else {
            for (int i = 0; i<N; i++){
                if (i+1 == number) continue;
                if (!visited[i]) dfs(N, M, i+1, depth+1, pick, visited);
            }
        }
        visited[number-1] = false;
    }
}
//AI Recommend
import java.io.*; import java.util.*;

public class Main {
    static int N, M;
    static boolean[] used;    // 크기 N+1, 숫자 x 그대로 인덱스
    static int[] out;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int d) {
        if (d == M) {
            for (int i = 0; i < M; i++) sb.append(out[i]).append(i + 1 == M ? '\n' : ' ');
            return;
        }
        for (int x = 1; x <= N; x++) {
            if (used[x]) continue;
            used[x] = true; out[d] = x;
            dfs(d + 1);
            used[x] = false; // 백트래킹
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        used = new boolean[N + 1];
        out  = new int[M];
        dfs(0);
        System.out.print(sb);
    }
}
//Second try
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Scratch {
    static int N, M;
    static long cnt;
    static int[] pick;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pick = new int[M];
        visited = new boolean[N];
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
        for (int i = 0; i<N; i++){
            if (!visited[i]) {
                visited[i] = true;
                pick[depth] = i+1;
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }
}