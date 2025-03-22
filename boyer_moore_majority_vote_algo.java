public class boyer_moore_majority_vote_algo {
    int majorityElement(int[] nums){
        int candidate = nums[0];
        int count = 1;
        for(int i = 1; i <nums.length; i++){
            if (count == 0) candidate = nums[i];
            if (nums[i] == candidate) count++;
            else count--;
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 3, 2};
        int[] arr2 = {2, 2, 1, 1, 1, 2, 2};
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 9, 9, 9};
        int[] arr4 = {1,2,1,2,1,2,1,2,2,1,2,1,2,1};
        boyer_moore_majority_vote_algo algo = new boyer_moore_majority_vote_algo();
        System.out.println(algo.majorityElement(arr1));
        System.out.println(algo.majorityElement(arr2));
        System.out.println(algo.majorityElement(arr3));
        System.out.println(algo.majorityElement(arr4));
    }
}
