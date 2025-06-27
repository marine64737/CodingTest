//https://school.programmers.co.kr/learn/courses/30/lessons/42747
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
public static int solution(int[] citations) {
    int answer = 0;
    int N = citations.length;
    Arrays.sort(citations);
    for (int i=0; i< N; i++){
        if (citations[i] >= N - i){
            return N-i;
        }
    }
    return answer;
}