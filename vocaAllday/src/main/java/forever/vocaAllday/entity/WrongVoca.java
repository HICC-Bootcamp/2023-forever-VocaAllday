package forever.vocaAllday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "wrong_voca")
@Getter
@ToString
@NoArgsConstructor
public class WrongVoca {

    @Id
    @Column(name = "wrong_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wrongId; // WrongVoca ID

    @Lob
    private String word; // 틀린 영어 단어

    @Lob
    private String meaning; // 틀린 영어 단어 뜻

    public WrongVoca(String word,String meaning){
        this.word=word;
        this.meaning=meaning;
    }



}
