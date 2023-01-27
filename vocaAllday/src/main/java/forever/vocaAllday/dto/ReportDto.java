package forever.vocaAllday.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReportDto {

    @NotNull(message = "단어장 이름은 필수 입력값입니다.")
    private String vocaTitle; //단어장 이름

}