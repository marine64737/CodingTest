//https://www.acmicpc.net/problem/14888
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Scratch {
    static int N;
    static int[] arr;
    static int[] operand;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operand = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dfs(0, arr[0]);
        System.out.println(max);
        System.out.print(min);
    }

    public static void dfs(int depth, int result){
        if (depth+1 == N) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }
        for (int i=0; i<4; i++){
            if (operand[i] > 0){
                operand[i] --;
                if (i==0) dfs(depth+1, result + arr[depth+1]);
                else if (i==1) dfs(depth+1, result - arr[depth+1]);
                else if (i==2) dfs(depth+1, result * arr[depth+1]);
                else dfs(depth+1, result / arr[depth+1]);
                operand[i] ++;
            }
        }
    }
}