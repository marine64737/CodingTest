import java.util.*;

class Scratch {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 2, 3}));
    }

// First try
//    public static int solution(int[] citations) {
//        int answer = 0;
//        int num=0;
//        int N = citations.length;
//        Integer[] tmp = Arrays.stream(citations).boxed().toArray(Integer[]::new);
//        Arrays.sort(tmp, Collections.reverseOrder());
//        for (int j=0; j< N; j++){
//            for (int i=0; i< N; i++){
//                if (tmp[i] >= tmp[j]){
//                    num++;
//                }
//            }
//            if (tmp[j] == num){
//                answer = num;
//                break;
//            }
//            num = 0;
//        }
//        return answer;
//    }

    //Second try
    public static int[] solution(int[] prices) {
    int n = prices.length;
    int[] answer = new int[n];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {
        // If current price < price at stack top, calculate time and pop
        while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
            int top = stack.pop();
            answer[top] = i - top;
        }
        stack.push(i);
    }

    // For prices that never dropped
    while (!stack.isEmpty()) {
        int top = stack.pop();
        answer[top] = n - top - 1;
    }

    return answer;
}
}