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
 *
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
 * </porblem>
 *
 * <solution>
 *     double linked list 구현
 *     node 객체 배열을 생성해서 prev, next 연결
 *     removed 변수를 사용해서 결과 출력할 때 사용
 *     최근 삭제된 노드부터 가져와야하므로 stack
 *     head, tail 작업은 모든 링크과정에서 null을 잡아주며 해결
 *     cursor는 각 prev, next의 id값을 통해서 배열 인덱스로 이동
 *
 *     C작업을 할때 기존에 지울 노드의 링크를 다끊지않고 남겨두어서
 *     Z작업을 할때 편하게
 * </solution>
 *
 */
class Solution_표편집 {

    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();

        Node[] table = new Node[n];
        for(int i=0; i<n; i++) table[i] = new Node();
        Stack<Integer> stack = new Stack<>();


        // head setting
        table[0].prev = null;
        table[0].next = table[1];
        table[0].removed = false;
        table[0].id = 0;

        // table setting
        for(int i=1; i<n; i++){
            table[i].next = null;
            table[i].prev = table[i-1];
            table[i-1].next = table[i];
            table[i].removed = false;
            table[i].id = i;
        }

        // command start
        for( String s : cmd) {
            StringTokenizer st = new StringTokenizer(s);
            String command = st.nextToken();

            int move;
            switch(command) {
                case "D":
                    move = Integer.parseInt(st.nextToken());
                    // cursor 이동, DOWN은 next로 이동
                    while(move-- > 0){
                        k = table[k].next.id;
                    }
                    break;

                case "U":
                    move = Integer.parseInt(st.nextToken());
                    // cursor 이동, UP은 prev로 이동
                    while(move-- > 0){
                        k = table[k].prev.id;
                    }
                    break;

                case "C":
                    // stack에 지운 인덱스 저장
                    stack.add(k);
                    table[k].removed = true;

                    // prev와 next를 연결
                    if(table[k].prev != null) table[k].prev.next = table[k].next;
                    if(table[k].next != null) table[k].next.prev = table[k].prev;

                    // 커서 이동 [마지막행 고려]
                    if(table[k].next == null) k = table[k].prev.id;
                    else k = table[k].next.id;

                    break;

                case "Z":

                    int recover = stack.pop();
                    table[recover].removed = false;
                    // 기존에 연결되어있던 prev, next를 자신으로 연결
                    if(table[recover].prev != null) table[recover].prev.next = table[recover];
                    if(table[recover].next != null) table[recover].next.prev = table[recover];
                    // 특별한 커서이동 필요없음, 어차피 자기자신가리키고 있음
                    break;

            }
        }

        for(int i=0; i<n; i++){
            if(table[i].removed) answer.append("X");
            else answer.append("O");
        }

        return answer.toString();
    }

    static class Node {
        Node next, prev;
        int id;
        Boolean removed;
    }
}




