//https://www.acmicpc.net/problem/1182
 //First try
import java.io.*;
import java.util.*;

class Scratch {
    static int N, S, result;
    static int[] arr;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = Integer.MIN_VALUE;
        answer = 0;
        dfs(0, 0);
        System.out.print(answer);
    }
    public static  void dfs(int depth, int index){
        if (result == S) answer++;
        for (int i=index; i<N; i++){
            if (depth == 0) {
                result = arr[i];
                dfs(depth+1, i+1);
            }
            else {
                result += arr[i];
                dfs(depth+1, i+1);
                result -= arr[i];
            }
        }
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    static int N, S, result;
    static int[] arr;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = 0;
        answer = 0;
        dfs(0, 0);
        System.out.print(answer);
    }
    public static  void dfs(int depth, int index){
        for (int i=index; i<N; i++){
            if (depth == 0) {
                result = arr[i];
                if (result == S) answer++;
                dfs(depth+1, i+1);
            }
            else {
                result += arr[i];
                if (result == S) answer++;
                dfs(depth+1, i+1);
                result -= arr[i];
            }
        }
    }
}