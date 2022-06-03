import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
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