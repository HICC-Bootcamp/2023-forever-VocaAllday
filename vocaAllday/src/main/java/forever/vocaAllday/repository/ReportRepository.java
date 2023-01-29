package forever.vocaAllday.repository;

import forever.vocaAllday.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report,Long> {
    @Query("select r from Report r where member_id = :member_id and vocaTitle = :vocaTitle")
    Report findByReport(@Param("member_id") Long id, @Param("vocaTitle") String vocaTitle);

}