/**
 * 문제요약
 * 다리를 건너는데 최대 k개의 돌만큼 뛰어넘을 수 있다.
 * 사람들 무제한으로 건널 수 있고, 돌을 밟으면 내구도가 1씩감소
 * 최대 몇명까지 건널 수 있는지
 *
 * <solution>
 * 생각해보니까 연속된 돌다리 k개의 합이 최소인 부분에서 무조건
 * 못건너게 되어있다.
 * </solution>
 */

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int start = 0;
        int end = 987654321;

        while(start <= end){
            int mid = (start+end) >> 1;
            if(isPossible(mid, stones,k)){
                start = mid+1;
                answer = Math.max(answer, mid);
            } else {
                end = mid-1;
            }
        }
        return start-1;
    }

    private boolean isPossible(int mid, int[] stones, int k) {
        int cnt = 0;
        for(int i=0; i<stones.length; i++){
            if(stones[i] < mid) cnt++;
            else {
                cnt=0;
            }
            if(cnt == k) return false;
        }
        return true;
    }

}