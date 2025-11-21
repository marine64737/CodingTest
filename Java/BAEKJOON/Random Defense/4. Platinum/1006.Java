//https://www.acmicpc.net/problem/1006
//Trying
import java.io.*;
import java.util.*;

class Scratch {
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
            do { c= read(); } while (c <= 32);
            if (c=='-') {s=-1; c=read();}
            int val = 0;
            while (c>=32){
                val = val*10 + c-'0';
                c=read();
            }
            return val*s;
        }
    }

    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        int T = fs.readInt();
        for (int t=0; t<T; t++){
            int N = fs.readInt();
            int W = fs.readInt();
            int[][] enemy = new int[2][N+1];
            int[][] dp = new int[N+1][4];
            for (int i=0; i<N+1; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[0][0]=0;
            for (int i=0; i<2; i++){
                for (int j=1; j<N+1; j++){
                    enemy[i][j] = fs.readInt();;
                }
            }
            for (int i=1; i<N+1; i++){
                if (i==1){
                    if (enemy[i][0]+enemy[i][1] <= W) dp[i][0] = 1;
                    else dp[i][0]=2;
                }
                else{
                    if (enemy[0][i]+enemy[1][i]<=W) dp[i][0] = Math.min(dp[i][0], (Math.min(dp[i - 1][0] + 1, Math.min(dp[i - 1][1] + 1, dp[i-1][2]+1))));
                    else dp[i][0] = 2;
                    if (enemy[i-1][0]+enemy[i][0]<=W) dp[i][0] = Math.min(dp[i][0], Math.min(dp[i-1][0]+1, Math.min(dp[i-1][1]+1, dp[i-1][2])));
                    else dp[i][0] = 2;
                    if (enemy[i-1][1]+enemy[i][1]<=W) dp[i][0] = Math.min(dp[i][0], dp[i-1][0]+1);
                    else dp[i][0] = 2;
                }
            }
            int ans = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
            System.out.print(ans);
        }
    }
}
//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return -1;
            } while (c <= ' ');
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    static final int INF = 1_000_000_000;

    static int N, W;
    static int[][] enemy;   // enemy[0][i], enemy[1][i]
    static int[] a, b, c;   // DP states

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            N = fs.nextInt();
            W = fs.nextInt();

            enemy = new int[2][N + 1];
            a = new int[N + 1];
            b = new int[N + 1];
            c = new int[N + 1];

            for (int row = 0; row < 2; row++) {
                for (int i = 1; i <= N; i++) {
                    enemy[row][i] = fs.nextInt();
                }
            }

            // N == 1 경우는 따로 처리 (원형 의미 거의 없음)
            if (N == 1) {
                if (enemy[0][1] + enemy[1][1] <= W) out.append(1).append('\n');
                else out.append(2).append('\n');
                continue;
            }

            int answer = INF;

            // 0) 원형 고려 안 함 (그냥 1..N 직선)
            initDP(
                    (enemy[0][1] + enemy[1][1] <= W) ? 1 : 2, // a[1]
                    1,                                       // b[1]
                    1                                        // c[1]
            );
            runDP();
            answer = a[N];

            // 1) 위 줄 1열과 N열을 하나로 묶는 경우
            if (enemy[0][1] + enemy[0][N] <= W) {
                initDP(1, 0, 1);
                runDP();
                // 위 줄은 1~N 묶는 가로 하나가 이미 있다고 보고, 나머지 최소값은 c[N]
                answer = Math.min(answer, c[N] + 1);
            }

            // 2) 아래 줄 1열과 N열을 하나로 묶는 경우
            if (enemy[1][1] + enemy[1][N] <= W) {
                initDP(1, 1, 0);
                runDP();
                // 아래 줄은 1~N 묶는 가로 하나가 이미 있다고 보고, 나머지 최소값은 b[N]
                answer = Math.min(answer, b[N] + 1);
            }

            // 3) 위/아래 둘 다 1열과 N열을 각각 묶는 경우
            if (enemy[0][1] + enemy[0][N] <= W && enemy[1][1] + enemy[1][N] <= W) {
                initDP(0, 0, 0);   // 1열은 이미 양쪽에서 가로로 묶였다고 가정
                runDP();
                // 1열은 이미 양쪽에서 처리됐으니 2..N-1까지만 dp, +2는 양쪽 가로 두 개
                answer = Math.min(answer, a[N - 1] + 2);
            }

            out.append(answer).append('\n');
        }

        System.out.print(out);
    }

    // a[1], b[1], c[1] 초기 세팅
    static void initDP(int a1, int b1, int c1) {
        // a[0], b[0], c[0]는 기본값 0 유지 (0열까지 커버 = 0개)
        Arrays.fill(a, 1, N + 1, INF);
        Arrays.fill(b, 1, N + 1, INF);
        Arrays.fill(c, 1, N + 1, INF);

        a[1] = a1;
        b[1] = b1;
        c[1] = c1;
    }

    // 선형(원형 X)에서 1..N까지 dp
    static void runDP() {
        for (int i = 2; i <= N; i++) {
            // 위쪽 i열이 "열려" 있는 상태(b[i])
            b[i] = a[i - 1] + 1;  // 위 i열에 새 소대 1개 배치
            if (enemy[0][i - 1] + enemy[0][i] <= W) {
                // 위 (i-1, i)를 가로로 이어서 하나의 소대로 덮는 경우
                b[i] = Math.min(b[i], c[i - 1] + 1);
            }

            // 아래쪽 i열이 "열려" 있는 상태(c[i])
            c[i] = a[i - 1] + 1;
            if (enemy[1][i - 1] + enemy[1][i] <= W) {
                // 아래 (i-1, i)를 가로로 이어서 하나의 소대로 덮는 경우
                c[i] = Math.min(c[i], b[i - 1] + 1);
            }

            // i열까지 "완전히 닫힌" 상태 a[i]
            a[i] = Math.min(b[i] + 1, c[i] + 1); // 위만 열어둔 상태 + 아래 하나, or 아래만 열어둔 상태 + 위 하나

            // i열 위/아래를 세로 하나로 묶는 경우
            if (enemy[0][i] + enemy[1][i] <= W) {
                a[i] = Math.min(a[i], a[i - 1] + 1);
            }

            // 위/아래 모두 (i-1, i)를 가로로 묶어서 2열짜리 블록 두 개 만드는 경우
            if (enemy[0][i - 1] + enemy[0][i] <= W &&
                    enemy[1][i - 1] + enemy[1][i] <= W) {
                a[i] = Math.min(a[i], a[i - 2] + 2);
            }
        }
    }
}
