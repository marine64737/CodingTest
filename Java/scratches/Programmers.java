import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;

class Scratch {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 0, 6, 1, 5}));
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
    public static int solution(int[] citations) {
        int answer = 0;
        int num=0;
        int N = citations.length;
        Integer[] tmp = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(tmp, Collections.reverseOrder());
        for (int j=0; j< N; j++){
            for (int i=0; i< N; i++){
                if (tmp[i] >= tmp[j]){
                    num++;
                }
            }
            if (tmp[j] == num){
                answer = num;
                break;
            }
            num = 0;
        }
        return answer;
    }
}