//https://www.acmicpc.net/problem/10818
public static void main(String[] args) throws IOException {
    // test your function here
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String[] inputs = br.readLine().split(" ");
    List<Integer> nums = new ArrayList<>();
    for (int i = 0; i< N; i++){
        int num = Integer.parseInt(inputs[i]);
        nums.add(num);
    }
    System.out.println(Collections.min(nums)+" "+Collections.max(nums));

}