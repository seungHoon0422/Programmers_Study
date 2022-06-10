package Kakao_Blind_Recruitment_2020;

/**
2020 kakao blind recruit 문자열 압축 level 2

[투포인터, 문자열]

문제요약 :
문자열이 주어졌을때 해당 문자열을 최대로 압축시켰을 때 길이를 리턴시킨다.
반복되는 문자열의 경우에는 해당 문자열 앞에 숫자로 반복된 횟수를 적는 방식으로 압축시킨다.

<solution>
 주어진 문자열을 압축시킬 단위를 설정할 때 작은 단위부터 시작하기 보다
 큰단위로 시작해서 최대로 압축시킬 수 있는 경우를 찾는다.
 문자열을 압축시키기 위해서는 전체 문자열길이의 절반 길이부터 시작해서 탐색 시작
 
 -- 생각보다 오류를 많이만남.... 문제를 잘읽자
 처음부터 정해진 길이만큼 압축
 문제를 너무 어렵게생각해서, 압축할 수 있는 거는 모두압축시켜버렸음...
 정해진 길이만큼씩 잘라서 읽고, 안되면 그냥 추가해버리기
 </solution>


 */

class Solution_문자열압축_level2 {
    public int solution(String s) {
        int answer = 0;
        String ans = s;
        int unit = s.length()/2;

        for(int i=unit; i>0; i--) {
            int left = 0;
            int right = i;
            String after = "";
            while(right < s.length()){
                right = left + i;
                int count = 1;
                String substr = s.substring(left, left+i);
                int nl = right;
                int nr = nl + i;
                while(nr <= s.length()) {
                    String next = s.substring(nl, nr);
                    if(substr.equals(next)) {
                        count++;
                        nl = nr;
                        nr = nl+i;
                    } else break;
                }

                if(count == 1) {
                    after += s.substring(left, left+i);
                    left += i;
                    right = left + i;
                } else {
                    after += count + substr;
                    left = nl;
                    right = left + i;
                }
            }
            after += s.substring(left);
            if(left!=0 && after.length() < ans.length()){
                ans = after;
            }

        }
        return ans.length();
    }
}
// abcabcdededededede
// abcabc6de
// 9