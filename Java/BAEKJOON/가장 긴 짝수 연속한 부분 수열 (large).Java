//https://www.acmicpc.net/problem/22862
import java.io.*; import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {
        int answer=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int odd = 0;
        int index = 0;
        for (int i=0; i<N; i++){
            if (arr[i]%2 == 0) answer = Math.max(answer, i-index+1);
            else {
                while (odd == K) {
                    if (arr[index]%2 == 1){
                        odd--;
                    }
                    index++;
                }
                odd++;
                answer = Math.max(answer, i-index+1);
            }
        }
        System.out.print(answer-odd);
        return;
    }
}
