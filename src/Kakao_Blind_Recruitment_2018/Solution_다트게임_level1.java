package Kakao_Blind_Recruitment_2018;

import java.util.ArrayList;

/**
[구현]

문제요약 :
S,D,T 는 각 1제곱, 2제곱, 3제곱
* 스타상 : 현재 점수와, 직전점수 * 2
# 아차상 : 현재 점수를 반전

 <solution>
 먼저 숫자와 문자를 분리하는 작업 필요
 분리 후 다음 SDT를 확인해서 점수를 계산하고
 옵션 확인해서 구현

 분리작업 수행 시 문자열 길이 확인하면서 마지막에 널포인트 나지않게 조심
 옵션 적용 시 스타상의 경우 처음 점수에 대해 유의
 </solution>

 */

class Solution_다트게임_level1 {
    private ArrayList<Score> scores;
    private ArrayList<Integer> result;
    public int solution(String dartResult) {
        int answer = 0;

        scores = new ArrayList<Score>();
        result = new ArrayList<>();

        splitResult(dartResult);
        for(int i=0; i<scores.size(); i++){
            Score score = scores.get(i);

            result.add(score.score);
            if(score.option == '#') result.set(i, -score.score);
            else if(score.option == '*'){
                if(i>0) result.set(i-1, result.get(i-1) * 2);
                result.set(i, result.get(i) * 2);
            }
        }
        for(int score : result) answer += score;
        return answer;
    }

    private void splitResult(String dartResult) {

        int index = 0;
        while(index < dartResult.length()){
            String score = "";
            while(isNumber(dartResult.charAt(index)))
                score += dartResult.charAt(index++);
            char bonus = dartResult.charAt(index++);
            char option = 'x';
            if(index < dartResult.length() && !isNumber(dartResult.charAt(index))){
                option = dartResult.charAt(index++);
            }
            int total = Integer.parseInt(score);
            if(bonus == 'D') total = (int) Math.pow(Double.parseDouble(score), 2);
            else if(bonus == 'T') total = (int) Math.pow(Double.parseDouble(score), 3);
            scores.add(new Score(total, option));

        }
    }

    private boolean isNumber(char c){
        if('0' <= c && c <= '9') return true;
        else return false;
    }

    static class Score {
        int score;
        char option;

        public Score(int score, char option) {
            this.score = score;
            this.option = option;
        }

    }

}