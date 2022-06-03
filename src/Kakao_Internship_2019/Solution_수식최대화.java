package Kakao_Internship_2019;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [순열, 문자열 파싱]
 * 
 * <solution>
 *     연사자와 피연산자를 구분하여 저장하는 과정에서 시간이 오래걸림
 *     string tokenizer를 사용해서 시도했으나 
 *     투포인터를 사용해서 연산자인지 확인하면서 저장
 *     
 *     연산자가 3개밖에 없으므로 나올 수 있는 종류는 총 6가지
 *     따라서 순열코드를 작성하지 않고, hash형식으로 저장한다음 실행
 *     
 *     연산자 index를 실행할 때 피연산자 리스트에서는 index, index+1에 해당
 *     index위치에 연산 결과를 저장시키고, index+1 요소 삭제
 *     
 * </solution>
 */
class Solution_수식최대화 {
    private StringTokenizer st;
    private ArrayList<Character> op;
    private ArrayList<Long> val;
    private char[][] sequence;
    long answer;

    public long solution(String expression) {

        answer = 0;
        op = new ArrayList<Character>();
        val = new ArrayList<Long>();
        StringBuilder sb = new StringBuilder();

        int index = 0;
        while(index < expression.length()){
            if(isOperator(index, expression)){
                op.add(expression.charAt(index));
                val.add(Long.valueOf(sb.toString()));
                sb = new StringBuilder();
            } else {
                sb.append(expression.charAt(index));
            }
            index++;
        }
        val.add(Long.valueOf(sb.toString()));

        sequence = new char[][]{
                {'+', '-', '*'},
                {'+', '*', '-'},
                {'-', '+', '*'},
                {'-', '*', '+'},
                {'*', '+', '-'},
                {'*', '-', '+'}
        };

        for(int i=0; i<sequence.length; i++){
            int cnt = 0;
            ArrayList<Long> list = new ArrayList<>();
            ArrayList<Character> oper = new ArrayList<>();
            for(Long l : val) list.add(l);
            for(char c : op) oper.add(c);

            operate(list, oper, cnt, sequence[i]);
        }

        return answer;
    }

    private void operate(ArrayList<Long> value, ArrayList<Character> oper, int cnt, char[] sequence) {
        if(cnt == 3){
            answer = Math.max(answer, Math.abs(value.get(0)));
            return;
        }

        char operator = sequence[cnt];
        int index = 0;
        while(index < oper.size()){
            if(oper.get(index) != operator) index++;
            else {
                switch (operator){
                    case '+' :
                        value.set(index, value.get(index) + value.get(index+1));
                        break;
                    case '-' :
                        value.set(index, value.get(index) - value.get(index+1));
                        break;
                    case '*' :
                        value.set(index, value.get(index) * value.get(index+1));
                        break;

                }
                value.remove(index+1);
                oper.remove(index);
            }
        }
        operate(value, oper, cnt+1, sequence);
    }

    boolean isOperator(int end, String expression) {
        if (expression.charAt(end) == '+' || expression.charAt(end) == '-' || expression.charAt(end) == '*'){
            return true;
        } else
            return false;
    }
}