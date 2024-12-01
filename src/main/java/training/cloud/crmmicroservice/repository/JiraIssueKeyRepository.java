package training.cloud.crmmicroservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.cloud.crmmicroservice.model.JiraIssueKey;

@Repository
public interface JiraIssueKeyRepository extends JpaRepository<JiraIssueKey, Long> {
    Page<JiraIssueKey> findAll(Pageable pageable);
}
