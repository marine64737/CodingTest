//https://www.acmicpc.net/problem/13549
//First try
import java.io.*; import java.util.*;

class Scratch {
    static class Node implements Comparable<Node>{
        int v, w;
        Node(int v, int w){
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int max = Math.max(N, K);
        List<List<Node>> graph = new ArrayList<>();
        int range = (max < 50000) ? 2*max : 100000;
        for (int i=0; i<=range; i++) graph.add(new ArrayList<>());
        int[] dist = new int[range+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;
        for (int i=0; i<=range; i++){
            if (i > 0) graph.get(i).add(new Node(i-1, 1));
            if (i < range) graph.get(i).add(new Node(i+1, 1));
            if (i <= range/2) graph.get(i).add(new Node(2*i, 0));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(N, 0));
        while (!pq.isEmpty()){
            Node n = pq.poll();
            for (Node next : graph.get(n.v)){
                if (dist[next.v] > dist[n.v] + next.w){
                    dist[next.v] = dist[n.v] + next.w;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
        System.out.print(dist[K]);
        return;
    }
}

//Deque 사용 (AI Recommend)
import java.io.*;
        import java.util.*;

public class Main {
    static final int MAX = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(N);
        dist[N] = 0;

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            // 1) 순간이동 (비용 0 → 앞에 추가)
            int next = cur * 2;
            if (next <= MAX && dist[next] > dist[cur]) {
                dist[next] = dist[cur];
                dq.addFirst(next); // 비용 0 → 앞쪽에 넣음
            }

            // 2) 앞으로 한 칸 (비용 1 → 뒤에 추가)
            next = cur + 1;
            if (next <= MAX && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                dq.addLast(next); // 비용 1 → 뒤쪽에 넣음
            }

            // 3) 뒤로 한 칸 (비용 1 → 뒤에 추가)
            next = cur - 1;
            if (next >= 0 && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                dq.addLast(next);
            }
        }

        System.out.println(dist[K]);
    }
}
