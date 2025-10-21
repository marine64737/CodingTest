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