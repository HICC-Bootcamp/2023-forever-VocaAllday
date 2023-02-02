package forever.vocaAllday.repository;

import forever.vocaAllday.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long> {
    @Query("select r from Report r where member_id = :member_id and vocaTitle = :vocaTitle")
    Report findByReport(@Param("member_id") Long id, @Param("vocaTitle") String vocaTitle);

    @Query("select voca_title from Report r,Member m where r.member_id = m.member_id and m.email = :email")
    List<String> findByVocaTitles(@Param("email") String email);

}