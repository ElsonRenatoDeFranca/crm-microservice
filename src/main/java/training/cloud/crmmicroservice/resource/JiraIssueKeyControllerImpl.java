package training.cloud.crmmicroservice.resource;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import training.cloud.crmmicroservice.exception.CustomerMismatchException;
import training.cloud.crmmicroservice.model.JiraIssueKey;
import training.cloud.crmmicroservice.service.JiraIssueKeyService;

@RestController
@AllArgsConstructor
public class JiraIssueKeyControllerImpl implements JiraIssueKeyController {

    private static final Logger log = LoggerFactory.getLogger(JiraIssueKeyControllerImpl.class);
    private final JiraIssueKeyService jiraIssueKeyService;

    @Override
    public ResponseEntity<Void> save(Integer totalItems, Integer chunkSize) {
        try {
            jiraIssueKeyService.generateKeys(totalItems);
            jiraIssueKeyService.saveLargeDataSet(totalItems, chunkSize);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CustomerMismatchException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public Page<JiraIssueKey> findAll(int page, int size) {
        log.info("Searching all issues ");
        Pageable pageable = PageRequest.of(page, size);

        return jiraIssueKeyService.findAll(pageable);
    }

    @Override
    public ResponseEntity<Void> deleteAll() {
        jiraIssueKeyService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
