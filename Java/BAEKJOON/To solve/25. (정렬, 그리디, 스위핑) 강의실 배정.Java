//https://www.acmicpc.net/problem/11000
//First try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cl = new int[N][2];
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cl[i][0] = Integer.parseInt(st.nextToken());
            cl[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cl, (a, b) -> {
            if (a[1]==b[1]) return a[0]-b[0];
            return a[1]-b[1];
        });
        List<Integer> lastTime = new ArrayList<>();
        lastTime.add(0);
        int index=1;
        int start=0;
        for (int i=0; i<N; i++){
            int j=0;
            for (j = start; j <index; j++){
                if (cl[i][0] >= lastTime.get(j)){
                    lastTime.set(j, cl[i][1]);
                    break;
                }
            }
            if (j ==index) {
                lastTime.add(cl[i][1]);
                index++;
            }
            else start++;
        }
        System.out.print(index);
        return;
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1]==b[1]) return Math.toIntExact(a[0] - b[0]);
            return Math.toIntExact(a[1] - b[1]);
        });
        long[] mt = new long[N];
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = Integer.parseInt(st.nextToken());
            long end = Integer.parseInt(st.nextToken());
            pq.add(new long[]{start, end});
        }
        int num = 1;
        while (!pq.isEmpty()){
            long[] tmp = pq.poll();
            int i;
            for (i=0; i<num; i++){
                if (tmp[0] >= mt[i]) {
                    mt[i] = tmp[1];
                    break;
                }
            }
            if (i==num) {
                mt[num]=tmp[1];
                num++;
            }
        }
        System.out.print(num);
        return;
    }
}

//AI Recommend
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
        Arrays.sort(mt, (a, b) -> (a[0] == b[0])? Integer.compare(a[1],b[1]) : Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<N; i++){
            if (!pq.isEmpty() && mt[i][0] >= pq.peek()) pq.poll();
            pq.add(mt[i][1]);
        }
        System.out.print(pq.size());
        return;
    }
}