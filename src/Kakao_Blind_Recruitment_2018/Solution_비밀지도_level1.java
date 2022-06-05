package Kakao_Blind_Recruitment_2018;

/**

문제요약 :
두개의 빔리지도가 이진수형태로 주어졌을 때
두 비밀지도를 합쳐 전체 지도를 완성
<solution>
 두개의 비밀지도를 합치는 과정은 | 연산의 결과
 그후 2로 나누면서 지도출력
 </solution>
 */
class Solution_비밀지도_level1 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i=0; i<n; i++){
            answer[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
            answer[i] = answer[i].replaceAll("1", "#");
            answer[i] = answer[i].replaceAll("0", " ");
            while(answer[i].length() < n)
                answer[i] = ' ' + answer[i];
        }



        return answer;
    }
}