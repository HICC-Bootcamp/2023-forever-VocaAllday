package forever.vocaAllday.dto;

import forever.vocaAllday.entity.InputVoca;
import forever.vocaAllday.enums.TestType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputInfoDto {
    private String[] word;
    private String[] meaning;
    private String vocaTitle;
    private TestType testType; // 시험 유형

    public InputVoca createInputVoca(String word, String meaning) {
        InputVoca inputVoca = new InputVoca(word, meaning);
        return inputVoca;
    }


}
