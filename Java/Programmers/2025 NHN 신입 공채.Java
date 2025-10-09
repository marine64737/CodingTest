//건물 외벽 칠하기
class Main {
    int answer;
    public int solution(int[][] buildings) {
        answer=0;
        boolean[][] visited = new boolean[n][m];
        int n = buildings.length;;
        int m =  buildings[0].length;
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (buildings[i][j] == 0) visited[i][j] = true;
            }
        }
        for (int i=0; i<n; i++){
            int x = 1;
            int lo = 0;
            int hi = m-1;
            if (!visited[i][lo]){
                visited[i][lo] = true;
                answer++;
            }
            if (!visited[i][hi]){
                visited[i][hi] = true;
                answer++;
            }
            while (lo+x <= m-1){
                if (buildings[i][lo+x] > buildings[i][lo]){
                    if (!visited[i][lo+x]){
                        visited[i][lo+x] = true;
                        lo += x;
                        x=0;
                        answer++;
                    }
                }
                x++;
            }
            x=1;
            while (hi-x >= 0){
                if (buildings[i][hi-x] > buildings[i][hi]){
                    if (!visited[i][hi-x]){
                        visited[i][hi-x] = true;
                        hi -= x;
                        x=0;
                        answer++;
                    }
                }
                x++;
            }
        }
        for (int i=0; i<m; i++){
            int x = 1;
            int lo = 0;
            int hi = n-1;
            if (!visited[lo][i]){
                visited[lo][i] = true;
                answer++;
            }
            if (!visited[hi][i]){
                visited[hi][i] = true;
                answer++;
            }
            while (lo+x <= n-1){
                if (buildings[lo+x][i] > buildings[lo][i]){
                    if (!visited[lo+x][i]){
                        visited[lo+x][i] = true;
                        lo += x;
                        x=0;
                        answer++;
                    }
                }
                x++;
            }
            x=1;
            while (hi-x >= 0){
                if (buildings[hi-x][i] > buildings[hi][i]){
                    if (!visited[hi-x][i]){
                        visited[hi-x][i] = true;
                        hi -= x;
                        x=0;
                        answer++;
                    }
                }
                x++;
            }
        }
        return answer;
    }
}

//불, 얼음으로 마을 온도 측정
class Main {
    int[][] answer;
    public int solution(int m, int n, int[][] fires, int[][] ices) {
        answer = new int[m][m];
        dfs(0, m, n, fires, ices);

        return answer;
    }
    public void dfs(int depth, int m, int n, int[][] fires, int[][] ices){
        if (depth == n) return;
        for (int i=0; i<depth; i++){
            for (int j=0; j< fires.length; j++){
                for (int x=fires[j][0]-i; x<=fires[j][0]+i; x++){
                    for (int y=fires[j][1]-i; y<=fires[j][1]+i; y++){
                        if (x >= 0 && x < m && y >= 0 && y < m) answer[x][y]++;
                    }
                }
            }
            for (int j=0; j< ices.length; j++){
                for (int x=ices[j][0]-i; x<=ices[j][0]+i; x++){
                    for (int y=ices[j][1]-i; y<=ices[j][1]+i; y++){
                        if (x >= 0 && x < m && y >= 0 && y < m) answer[x][y]--;
                    }
                }
            }
        }
        dfs(depth+1, m, n, fires, ices);
    }
}

//반경 중복 없는 버전
class Main {
    int[][] answer;

    // m x m, depth == n 상태를 원한다면 steps = n
    public int[][] solution(int m, int n, int[][] fires, int[][] ices) {
        answer = new int[m][m];
        dfs(0, n, m, fires, ices);
        return answer;
    }

    private void dfs(int depth, int steps, int m, int[][] fires, int[][] ices){
        if (depth == steps) return;

        int r = depth; // 이번 단계에서 새로 칠할 반경만 처리

        // 🔥 fire: 정사각(체비셰프)
        for (int j = 0; j < fires.length; j++) {
            int sr = fires[j][0], sc = fires[j][1];
            for (int x = sr - r; x <= sr + r; x++) {
                for (int y = sc - r; y <= sc + r; y++) {
                    if (x >= 0 && x < m && y >= 0 && y < m
                            && Math.max(Math.abs(x - sr), Math.abs(y - sc)) <= r) {
                        answer[x][y]++;  // 불은 +1
                    }
                }
            }
        }

        // ❄️ ice: 마름모(맨해튼)로 하고 싶다면 아래 조건 추가
        for (int j = 0; j < ices.length; j++) {
            int sr = ices[j][0], sc = ices[j][1];
            for (int x = sr - r; x <= sr + r; x++) {
                for (int y = sc - r; y <= sc + r; y++) {
                    if (x >= 0 && x < m && y >= 0 && y < m
                            && Math.abs(x - sr) + Math.abs(y - sc) <= r) {
                        answer[x][y]--;  // 얼음은 -1
                    }
                }
            }
        }

        dfs(depth + 1, steps, m, fires, ices);
    }
}

//반경 중복 합산
class Main {
    // m x m 그리드, steps가 깊이(n). fires/ices는 {row, col}.
    public int[][] solution(int m, int steps, int[][] fires, int[][] ices) {
        int[][] ans = new int[m][m];

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < m; y++) {

                // 🔥 fire: Chebyshev (정사각)
                int add = 0;
                for (int[] f : fires) {
                    int dist = Math.max(Math.abs(x - f[0]), Math.abs(y - f[1]));
                    add += Math.max(0, steps - dist);
                }

                // ❄️ ice: Manhattan (마름모)
                int sub = 0;
                for (int[] ic : ices) {
                    int dist = Math.abs(x - ic[0]) + Math.abs(y - ic[1]);
                    sub += Math.max(0, steps - dist);
                }

                ans[x][y] = add - sub;
            }
        }
        return ans;
    }
}
//Last try
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