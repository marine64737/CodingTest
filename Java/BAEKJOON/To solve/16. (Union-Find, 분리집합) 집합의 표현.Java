//https://www.acmicpc.net/problem/1717
//First try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Integer>> arr = new ArrayList<>();
        for (int i=0; i<=n; i++){
            arr.add(new ArrayList<>());
            arr.get(i).add(i);
        }
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int sel = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (sel == 0) {
                for (int j=0; j<=n; j++){
                    if (arr.get(j).contains(a))
                        for (int tmp: arr.get(a)) arr.get(j).add(tmp);
                    if (arr.get(j).contains(b))
                        for (int tmp: arr.get(b)) arr.get(j).add(tmp);
                }
            }
            else {
                if (!start){
                    if (arr.get(a).contains(b)) sb.append("YES");
                    else sb.append("NO");
                    start = true;
                }
                else {
                    if (arr.get(a).contains(b)) sb.append("\n").append("YES");
                    else sb.append("\n").append("NO");
                }
            }
        }
        System.out.print(sb);
        return;
    }
}

//Second try(AI Recommend)
import java.io.*;
        import java.util.*;

class Scratch {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for (int i=0; i<=n; i++) parent[i] = i;
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int sel = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (sel == 0) union(a, b);
            else {
                if (find(a) == find(b)) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }
        System.out.print(sb);
        return;
    }
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }
    static int find(int a){
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}