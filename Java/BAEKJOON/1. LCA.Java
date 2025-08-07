//https://www.acmicpc.net/problem/11437
public void main(void) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = br.readline();
    int[][] nodes = new int[N-1][2];
    for (int i = 0; i < N-1; i++){
        nodes[i] = br.readline().split(" ");
    }
    int M = br.readline();
    int[][] questions = new int[M][2];
    for (int i = 0; i < M; i++){
        questions[i] = br.readline().split(" ");
    }
}