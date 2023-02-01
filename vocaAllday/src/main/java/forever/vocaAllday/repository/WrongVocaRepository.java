package forever.vocaAllday.repository;

import forever.vocaAllday.entity.Report;
import forever.vocaAllday.entity.WrongVoca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WrongVocaRepository extends JpaRepository<WrongVoca, Long> {
    @Modifying
    @Query("update WrongVoca set meaning = :meaning where wrong_id = :wrong_id")
    void updateMeaning(@Param("wrong_id") Long id, @Param("meaning") String meaning);
    @Modifying
    @Query("update WrongVoca set word = :word where wrong_id = :wrong_id")
    void updateWord(@Param("wrong_id") Long id, @Param("word") String word);
}
