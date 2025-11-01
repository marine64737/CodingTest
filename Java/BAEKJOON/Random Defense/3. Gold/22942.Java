//https://www.acmicpc.net/problem/22942
//First try
import java.io.*;
import java.util.*;

class Scratch {
    static class Circle {
        int pos;
        int dir;
        int id;
        Circle (int pos, int dir, int id){
            this.pos = pos;
            this.dir = dir;
            this.id = id;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        List<Circle> circle = new ArrayList<>();
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            circle.add(new Circle(x - r, 0, i));
            circle.add(new Circle(x + r, 1, i));
        }
        circle.sort((a,b) -> (a.pos==b.pos) ? Long.compare(a.dir, b.dir) : Long.compare(a.pos, b.pos));
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (Circle c : circle) {
            if (c.dir == 0) dq.push(c.id);
            else {
                if (dq.isEmpty() || dq.peek() != c.id) {
                    System.out.print("NO");
                    return;
                }
                dq.pop();
            }
        }
        System.out.print("YES");
        return;
    }
}