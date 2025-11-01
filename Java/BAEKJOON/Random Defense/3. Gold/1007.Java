//https://www.acmicpc.net/problem/1007
//First try
import java.io.*;
import java.util.StringTokenizer;

class Scratch {
    static int[][] dot;
    static boolean[] visited;
    static int N;
    static double answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t <T; t++){
            N = Integer.parseInt(br.readLine());
            dot = new int[N][2];
            visited = new boolean[N];
            answer = Double.MAX_VALUE;
            for (int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                dot[i][0] = Integer.parseInt(st.nextToken());
                dot[i][1] = Integer.parseInt(st.nextToken());
            }
            dfs(-1, 0, 0, 0, 0);
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
    static void dfs(int idx, int s, int e, long x, long y){
        if (s == N/2 && e == N/2) {
            double len = Math.sqrt(y * y + x * x);
            answer = Math.min(answer, len);
            return;
        }
        if (e < N/2) dfs(idx+1, s, e+1, x+dot[idx+1][0], y+dot[idx+1][1]);
        if (s < N/2) dfs(idx+1, s+1, e, x-dot[idx+1][0], y-dot[idx+1][1]);
    }
}
