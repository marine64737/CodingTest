//My try
public int solution(int[] queue1, int[] queue2) {
    int answer = -2;
    int i, j = 0;
    long x, y = 0;
    int length = queue1.length;
    for (int a = 0; a < length; a++){
        x += queue1[a];
        y += queue2[a];
    }
    if ((x+y) %2 != 0) return -1;
    while (i+j <= 2*length){
        if (x < y){
            x += queue2[j%length];
            y -= queue2[j%length];
            j++;
        }
        else if (x > y){
            x -= queue1[i%length];
            y += queue1[i%length];
            i++;
        }
        else if (i+j < 2){
            if (queue2[j%length] < queue1[i%length]){
                x += queue2[j%length];
                y -= queue2[j%length];
                j++;
            }
            else {
                x -= queue1[i%length];
                y += queue1[i%length];
                i++;
            }

        }
        else {
            return i+j;
        }
    }
    return (x == y) ? i+j : -1;
}