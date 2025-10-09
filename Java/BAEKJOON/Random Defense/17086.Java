//https://www.acmicpc.net/problem/17086
//First try(문제 잘못 이해)
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int dist = 0;
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                ArrayDeque<int[]> dq = new ArrayDeque<>();
                visited = new boolean[N][M];
                if (arr[i][j] == 1){
                    dq.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                    while(!dq.isEmpty()){
                        int[] top = dq.poll();
                        if (top[0]!=i && top[1]!=j && arr[top[0]][top[1]] == 1) {
                            dist = Math.max(dist, top[2]-1);
                            break;
                        }
                        for (int k = Math.max(top[0]-1, 0); k<=Math.min(top[0]+1,N-1); k++){
                            for (int l=Math.max(top[1]-1,0); l<=Math.min(top[1]+1,M-1); l++){
                                if (k==top[0] && l==top[1]) continue;
                                if (!visited[k][l]) {
                                    visited[k][l] = true;
                                    dq.add(new int[]{k, l, top[2]+1});
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.print(dist);
    }
}
//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0; i<N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) {
                    for (int k = 0; k <= N - 1; k++) {
                        for (int l = 0; l <= M - 1; l++) {
                            int d = Math.max(Math.abs(i - k), Math.abs(j - l));
                            dist[k][l] = Math.min(d, dist[k][l]);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, dist[i][j]);
            }
        }
        System.out.print(ans);
    }
}