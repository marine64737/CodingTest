//https://www.acmicpc.net/problem/31531
//First try
import java.io.*; import java.util.*;

class Main {
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
//Second try
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int score = 0;
        Double[][] balls = new Double[N][2];
        int[] ver = new int[N];
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            balls[i][0] = Double.parseDouble(st.nextToken());
            balls[i][1] = Double.parseDouble(st.nextToken());
        }
        Arrays.sort(balls, Comparator.comparingDouble(a -> a[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            double a_t = Math.abs((balls[a[0]][0]-balls[a[0]+1][0])/(balls[a[0]][1]-balls[a[0]+1][1]));
            double b_t = Math.abs((balls[b[0]][0]-balls[b[0]+1][0])/(balls[b[0]][1]-balls[b[0]+1][1]));
            if (a_t - b_t >= 0.0000000001) return Double.compare(a_t, b_t);
            else if (balls[a[0]][0] != balls[b[0]][0]) return Double.compare(balls[a[0]][0], balls[b[0]][0]);
            else return Double.compare(balls[a[0]][1], balls[b[0]][1]);
        });
        for (int i=0; i<N-1; i++) if (balls[i][1] > balls[i+1][1]) pq.add(new int[]{i, 0});
        while (!pq.isEmpty()){
            int[] top = pq.poll();
            int[] top2 = pq.peek();
            if (top2 != null && top[0] < N-2 && top[1] == ver[top[0]] && top[0]+1 == top2[0] && top2[1] == ver[top2[0]]
                    && (balls[top[0]+1][0]-balls[top[0]][0]==balls[top[0]+2][0]-balls[top[0]+1][0])
                    && balls[top[0]][1] == 1  && balls[top[0]+1][1] == 0 && balls[top[0]+2][1] == -1
            ){
                top2 = pq.poll();
                score+=5;
                double tmp = balls[top[0]][1];
                balls[top[0]][1] = balls[top2[0]+1][1];
                balls[top[0]+2][1] = tmp;
                balls[top[0]][0]=balls[top[0]+1][0];
                balls[top[0]+2][0]=balls[top[0]+1][0];
                for (int i=-1; i<3; i++){
                    if (top[0]+i >=0 && top[0]+i<N-1 && balls[i][1] > balls[i+1][1]){
                        ver[top[0]+i]++;
                        pq.add(new int[]{top[0]+i, ver[top[0]+i]});
                    }
                }
            }
            else{
                if (top[1] == ver[top[0]]){
                    if ((balls[top[0]][1]==1 && balls[top[0]+1][1]==0) || (balls[top[0]][1]==0 && balls[top[0]+1][1]==-1))
                        score+=2;
                    else if (balls[top[0]][1]==1 && balls[top[0]+1][1]==-1) score++;
                    double tmp = balls[top[0]][1];
                    balls[top[0]][1] = balls[top[0]+1][1];
                    balls[top[0]+1][1] = tmp;
                    tmp = balls[top[0]][0]+balls[top[0]+1][0];
                    balls[top[0]][0]=tmp/2;
                    balls[top[0]+1][0]=tmp/2;
                    for (int i=-1; i<2; i++){
                        if (top[0]+i >=0 && top[0]+i<N-1 && balls[top[0]+i][1] > balls[top[0]+i+1][1]){
                            ver[top[0]+i]++;
                            pq.add(new int[]{top[0]+i, ver[top[0]+i]});
                        }
                    }
                }
            }
        }
        System.out.print(score);
    }
}