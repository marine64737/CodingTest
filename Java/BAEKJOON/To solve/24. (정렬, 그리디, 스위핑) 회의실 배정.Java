//https://www.acmicpc.net/problem/1931
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] mt = new int[N][2];
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            mt[i][0] = Integer.parseInt(st.nextToken());
            mt[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(mt, (a,b) -> {
            if (a[1]==b[1]) return a[0]-b[0];
            return a[1]-b[1];
        });
        int cnt = 0;
        int lastTime = 0;
        for (int i=0; i<N; i++){
            if (mt[i][0] >= lastTime){
                cnt++;
                lastTime = mt[i][1];
            }
        }
        System.out.print(cnt);
        return;
    }
}