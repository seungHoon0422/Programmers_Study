package Kakao_Internship_2019;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * <problem>
 *     문제요약 : 정확성 + 효율성
 *     순서대로 입력받은 호텔 방배정
 *     앞번호부터 원하는 방이 비어있으면 바로 배정
 *     방이 차있으면 가장 가까운 높은 방에 배정
 *     {방을 배정하지 못하는 경우는 입력 x}
 *
 * </problem>
 *
 * <solution>
 *     가장큰 문제는 가장 가까운 방을 어떻게 찾는가
 *     이것도 하나씩 확인하면서 진행하면 시간초과 예상
 *     linked list 사용하자!!
 *
 * </solution>
 */
class Solution_호텔방배정_level4 {
    long[] answer;
    ArrayList<Long> next, prev;
    HashMap<Long, Long> nextRoom;
    public long[] solution(long k, long[] room_number) {
        answer = new long[room_number.length];
        nextRoom = new HashMap<>();

        for(int index=0; index< room_number.length; index++){
            answer[index] = find(room_number[index]);
        }


        return answer;
    }

    private long find(long room) {
        if(!nextRoom.containsKey(room)){
            nextRoom.put(room, room+1);
            return room;
        }
        long next = nextRoom.get(room);
        long empty = find(next);
        nextRoom.put(room, empty);
        return empty;
    }


}