//https://www.acmicpc.net/problem/10974

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Scratch {
    static int N;
    static int[] pick;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        pick = new int[N];
        dfs(0);
        System.out.print(sb);
    }
    public static void dfs(int depth){
        if (depth == N){
            for (int i=0; i<N; i++) {
                sb.append(pick[i]).append(i+1==N ? '\n':' ');
            }
            return;
        }
        for (int i=0; i<N; i++){
            if (!visited[i]){
                pick[depth] = i+1;
                visited[i] = true;
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }
}