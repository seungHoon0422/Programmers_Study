package Kakao_Blind_Recruitment_2021;

/**
 * <problem>
 * [문자열, 구현]
 * 주어진 규칙대로 입력된 id의 수정 진행
 *
 * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
 * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
 * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
 * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
 * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
 * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
 *      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
 * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 * </problem>
 *
 * 
 * 
 */
class Solution_신규아이디추천_level1 {
    public String solution(String id) {
        String answer = "";

        // 1단계 : 모든 대문자를 소문자로 치환
        id = id.toLowerCase();
        
        // 2단계 : 알파벳, 숫자, 빼기, 밑줄, 마침표를 제외한 모든 문자를 A로 치환
        // 모든 대문자를 소문자로 치환하였기 때문에 대문자 A로 치환
        for(int i=0; i<id.length(); i++){
            char c = id.charAt(i);
            if('a'<=c && c<='z') continue;
            else if('0'<=c && c<='9') continue;
            else if(c=='-' || c=='_' || c=='.') continue;
            id = id.replace(c, 'A');
        }
        // 'A' 제거
        id = id.replaceAll("A", "");
        // 3단계 : 연속된 . 제거, 3개이상 .이 붙어있는 경우도 존재하므로 없을때까지 진행
        while(id.contains(".."))
            id = id.replace("..", ".");
        
        // 4단계 : .이 처음 위치에 있는 경우, 빈문자열 여부도 같이 체크
        if(id.length() > 0 && id.charAt(0) == '.') id = id.substring(1);
        // 4단계 : .이 마지막 위치에 있는 경우, 빈문자열인지 체크
        if(id.length() > 0 && id.charAt(id.length()-1) == '.') id = id.substring(0, id.length()-1);
        // 5단계 : 빈문자열일 경우 'a' 추가
        if(id.length()  == 0 ) id = "a";
        // 6단계 : 문자열 길이가 16이상이라면 15개의 문자만 남기고 제거
        if(id.length()>=16) id = id.substring(0, 15);
        // 6단계 : 제거 후 마지막 문자가 .일 경우 마지막 문자 제거
        if(id.charAt(id.length()-1) == '.') id = id.substring(0, id.length()-1);
        // 7단계 : 길이가 2이상이 되도록 마지막 문자 반복하여 추가
        char lastch = id.charAt(id.length()-1);
        while(id.length() < 3) {
            id += lastch;
        }
        return id;
    }
}