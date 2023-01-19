package forever.vocaAllday.repository;

import forever.vocaAllday.entity.VocaNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocaNoteRepository extends JpaRepository<VocaNote,Long> {
}
