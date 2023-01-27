package forever.vocaAllday.dto;

import forever.vocaAllday.entity.InputVoca;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class InputVocaDto {

    private String word;
    private String meaning;
    private static ModelMapper modelMapper = new ModelMapper();
    public InputVoca createInputVoca() {
        return modelMapper.map(this, InputVoca.class);
    }

    public static InputVocaDto of(InputVoca InputVoca) {
        return modelMapper.map(InputVoca, InputVocaDto.class);
    }
}