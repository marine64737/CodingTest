//https://www.acmicpc.net/problem/31531
//First try
import java.io.*; import java.util.*;
class Scratch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int score = 0;
        int N = Integer.parseInt(br.readLine());
        Double[][] balls = new Double[N][2];
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            balls[i][0] = Double.parseDouble(st.nextToken());
            balls[i][1] = Double.parseDouble(st.nextToken());
        }
        Arrays.sort(balls, Comparator.comparingDouble(a -> a[0]));
        int start=0;
        int end = N-1;
        while (start <= end){
            boolean pass = true;
            while (balls[start][1] == -1) start++;
            while (balls[end][1] == 1) end--;
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            for (int i=start; i<=end-1; i++) {
                dq.push(i);
                if (balls[i][1] > balls[i + 1][1]) {
                    pass = false;
                    break;
                }
            }
            if (pass) {
                System.out.print(score);
                return;
            }
            while (!dq.isEmpty()){
                int i = dq.pop();
                if (balls[i][1] > balls[i+1][1]){
                    if (i <= end-2 && balls[i+1][1] > balls[i+2][1] && balls[i+2][0] - balls[i+1][0] == balls[i+1][0] - balls[i][0]){
                        score+=5;
                        double temp = balls[i+2][1];
                        balls[i+2][1] = balls[i][1];
                        balls[i][1] = temp;
                        balls[i][0] = balls[i+1][0];
                        balls[i+2][0] = balls[i+1][0];
                    }
                    else {
                        if (balls[i][1] == 1 && balls[i+1][1] == -1) score++;
                        else if ((balls[i][1] == 1 && balls[i+1][1] == 0) || (balls[i][1] == 0 && balls[i+1][1] == -1)) score+=2;
                        double temp = balls[i+1][1];
                        balls[i+1][1] = balls[i][1];
                        balls[i][1] = temp;
                        balls[i][0] = (balls[i][0]+balls[i+1][0])/2;
                        balls[i+1][0] = balls[i][0];
                    }
                }
            }
        }
        System.out.print(score);
    }
}
//Second try
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int score = 0;
        Double[][] balls = new Double[N][2];
        int[] ver = new int[N];
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            balls[i][0] = Double.parseDouble(st.nextToken());
            balls[i][1] = Double.parseDouble(st.nextToken());
        }
        Arrays.sort(balls, Comparator.comparingDouble(a -> a[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            double a_t = Math.abs((balls[a[0]][0]-balls[a[0]+1][0])/(balls[a[0]][1]-balls[a[0]+1][1]));
            double b_t = Math.abs((balls[b[0]][0]-balls[b[0]+1][0])/(balls[b[0]][1]-balls[b[0]+1][1]));
            if (a_t - b_t >= 0.0000000001) return Double.compare(a_t, b_t);
            else if (balls[a[0]][0] != balls[b[0]][0]) return Double.compare(balls[a[0]][0], balls[b[0]][0]);
            else return Double.compare(balls[a[0]][1], balls[b[0]][1]);
        });
        for (int i=0; i<N-1; i++) if (balls[i][1] > balls[i+1][1]) pq.add(new int[]{i, 0});
        while (!pq.isEmpty()){
            int[] top = pq.poll();
            int[] top2 = pq.peek();
            if (top2 != null && top[0] < N-2 && top[1] == ver[top[0]] && top[0]+1 == top2[0] && top2[1] == ver[top2[0]]
                    && (balls[top[0]+1][0]-balls[top[0]][0]==balls[top[0]+2][0]-balls[top[0]+1][0])
                    && balls[top[0]][1] == 1  && balls[top[0]+1][1] == 0 && balls[top[0]+2][1] == -1
            ){
                top2 = pq.poll();
                score+=5;
                double tmp = balls[top[0]][1];
                balls[top[0]][1] = balls[top2[0]+1][1];
                balls[top[0]+2][1] = tmp;
                balls[top[0]][0]=balls[top[0]+1][0];
                balls[top[0]+2][0]=balls[top[0]+1][0];
                for (int i=-1; i<3; i++){
                    if (top[0]+i >=0 && top[0]+i<N-1 && balls[i][1] > balls[i+1][1]){
                        ver[top[0]+i]++;
                        pq.add(new int[]{top[0]+i, ver[top[0]+i]});
                    }
                }
            }
            else{
                if (top[1] == ver[top[0]]){
                    if ((balls[top[0]][1]==1 && balls[top[0]+1][1]==0) || (balls[top[0]][1]==0 && balls[top[0]+1][1]==-1))
                        score+=2;
                    else if (balls[top[0]][1]==1 && balls[top[0]+1][1]==-1) score++;
                    double tmp = balls[top[0]][1];
                    balls[top[0]][1] = balls[top[0]+1][1];
                    balls[top[0]+1][1] = tmp;
                    tmp = balls[top[0]][0]+balls[top[0]+1][0];
                    balls[top[0]][0]=tmp/2;
                    balls[top[0]+1][0]=tmp/2;
                    for (int i=-1; i<2; i++){
                        if (top[0]+i >=0 && top[0]+i<N-1 && balls[top[0]+i][1] > balls[top[0]+i+1][1]){
                            ver[top[0]+i]++;
                            pq.add(new int[]{top[0]+i, ver[top[0]+i]});
                        }
                    }
                }
            }
        }
        System.out.print(score);
    }
}
//Third try
import java.io.*;
        import java.util.*;

