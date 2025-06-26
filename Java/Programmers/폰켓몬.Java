public int solution(int[] nums) {
    int answer = 0;
    int[] numsSet = Arrays.stream(nums).distinct().toArray();
    return (numsSet.length > nums.length/2) ?
            nums.length/2 : numsSet.length;
}

public int solution(int[] nums) {
    int answer = 0;
    int[] numsSet = Arrays.stream(nums).distinct().toArray();
    return Math.min(numsSet.length, nums.length / 2);
}