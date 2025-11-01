//https://www.acmicpc.net/problem/33666
//AI Recommend
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] P = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) P[i] = Long.parseLong(st.nextToken());

        // 1) 평균(2회 이상 보낸 사람들만) 계산
        long sum2 = 0;
        long cnt2 = 0;
        for (long p : P) {
            if (p >= 2) { sum2 += p; cnt2++; }
        }
        long avg = (cnt2 == 0) ? 0 : (sum2 / cnt2);

        // 2) 매크로 의심(-1) 판정: P_i ≤ avg 이면서 P_i > M이면 같은 메시지를 2번 이상 보냄
        for (long p : P) {
            if (p <= avg && p > M) {
                System.out.print(-1);
                return;
            }
        }

        // 3) 각 메시지별 횟수 집계 (O(N+M))
        long[] diff = new long[M + 2];   // 잔여 분배(1..rem)용 차분
        long totalFull = 0;              // P_i ≤ avg 그룹에서 각 메시지에 공통으로 더해질 몫 합(= sum floor(P_i / M))
        long countAbove = 0;             // P_i > avg 인원 수 (→ 1번 메시지에만 +1)

        for (long p : P) {
            if (p <= avg) {
                totalFull += p / M;          // 전체 회전 수만큼 모든 메시지에 동일 가산
                int rem = (int)(p % M);
                if (rem > 0) {               // 앞쪽 [1..rem] 구간에만 +1
                    diff[1] += 1;
                    diff[rem + 1] -= 1;
                }
            } else {
                countAbove++;                 // 1번 메시지에만 +1
            }
        }

        StringBuilder sb = new StringBuilder();
        long carry = 0;
        for (int i = 1; i <= M; i++) {
            carry += diff[i];                 // 잔여 분배 누적
            long val = totalFull + carry;     // 기본 분배량
            if (i == 1) val += countAbove;    // avg 초과자들의 1번 메시지
            if (i > 1) sb.append(' ');
            sb.append(val);
        }
        System.out.print(sb.toString());
    }
}
//Try
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] people = new long[N];
        long[] diff = new long[M+2];
        int people_num=0;
        long people_total = 0;
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            people[i] = Long.parseLong(st.nextToken());
            if (people[i] > 1){
                people_num++;
                people_total+=people[i];
            }
        }
        long avg = (people_num > 0) ? people_total/people_num : 0;
        long allAdd=0;
        long over = 0;
        for (long p : people){
            if (p <= avg && p > M){
                System.out.print(-1);
                return;
            }
            else if (p <= avg){
                allAdd += p/M;
                int remain = (int)(p%M);
                if (remain > 0) {
                    diff[1]++;
                    diff[remain+1]--;
                }
            }
            else over++;
        }
        long sum=0;
        for (int i=1; i<=M; i++) {
            if (i > 1) sb.append(" ");
            sum+=diff[i];
            if (i==1) sb.append(sum+over+allAdd);
            else sb.append(sum+allAdd);
        }
        System.out.print(sb);
    }
}
//Retry
import java.io.*;

class Main {
    static class FastScanner{
        InputStream in;
        byte[] buffer = new byte[1<<16];
        int ptr=0, len=0;
        FastScanner(InputStream in){
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
        int readInt() throws IOException {
            int c =0;
            int sign = 1;
            do { c = read(); } while (c <= 32);
            if (c == '-') { sign = -1; c = read(); }
            int val = 0;
            while (c > 32){
                val = val * 10 + (c-'0');
                c = read();
            }
            return val * sign;
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = fs.readInt();
        int M = fs.readInt();
        int[] arr = new int[N];
        int[] reply = new int[M+2];
        long message = 0;
        int people=0;
        for (int i=0; i<N; i++) {
            arr[i]= fs.readInt();
            if (arr[i] > 1){
                message+=arr[i];
                people++;
            }

        }
        long avg = (people > 0)? message/people : 0;
        int over = 0;
        int common = 0;
        for (int i=0; i<N; i++) {
            if (arr[i] > M && arr[i] <= avg){
                System.out.print(-1);
                return;
            }
            if (arr[i] > avg) over++;
            else {
                common+=arr[i]/M;
                int remain = arr[i]%M;
                if (remain > 0){
                    reply[remain+1]--;
                    reply[1]++;
                }
            }
        }
        int ans=0;
        for (int i=0; i<M; i++) {
            ans+=reply[i+1];
            if (i==0) sb.append(ans+common+over);
            else sb.append(" ").append(ans+common);
        }
        System.out.print(sb);
    }
}