class Scratch {
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            this.in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }

        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            do {
                c = read();
            } while (c <= 32);
            if (c == '-') {
                s = -1;
                c = read();
            }
            while (c > 32) {
                x = x * 10 + (c - '0');
                c = read();
            }
            return x * s;
        }

        long nextLong() throws IOException {
            long c, s = 1, x = 0;
            do {
                c = read();
            } while (c <= 32);
            if (c == '-') {
                s = -1;
                c = read();
            }
            while (c > 32) {
                x = x * 10 + (c - '0');
                c = read();
            }
            return x * s;
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner fs= new FastScanner(System.in);
        int N = fs.nextInt();
        int score = 0;
        Long[] left = new Long[N];
        Long[] mid = new Long[N];
        Long[] right = new Long[N];
        int idx_left=0;
        int idx_mid=0;
        int idx_right=0;
        for (int i=0; i<N; i++){
            long pos = fs.nextLong();
            int dir = fs.nextInt();;
            if (dir == 1) right[idx_right++] = pos;
            else if (dir == -1) left[idx_left++] = pos;
            else  mid[idx_mid++] = pos;
        }
        Arrays.sort(left, 0, idx_left);
        Arrays.sort(mid, 0, idx_mid);
        if (idx_mid > 0){
            for (int i=0; i<idx_right; i++){
                score += 2*(idx_mid-bisect(mid, idx_mid, right[i]));
            }
            for (int i=0; i<idx_left; i++){
                score += 2*bisect(mid, idx_mid, left[i]);
            }
        }
        if (idx_left > 0){
            for (int i=0; i<idx_right; i++){
                score += (idx_left-bisect(left, idx_left, right[i]));
            }
        }
        System.out.print(score);
    }
    static int bisect(Long[] arr, int arr_len, long num){
        if (num < arr[0]) return 0;
        if (num > arr[arr_len-1]) return arr_len;
        int start = 0;
        int end = arr_len-1;
        int pos=0;
        while (start <= end){
            int mid = start+(end-start)/2;
            if (num >= arr[mid]){
                pos = mid;
                end = mid-1;
            }
            else {
                start = mid + 1;
            }
        }
        return pos;
    }
}
//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {

    // ---- byte 기반 빠른 입력 ----
    static final class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        long nextLong() throws IOException {
            int c;
            do { c = read(); } while (c <= 32);
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            long val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
        int nextInt() throws IOException { return (int) nextLong(); }
    }

    // pos와 dir을 같이 정렬 (퀵소트)
    static void sortByPos(long[] pos, int[] dir) {
        quick(pos, dir, 0, pos.length - 1);
    }
    static void quick(long[] a, int[] b, int l, int r) {
        while (l < r) {
            int i = l, j = r;
            long p = a[(l + r) >>> 1];
            while (i <= j) {
                while (a[i] < p) i++;
                while (a[j] > p) j--;
                if (i <= j) {
                    long tmpA = a[i]; a[i] = a[j]; a[j] = tmpA;
                    int  tmpB = b[i]; b[i] = b[j]; b[j] = tmpB;
                    i++; j--;
                }
            }
            // 꼬리재귀 제거
            if (j - l < r - i) { if (l < j) quick(a, b, l, j); l = i; }
            else { if (i < r) quick(a, b, i, r); r = j; }
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int N = fs.nextInt();

        long[] pos = new long[N];
        int[] dir = new int[N]; // -1, 0, +1

        for (int i = 0; i < N; i++) {
            pos[i] = fs.nextLong();
            dir[i] = fs.nextInt();   // -1, 0, 1
        }

        // 위치 기준 정렬 (모든 위치는 서로 다름)
        sortByPos(pos, dir);

        long ans = 0;

        // (→,←): 왼쪽에서 본 오른쪽 이동 개수 누적 후, 왼쪽→오른쪽 스캔 중 ←에서 더함
        long rightSeen = 0;
        for (int i = 0; i < N; i++) {
            int d = dir[i];
            if (d == 1) rightSeen++;
            else if (d == -1) ans += rightSeen;     // RL 쌍: +1씩
        }

        // (→,0): 정지 공에서 왼쪽에 있는 → 개수만큼 +2
        long r0 = 0;
        rightSeen = 0;
        for (int i = 0; i < N; i++) {
            int d = dir[i];
            if (d == 1) rightSeen++;
            else if (d == 0) r0 += rightSeen;       // (→,0) 개수
        }

        // (0,←): 정지 공에서 오른쪽에 있는 ← 개수만큼 +2
        long zeroL = 0, leftSeen = 0;
        for (int i = N - 1; i >= 0; i--) {
            int d = dir[i];
            if (d == -1) leftSeen++;
            else if (d == 0) zeroL += leftSeen;     // (0,←) 개수
        }

        ans += 2L * (r0 + zeroL);

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('\n');
        System.out.print(sb);
    }
}
//By my own
import java.io.*;
        import java.util.*;

