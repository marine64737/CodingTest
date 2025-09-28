//https://www.acmicpc.net/problem/1916
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
        public int compareTo(Node a) {
            return this.w - a.w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<List<Node>> graph = new ArrayList<>();
        for (int i=0; i<=N; i++) graph.add(new ArrayList<>());
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        pq.add(new Node(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()){
            Node cur = pq.poll();
            if (dist[cur.v] < cur.w) continue;
            for (Node next : graph.get(cur.v)){
                if (dist[next.v] > dist[cur.v] + next.w){
                    dist[next.v] = dist[cur.v] + next.w;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
        System.out.print(dist[end]);
        return;
    }
}
