//https://www.acmicpc.net/problem/1753
//Example or AI Recommend
import java.io.*;
import java.util.*;

class Main {
    static class Node implements Comparable<Node> {
        int v, w;
        Node(int v, int w) {
            this.v = v; this.w = w;
        }
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        List<List<Node>> g = new ArrayList<>();
        for (int i = 0; i <= V; i++) g.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g.get(u).add(new Node(v, w));
        }

        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.w > dist[cur.v]) continue; // 이미 더 짧은 경로 있음

            for (Node next : g.get(cur.v)) {
                if (dist[next.v] > dist[cur.v] + next.w) {
                    dist[next.v] = dist[cur.v] + next.w;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }
}

//배열 버전(AI Recommend)
import java.io.*;
        import java.util.*;

public class Main {
    static class FastScanner {
        BufferedInputStream in = new BufferedInputStream(System.in);
        byte[] buf = new byte[1 << 16];
        int ptr = 0, len = 0;
        int read() throws IOException {
            if (ptr >= len) { len = in.read(buf); ptr = 0; if (len < 0) return -1; }
            return buf[ptr++];
        }
        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            do { c = read(); } while (c <= ' ');
            if (c == '-') { s = -1; c = read(); }
            while (c > ' ') { x = x * 10 + (c - '0'); c = read(); }
            return x * s;
        }
    }

    static class Edge {
        int v, w;
        Edge(int v, int w) { this.v = v; this.w = w; }
    }
    static class Node implements Comparable<Node> {
        int v, dist;
        Node(int v, int dist) { this.v = v; this.dist = dist; }
        public int compareTo(Node o) { return Integer.compare(this.dist, o.dist); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int V = fs.nextInt();
        int E = fs.nextInt();
        int S = fs.nextInt();

        List<Edge>[] g = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            int w = fs.nextInt();
            g[u].add(new Edge(v, w));      // 단방향
        }

        final int INF = 1_000_000_000;
        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[S] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v, d = cur.dist;

            // 구식(stale) 항목 스킵 (핵심)
            if (d != dist[u]) continue;

            for (Edge e : g[u]) {
                int nd = d + e.w;                 // 누적거리
                if (nd < dist[e.v]) {
                    dist[e.v] = nd;
                    pq.add(new Node(e.v, nd));    // 누적거리로 삽입
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append('\n');
        }
        System.out.print(sb);
    }
}
