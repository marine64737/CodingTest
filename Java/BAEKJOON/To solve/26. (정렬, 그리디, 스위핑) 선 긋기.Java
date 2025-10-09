//https://www.acmicpc.net/problem/2170
//First try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] start = new long[N];
        long[] end = new long[N];
        Arrays.fill(start, Integer.MAX_VALUE);
        Arrays.fill(end, Integer.MIN_VALUE);
        StringTokenizer st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken());
        end[0] = Integer.parseInt(st.nextToken());
        int index = 1;
        for (int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            int j;
            for (j = 0; j < index; j++){
                if ((a >= start[j] && a <= end[j]) || (b >= start[j] && b <= end[j])){
                    start[j] = Math.min(a, start[j]);
                    end[j] = Math.max(b, end[j]);
                    break;
                }
            }
            if (j==index){
                start[j] = Math.min(a, start[j]);
                end[j] = Math.max(b, end[j]);
                index++;
            }
        }
        long sum=0;
        for (int i = 0; i < index; i++) sum += end[i]-start[i];
        System.out.print(sum);
        return;
    }
}

//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] seg = new long[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (a > b) { long t = a; a = b; b = t; } // 혹시 몰라 정규화
            seg[i][0] = a;
            seg[i][1] = b;
        }

        Arrays.sort(seg, (x, y) -> {
            if (x[0] == y[0]) return Long.compare(x[1], y[1]);
            return Long.compare(x[0], y[0]);
        });

        long ans = 0;
        long curL = seg[0][0], curR = seg[0][1];

        for (int i = 1; i < N; i++) {
            long L = seg[i][0], R = seg[i][1];
            if (L <= curR) {                // 겹치거나 맞닿음
                if (R > curR) curR = R;     // 확장
            } else {                         // 완전히 분리
                ans += curR - curL;
                curL = L; curR = R;
            }
        }
        ans += curR - curL; // 마지막 구간

        System.out.println(ans);
    }
}
