package Kakao_Blind_Recruitment_2022;

import java.util.StringTokenizer;

/**

 문제요약 :
 주어진 숫자로 k진수로 변환헀을 때
 0 사이에 있는 숫자가 소수인지 판별해서 개수를 리턴

 <solution>
 소수 판별문제는 에라토스테네스의 체 사용
 k진수로 변환했을 때 해당 숫자가 소수인지 판별하려면
 특정 숫자가 소수인지 구분해야댐, 미리 저장해서 사용하기 힘들듯

 </solution>

 */

class Solution_k진수에서소수개수구하기_level2 {
    public int solution(int n, int k) {
        int answer = 0;

        StringBuilder arr = new StringBuilder();
        while(n>0) {
            arr.append(n%k);
            n /= k;
        }
        String str = arr.reverse().toString();
        StringTokenizer st = new StringTokenizer(str, "0");
        while(st.hasMoreTokens()){
            String number = st.nextToken();
            if(isPrime(Long.parseLong(number))) answer++;
        }
        return answer;
    }

    private boolean isPrime(Long n) {
        if(n <= 1) return false;
        else if(n == 2) return true;
        for(int i = 2; i <= Math.sqrt(n); i++)
            if(n % i == 0)
                return false;
        return true;
    }
}