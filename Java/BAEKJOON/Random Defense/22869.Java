//https://www.acmicpc.net/problem/22869
//First try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<N+1; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[][][] route = new int[N+1][N+1][2];
        boolean[][] visited = new boolean[N+1][N+1];
        for (int i=1; i<N; i++) {
            for (int j=i+1; j<N+1; j++){
                route[i][j][0] = j;
                route[i][j][1] = (j-i)*(1+Math.abs(arr[i]-arr[j]));
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1]==b[1]) ? Integer.compare(b[0], a[0])
                : Integer.compare(a[1], b[1]));
        for (int i=2; i<N+1; i++) {
            if (route[1][i][1] <= K){
                visited[1][i] = true;
                pq.add(route[1][i]);
            }
        }
        while (!pq.isEmpty()){
            int[] top = pq.poll();
            if (top[0] == N){
                System.out.print("YES");
                return;
            }
            for (int[] next : route[top[0]]) {
                if (!visited[top[0]][next[0]] && next[0] > top[0] && next[1] <= K) {
                    visited[top[0]][next[0]] = true;
                    pq.add(next);
                }
            }
        }
        System.out.print("NO");
    }
}
//Second  try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<N+1; i++) arr[i] = Integer.parseInt(st.nextToken());
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i=2; i<N+1; i++) {
            int dist = (i-1) * (1+Math.abs(arr[i]-arr[1]));
            if (dist <= K) {
                visited[i] = true;
                dq.add(i);
            }
        }
        while (!dq.isEmpty()){
            int top = dq.poll();
            if (top == N){
                System.out.print("YES");
                return;
            }
            for (int i=top+1; i<=N; i++) {
                int dist = (i-top) * (1+Math.abs(arr[i]-arr[top]));
                if (!visited[i] && dist <= K) {
                    visited[i] = true;
                    dq.add(i);
                }
            }
        }
        System.out.print("NO");
    }
}