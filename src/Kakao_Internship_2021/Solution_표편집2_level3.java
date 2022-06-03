package Kakao_Internship_2021;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * [구현, 자료구조]
 *
 * <porblem>
 * 표의 행을 선택, 삭제, 복구 프로그램 작성
 * command list
 *  U X : 현재 선택된 행에서 X칸 위에있는 행을 선택
 *  D X : 현재 선택된 행에서 X칸 밑의 행을 선택
 *  C : 현재 선택된 행을 삭제 후 바로 아래행 선택, 삭제된 행이 가장 마지막행인 경우 바로 윗행 선택
 *  Z : 가장 최근에 삭제된 행을 원래대로 복구 -> 단 현재 선택된 행은 바뀌지 않는다.
 * </porblem>
 *
 * <solution>
 * 가장 중요한 커맨드는 Z, C
 * command C
 * 삭제하기 전에 index, id를 리스트에 저장한다 => 복구 작업위해
 * 마지막행을 삭제할 때에는 인덱스를 가장 마지막 인덱스에 위치
 *
 * command Z
 * 복구작업을 하기 위해서 가장 최근에 삭제한 id 확인
 * 아무것도 없으면 무시
 * 복구할 인덱스를 어떻게 찾을까???
 * 직전에 삭제된 아이디를 추가할때에는 해당 위치에 삽입하는 작어
 * 현재 선택된 항이 수정되지 않는것 주의!!
 * 현재 인덱스보다 뒤에 추가하게 되는경우에는 문제없지만
 * 현재 인덱스보다 앞에 삽입하는 경우에는 인덱스를 1개 미뤄줘야한다.
 * </solution>
 *
 *
 * D는 행증가, U은 행감소..... => 이걸 거꾸로 하고있었음...
 * 하지만 런타임에러, 시간초과
 *
 */
class Solution_표편집2_level3 {

    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        int table = n;

        for (String s : cmd) {

            StringTokenizer st = new StringTokenizer(s, " ");
            String command = st.nextToken();
            switch (command) {
                case "U":
                    k -= Integer.parseInt(st.nextToken());
                    break;

                case "D":
                    k += Integer.parseInt(st.nextToken());
                    break;

                case "C" :
                    stack.add(k);
                    table--;
                    if(table == k) k--;
                    break;

                case "Z":
                    if(stack.pop()<=k) k++;
                    table++;
                    break;

            }
        }
        for(int i=0; i<table; i++)
            answer.append("O");
        while(!stack.isEmpty()) {
            answer.insert(stack.pop(), "X");
        }
        return answer.toString();
    }

}




