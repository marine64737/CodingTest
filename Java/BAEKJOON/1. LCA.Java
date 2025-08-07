//https://www.acmicpc.net/problem/11437
public void main(void) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = br.readLine();
    String[] inputs = new String[N-1];
    for (int i = 0; i < N-1; i++){
        inputs[i] = br.readLine().split(" ");
    }
    int[][] nodes = new int[N-1][2];
    for (int i = 0; i < N-1; i++){
    }
    int M = br.readLine();
    int[][] questions = new int[M][2];
    for (int i = 0; i < M; i++){
        questions[i] = br.readLine().split(" ");
    }
    System.out.println("N: "+N);
    System.out.println("nodes: "+nodes);
    System.out.println("M: "+M);
    System.out.println("questions: "+questions);
}