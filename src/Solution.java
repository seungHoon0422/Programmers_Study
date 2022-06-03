import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
    private StringTokenizer st;
    private ArrayList<Character> op;
    private ArrayList<Long> val;

    public long solution(String expression) {
        long answer = 0;

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
        for(Character c : op) System.out.println(c);
        for(Long l : val) System.out.println(l);


        return answer;
    }

    boolean isOperator(int end, String expression) {
        if (expression.charAt(end) == '+' || expression.charAt(end) == '-' || expression.charAt(end) == '*'){
            return true;
        } else
            return false;
    }
}