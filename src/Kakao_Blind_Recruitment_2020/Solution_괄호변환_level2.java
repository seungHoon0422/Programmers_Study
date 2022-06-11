package Kakao_Blind_Recruitment_2020;

import java.util.Stack;

/**
 [구현, 스택]

<solution>
 올바른 괄호문자열 변환 과정을 맞춰서 그대로 구현하면 되는 문제
 올바른 괄호 문자열인지 확인하는 방법에서 stack 사용
 ( 문자가 들어오면 스택에 넣어주고, ) 문자가 들어오면 stack에 (가 있는 경우에 pop
 left, right 변수를 사용해서 두 괄호의 개수가 맞는지 체크를 통해
 균형잡힌 문자열인지 확인하고, 균형잡힌 문자열일 경우 stack이 비어있는지를 확인해
 올바른 괄호 문자열인지 판단
 </solution>

 */
class Solution_괄호변환_level2 {
    public String solution(String p) {
        String answer = "";

        return splitString(p);
    }

    private String splitString(String p) {

        if(p.length() == 0) return "";

        String u="", v="";
        int left = 0, right = 0;
        Stack<Character> stack = new Stack<>();
        int index=0;
        for(; index<p.length(); index++){
            char ch = p.charAt(index);
            if(ch=='(') {
                stack.push(ch);
                left++;
            }
            else {
                if(!stack.isEmpty() && stack.peek()=='(') stack.pop();
                right++;
            }
            if(left == right) break;
        }

        u = p.substring(0, index+1);
        v = p.substring(index+1);
        if(stack.isEmpty()) {
            // 올바른 문자열일 경우
            return u + splitString(v);
        } else {
            StringBuilder sb = new StringBuilder();
            for(int i=1; i<u.length()-1; i++)
                if(u.charAt(i)=='(') sb.append(')');
                else sb.append('(');
            return "(" + splitString(v) + ")" + sb.toString();
        }




    }
}