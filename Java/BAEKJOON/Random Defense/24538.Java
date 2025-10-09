//https://www.acmicpc.net/problem/24538
//First try
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] employee = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            employee[i][0] = Integer.parseInt(st.nextToken());
            employee[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= K; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (employee[j][0] <= i && i <= employee[j][1]) {
                    sum+=i-employee[j][0]+1;
                }
            }
            sb.append(sum+" ");
        }
        System.out.print(sb);
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] employee = new int[N][2];
        boolean[] worked = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            employee[i][0] = Integer.parseInt(st.nextToken());
            employee[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= K; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (!worked[j]){
                    if (employee[j][0] <= i && i <= employee[j][1]) {
                        sum+=i-employee[j][0]+1;
                    }
                    else if (i > employee[j][1]) worked[j] = true;
                }
            }
            sb.append(sum+" ");
        }
        System.out.print(sb);
    }
}
//AI Recommend(1)
import java.io.*;
        import java.util.*;

public class Main {
    static class Fast {
        private final InputStream in;
        private final byte[] buf = new byte[1<<16];
        private int ptr = 0, len = 0;
        Fast(InputStream is){ in = is; }
        private int read() throws IOException {
            if (ptr >= len) { len = in.read(buf); ptr = 0; if (len < 0) return -1; }
            return buf[ptr++];
        }
        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') { s = -1; c = read(); }
            while (c > ' ') { x = x*10 + (c - '0'); c = read(); }
            return x * s;
        }
    }

    public static void main(String[] args) throws Exception {
        Fast fs = new Fast(System.in);
        int N = fs.nextInt();

        int maxE = 0;
        int[] S = new int[N];
        int[] E = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = fs.nextInt();
            E[i] = fs.nextInt();
            if (E[i] > maxE) maxE = E[i];
        }

        long[] cntDiff = new long[maxE + 3];
        long[] sumDiff = new long[maxE + 3];

        for (int i = 0; i < N; i++) {
            int s = S[i], e = E[i];
            cntDiff[s] += 1;
            cntDiff[e + 1] -= 1;
            sumDiff[s] += s;
            sumDiff[e + 1] -= s;
        }

        long alive = 0, sumS = 0;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= maxE; t++) {
            alive += cntDiff[t];
            sumS  += sumDiff[t];
            long profit = (long)(t + 1) * alive - sumS; // (t+1)*alive - sumS
            if (t > 1) sb.append(' ');
            sb.append(profit);
        }
        System.out.print(sb.toString());
    }
}
//AI Recommend(2) -  시작일수 고정에 근무 일수만 나와있을 때.
import java.io.*;
        import java.util.*;

public class Main {
    static class Fast {
        private final InputStream in;
        private final byte[] buf = new byte[1<<16];
        private int ptr=0, len=0;
        Fast(InputStream is){ in=is; }
        private int read() throws IOException {
            if (ptr>=len) { len=in.read(buf); ptr=0; if (len<0) return -1; }
            return buf[ptr++];
        }
        int nextInt() throws IOException {
            int c, s=1, x=0;
            do c=read(); while (c<=32);
            if (c=='-') { s=-1; c=read(); }
            while (c>32) { x = x*10 + (c-'0'); c=read(); }
            return x*s;
        }
    }

    public static void main(String[] args) throws Exception {
        Fast fs = new Fast(System.in);

        // 첫 두 수를 읽어두고, 나머지 개수로 N/K를 판별
        int a = fs.nextInt();      // N 또는 K
        int b = fs.nextInt();      // K 또는 N

        ArrayList<Integer> arr = new ArrayList<>();
        try {
            while (true) arr.add(fs.nextInt());
        } catch (Exception ignore) {}

        int N, K;
        if (arr.size() == a) { N = a; K = b; }
        else if (arr.size() == b) { N = b; K = a; }
        else {
            // 애매하면 관례대로 N=a, K=b로 둔다.
            N = a; K = b;
        }

        // 차분 배열: diff[1] += 1, diff[a_i+1] -= 1  → alive[d] = d일 이상 근무자 수
        int[] diff = new int[K + 3];  // K 최대 1e6 정도. 메모리 OK.  (문제 서술상)
        for (int i = 0; i < N; i++) {
            int ai = arr.get(i);
            if (ai <= 0) continue;            // 근무일수 0이면 기여 없음
            if (ai > K) ai = K;               // K일 이후는 출력하지 않으므로 K로 캡
            diff[1] += 1;
            diff[ai + 1] -= 1;
        }

        StringBuilder sb = new StringBuilder();
        long alive = 0;                        // 현재 d일 이상 근무 중인 사람 수
        for (int d = 1; d <= K; d++) {
            alive += diff[d];
            long profit = alive * d;          // d번째 날 수익
            sb.append(profit).append('\n');
        }
        System.out.print(sb);
    }
}
//Last try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] employee = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            employee[i][0] = Integer.parseInt(st.nextToken());
            employee[i][1] = Integer.parseInt(st.nextToken());
        }
        long[] cntEmp = new long[K+2];
        long[] sumStart = new long[K+2];
        for (int i=0; i<N; i++){
            cntEmp[employee[i][0]]++;
            cntEmp[employee[i][1]+1]--;
            sumStart[employee[i][0]]+=employee[i][0];
            sumStart[employee[i][1]+1]-=employee[i][0];
        }
        long numEmp = 0;
        long sumEmp = 0;
        for (int i=1; i<=K; i++){
            numEmp += cntEmp[i];
            sumEmp += sumStart[i];
            if (i>1) sb.append(" ");
            sb.append((i+1)*numEmp-sumEmp);
        }
        System.out.print(sb);
    }
}