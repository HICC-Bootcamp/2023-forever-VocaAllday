package forever.vocaAllday.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "wrong_voca")
@Getter
@ToString
public class WrongVoca {

    @Id
    @Column(name = "wrong_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wrongId; // WrongVoca ID

    @Lob
    @Column(nullable = false)
    private String word; // 틀린 영어 단어

    @Lob
    @Column(nullable = false)
    private String meaning; // 틀린 영어 단어 뜻


}