class Scratch {
    public static class FastScanner{
        public InputStream in;
        public byte[] buffer = new byte[1<<16];
        private int ptr = 0, len = 0;
        private FastScanner (InputStream in){
            this.in = in;
        }
        int read() throws IOException{
            if (ptr >= len){
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        long readLong() throws IOException{
            int c=read();
            int sign = 1;
            while (c <= 32) c = read();
            if (c=='-') { sign = -1; c = read();}
            long val = 0;
            while (c > 32){
                val = val * 10 + (c-'0');
                c = read();
            }
            return val * sign;
        }
        int readInt() throws IOException { return (int) readLong();}
    }
    static void quick(long[] pos, int[] dir, int l, int r){
        while (l < r){
            int i = l;
            int j = r;
            long mid = pos[(i+j) >>> 1];
            while (i <= j){
                while (pos[i] < mid) i++;
                while (pos[j] > mid) j--;
                if (i <= j) {
                    long tmpP = pos[i]; pos[i] = pos[j]; pos[j] = tmpP;
                    int tmpD = dir[i]; dir[i] = dir[j]; dir[j] = tmpD;
                    i++; j--;
                }
            }
            if (j - l < r - i) {quick(pos, dir, l, j); l = i;}
            else {quick(pos, dir, i, r); r = j;}
        }
    }
    static void sortByPos(long[] pos, int[] dir){
        quick(pos, dir, 0, pos.length-1);
    }
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.readInt();
        long[] pos = new long[N];
        int[] dir = new int[N];
        for (int i=0; i<N; i++){
            pos[i] = fs.readLong();
            dir[i] = fs.readInt();
        }
        sortByPos(pos, dir);
        long ans = 0;
        long rightseen=0;
        for (int i=0; i<N; i++){
            if (dir[i] == 1) rightseen++;
            else if (dir[i] == -1) {ans+=rightseen;}
        }
        long leftseen=0;
        long zeroL=0;
        for (int i=N-1; i>=0; i--){
            if (dir[i] == -1) leftseen++;
            else if (dir[i] == 0) {zeroL+=leftseen;}
        }
        rightseen=0;
        long right0=0;
        for (int i=0; i<N; i++){
            if (dir[i] == 1) rightseen++;
            else if (dir[i] == 0) {right0+=rightseen;}
        }
        System.out.print(ans+2*(zeroL+right0));
    }
}