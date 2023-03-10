package forever.vocaAllday.dto.request;

import forever.vocaAllday.enums.ExamType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageDto {
    private String vocaTitle;
    private ExamType examType;
}