//https://school.programmers.co.kr/learn/courses/30/lessons/118667
//First try
//public int solution(int[] queue1, int[] queue2) {
//    int answer = -2;
//    int i, j = 0;
//    long x, y = 0;
//    int length = queue1.length;
//    for (int a = 0; a < length; a++){
//        x += queue1[a];
//        y += queue2[a];
//    }
//    if ((x+y) %2 != 0) return -1;
//    while (i+j <= 2*length){
//        if (x < y){
//            x += queue2[j%length];
//            y -= queue2[j%length];
//            j++;
//        }
//        else if (x > y){
//            x -= queue1[i%length];
//            y += queue1[i%length];
//            i++;
//        }
//        else if (i+j < 2){
//            if (queue2[j%length] < queue1[i%length]){
//                x += queue2[j%length];
//                y -= queue2[j%length];
//                j++;
//            }
//            else {
//                x -= queue1[i%length];
//                y += queue1[i%length];
//                i++;
//            }
//
//        }
//        else {
//            return i+j;
//        }
//    }
//    return (x == y) ? i+j : -1;
//}

public static int solution(int[] queue1, int[] queue2) {
    int answer = -2;
    int count = 0;
    long x = 0, y = 0;
    int length = queue1.length;
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();
    for (int a = 0; a < length; a++){
        q1.add(queue1[a]);
        q2.add(queue2[a]);
        x += queue1[a];
        y += queue2[a];
    }
    while (count <= 3*length){
        if (x < y){
            int num = q2.remove();
            x += num;
            y -= num;
            q1.add(num);
        }
        else if (x > y){
            int num = q1.remove();
            x -= num;
            y += num;
            q2.add(num);
        }
        else {
            return count;
        }
        count++;
    }
    return -1;
}