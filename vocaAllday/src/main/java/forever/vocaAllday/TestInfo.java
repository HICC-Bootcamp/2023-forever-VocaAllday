package forever.vocaAllday;

import forever.vocaAllday.enums.TestType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TestInfo {
    private TestType testType; // 시험 유형
    private String numOfQuestions;// 단어 당 문제개수
    private String vocaTitle; // 시험지를 만들 단어장 이름


}
