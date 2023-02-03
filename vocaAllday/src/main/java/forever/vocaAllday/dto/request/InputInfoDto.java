package forever.vocaAllday.dto.request;

import forever.vocaAllday.entity.InputVoca;
import forever.vocaAllday.enums.ExamType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InputInfoDto {
    private List<String> word;
    private List<String> meaning;
    private String vocaTitle;
    private ExamType examType; // 시험 유형

    public InputVoca createInputVoca(String word, String meaning) {
        InputVoca inputVoca = new InputVoca(word, meaning);
        return inputVoca;
    }


}
