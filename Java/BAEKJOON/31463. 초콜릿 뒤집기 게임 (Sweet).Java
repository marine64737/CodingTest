//https://www.acmicpc.net/problem/31463
//First try
import java.io.*;

class Main {
    static long ans=0;
    static long C = (long) 1e9+7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int C = (int) (1e9+7);
        for (int i=0; i<N; i++){
            long ans=0;
            int idx=-1;
            int num=0;
            int l_idx=0;
            int r_idx=0;
            String s = br.readLine();
            char[] chocolate = s.toCharArray();
            int[] left = new int[chocolate.length];
            int[] right = new int[chocolate.length];
            int H=0;
            for (char c : chocolate) if (c == 'H' || c == '?') H++;
            for (int j = 0; j < chocolate.length; j++){
                right[j] = H;
                if (chocolate[j]=='H'|| chocolate[j]=='?'){
                    H--;
                    if (j ==0) left[j] = 1;
                    else left[j] = left[j -1] + 1;
                }
                else{
                    if (j ==0) left[j] = 0;
                    else left[j] = left[j -1];
                }
            }
            for (int j = 0; j < chocolate.length; j++){
                if (chocolate[j] == 'H' || chocolate[j] == '?'){
                    if(idx<=0 && left[j]%2==1) {
                        idx = j;
                        if (idx == 0) l_idx++;
                        else num++;
                    }
                    else if (idx > 0 && (left[j]-left[idx-1])%2==1 && right[j]%2==1){
                        idx = j;
                        if (idx == N-1) r_idx++;
                        else num++;
                    }
                }
            }
            System.out.println((pow(num)/2+l_idx+r_idx)%C);
        }
    }
    static long pow(int n){
        if (n==0) return 0;
        else if (n==1) return 2;
        long half = pow(n/2)%C;
        return (n%2==0)? half*half%C : half*half*2%C;
    }
}
//Last try
import java.io.*;

class Scratch {
    static long ans=0;
    static long C = (long) 1e9+7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int C = (int) (1e9+7);
        for (int i=0; i<N; i++){
            String s = br.readLine();
            char[] chocolate = s.toCharArray();
            int H=0;
            int Q = 0;
            for (char c : chocolate) {
                if (c == 'H') H++;
                else if (c == '?') Q++;
            }
            System.out.println((Q==0)? H%2 : pow(Q-1)%C);
        }
    }
    static long pow(int n){
        if (n==0) return 1;
        else if (n==1) return 2;
        long half = pow(n/2)%C;
        return (n%2==0) ? half*half%C : half*half*2%C;
    }
}