package Kakao_Blind_Recruitment_2022;

/**
문제요약 :
 라이언이 우승하게하기
 같은 점수에 같은 발을 쏘면 패배
 최종 결과합산이 같으면 패배
 둘다 0발이면 아무도 가져가지 못한다.

<solution>
 브루트포스 방식으로 완탐 진행
 점수 차이가 가장 크게 우승해야하므로, 서로의 점수합산 점수를 가져가야한다.
 </solution>
 */

class Solution_양궁대회_level2 {
    private int maxdiff;
    private int[] answer;

    public int[] solution(int n, int[] info) {
        answer = new int[info.length];
        int[] data = new int[info.length];
        maxdiff = 0;
        int index = 0;
        int lion=0, apeach = 0;
        shoot(n,index, data, info);

        if(maxdiff == 0) return new int[]{-1};
        return answer;
    }


    private void shoot(int n, int index, int[] data, int[] info) {
        if(index==10) {
            int input = -1;
            if(n!=0){
                for(int i=10; i>=0; i--){
                    if(data[i] == 0) {
                        input = i;
                        data[i] = n;
                        n=0;
                        break;
                    }
                }
            }

            int lion = 0, apeach=0;
            for(int i=0; i<=10; i++){
                if(data[i] > info[i]) lion += 10-i;
                else if(info[i] != 0) apeach += 10-i;
            }

            System.out.println("l : "+lion+", a : " + apeach);
            if(lion > apeach) {
                if(maxdiff < lion - apeach) {
                    maxdiff = lion - apeach;
                    System.arraycopy(data, 0, answer, 0, 11);
                } else if(maxdiff == lion-apeach){
                    int answermin = 0;
                    int lionmin = 0;
                    for(int i=10; i>=0; i--){
                        if(answer[i] != 0) {
                            answermin = i;
                            break;
                        }
                    }
                    for(int i=10; i>=0; i--){
                        if(data[i] != 0) {
                            lionmin = i;
                            break;
                        }
                    }
                    if(lionmin > answermin || (lionmin == answermin && data[lionmin] > answer[answermin])) {
                        System.arraycopy(data, 0, answer, 0, 11);
                    }
                }
            }
            if(input != -1) data[input] = 0;
            return;
        }

        shoot(n, index+1, data, info);
        if(n>info[index]) {
            data[index] = info[index]+1;
            shoot(n-(info[index]+1), index+1, data, info);
            data[index] = 0;
        }



    }
}