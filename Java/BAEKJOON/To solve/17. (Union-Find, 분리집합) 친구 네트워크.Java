//https://www.acmicpc.net/problem/4195
//First try
import java.io.*;
import java.util.*;

class Scratch {
    static HashMap<String, String> parent;
    static HashMap<String, Integer> network;
    static boolean[] net;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        for (int i=0; i<N; i++){
            parent = new HashMap<>();
            network = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for (int j=0; j<M; j++){
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                parent.putIfAbsent(a, a);
                parent.putIfAbsent(b, b);
                union(a, b);
                if (!network.containsKey(find(a)) && !network.containsKey(find(b))) {
                    network.putIfAbsent(find(a), 2);
                }
                else if (!network.containsKey(find(a)) || !network.containsKey(find(b))){
                    network.put(find(a), network.get(find(a)) + 1);
                }
                sb.append(network.get(find(a))).append("\n");
            }
        }
        System.out.print(sb);
        return;
    }
    static String find(String a){
        if (parent.get(a).equals(a)) return a;
        return find(parent.get(a));
    }
    static void union(String a, String b){
        a = find(a);
        b = find(b);
        if (!Objects.equals(a, b)) parent.put(b, a);
    }
}

//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {
    static int[] parent, size;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    static int union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return size[ra];

        // union by size (큰 쪽에 합침)
        if (size[ra] < size[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }

        parent[rb] = ra;
        size[ra] += size[rb];
        return size[ra];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>(F * 2);

            parent = new int[F * 2 + 1];
            size   = new int[F * 2 + 1];
            int idx = 0;

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String A = st.nextToken();
                String B = st.nextToken();

                if (!map.containsKey(A)) {
                    map.put(A, idx);
                    parent[idx] = idx;
                    size[idx] = 1;
                    idx++;
                }
                if (!map.containsKey(B)) {
                    map.put(B, idx);
                    parent[idx] = idx;
                    size[idx] = 1;
                    idx++;
                }

                int a = map.get(A);
                int b = map.get(B);
                sb.append(union(a, b)).append('\n');
            }
        }
        System.out.print(sb);
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    static int[] parent;
    static int[] size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            HashMap<String, Integer> hm = new HashMap<>();
            parent = new int[2 * M];
            size = new int[2 * M];
            int index = 0;
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                String A = st.nextToken();
                String B = st.nextToken();
                if (hm.putIfAbsent(A, index) == null) {
                    parent[index] = index;
                    size[index] = 1;
                    index++;
                }
                if (hm.putIfAbsent(B, index) == null) {
                    parent[index] = index;
                    size[index] = 1;
                    index++;
                }
                int a = hm.get(A);
                int b = hm.get(B);
                union(a, b);
                sb.append(size[find(a)]).append("\n");
            }
        }
        System.out.print(sb);
        return;
    }
    static int find(int a){
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a==b) return;
        parent[b] = a;
        size[a]+=size[b];
    }
}