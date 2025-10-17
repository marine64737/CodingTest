//https://www.acmicpc.net/problem/17272
//First try
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long total=0;
        for (long i=0; i<=N/M; i++){
            long j = N - M * i;
            total = (total + factorial(i+j)/factorial(i)/factorial(j))%1000000007;
        }
        System.out.print(total);
    }
    static long factorial(long n){
        if (n==0) return 1;
        else if (n==1) return 1;
        return factorial(n-1)*n;
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Main {
    static int[] facto;
    static int C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        C = 1000000007;
        long sum=1;
        for (int i=1; i<=N/M; i++){
            long tmp = 1;
            for (int j=0; j<i; j++){
                tmp = tmp * rem((N-i*M+i-j) / facto(i)) % C;
            }
            sum += tmp;
        }
        System.out.print(sum);
    }
    static long rem(long i){
        long div = 2;
        long mod = 1;
        while (i > 1) {
            if (prime(div) && i%div == 0) {
                mod = mod * div % C;
                i /= div;
            }
            else div++;
        }
        return mod;
    }
    static long facto(long i){
        if (i <= 1) return 1;
        else return facto(i-1)*i;
    }
    static boolean prime(long n){
        if (n==2) return true;
        boolean primeN = true;
        for (int i=2; i<=Math.sqrt(n); i++){
            if (n % i == 0) {
                primeN = false;
                break;
            }
        }
        return primeN;
    }
}
//Third try
import java.io.*;
        import java.util.*;

class Main {
    static int[] facto;
    static int C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        C = 1000000007;
        long sum=1;
        for (int i=1; i<=N/M; i++){
            long tmp = 1;
            for (int j=0; j<i; j++){
                tmp = tmp * rem((N-i*M+i-j) / facto(i)) % C;
            }
            sum += tmp;
        }
        System.out.print(sum);
    }
    static long rem(long i){
        long div = 2;
        long mod = 1;
        while (i > 1) {
            if (prime(div) && i%div == 0) {
                mod = mod * div % C;
                i /= div;
            }
            else div++;
        }
        return mod;
    }
    static long facto(long i){
        if (i <= 1) return 1;
        else return facto(i-1)*i;
    }
    static boolean prime(long n){
        if (n==2) return true;
        boolean primeN = true;
        for (int i=2; i<=Math.sqrt(n); i++){
            if (n % i == 0) {
                primeN = false;
                break;
            }
        }
        return primeN;
    }
}

//fourth try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] dp = new int[M];
        long[][] T = new long[M][M];
        int C = 1000000007;
        Arrays.fill(dp, 1);
        if (N < M) {
            System.out.print(1);
            return;
        }
        T[0][0] = 1; T[0][M-1] = 1;
        for (int c=0; c<M-1; c++) T[c+1][c]=1;

        long[] answer = mul2(Tresult(T, N-M+1, C), dp, C);
        System.out.print(answer[0]);
    }

    static long[][] mul(long[][] arr1, long[][] arr2, int C){
        int len = arr1.length;
        long[][] ans = new long[len][len];
        for (int i=0; i<len; i++) {
            for (int j = 0; j < len; j++) {
                long sum = 0;
                for (int k = 0; k < len; k++) {
                    sum += arr1[i][k] * arr2[k][j];
                    if (sum > C) sum %= C;
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }
    static long[] mul2(long[][] arr1, int[] arr2, int C){
        int len = arr1.length;
        long[] ans = new long[len];
        for (int i=0; i<len; i++) {
            long sum = 0;
            for (int j = 0; j < len; j++) {
                sum += arr1[i][j] * arr2[j];
                if (sum > C) sum %= C;
            }
            ans[i] = sum;
        }
        return ans;
    }

    static long[][] Tresult(long[][] T, long i, int C){
        if (i==1) return T;
        long[][] half = Tresult(T, i/2, C);
        long[][] sq = mul(half, half, C);
        return (i%2==0)? sq : mul(sq, T, C);
    }
}

//Fifth try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        if (N < M) {
            System.out.print(1);
            return;
        }
        int[] dp = new int[M];
        long[][] T = new long[M][M];
        int C = 1000000007;
        Arrays.fill(dp, 1);
        T[0][0] = 1; T[0][M-1] = 1;
        for (int c=0; c<M-1; c++) T[c+1][c]=1;
        T = Tresult(T, N-M+1, C);
        long answer = 0;
        for (int i=0; i<M; i++) answer=(answer+T[0][i]*dp[i])%C;
        System.out.print(answer);
    }

    static long[][] mul(long[][] arr1, long[][] arr2, int C){
        int len = arr1.length;
        long[][] ans = new long[len][len];
        for (int i=0; i<len; i++) {
            for (int j = 0; j < len; j++) {
                long sum = 0;
                for (int k = 0; k < len; k++) {
                    sum += arr1[i][k] * arr2[k][j];
                    if (sum > C) sum %= C;
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }
    static long[][] Tresult(long[][] T, long i, int C){
        if (i==1) return T;
        long[][] half = Tresult(T, i/2, C);
        long[][] sq = mul(half, half, C);
        return (i%2==0)? sq : mul(sq, T, C);
    }
}