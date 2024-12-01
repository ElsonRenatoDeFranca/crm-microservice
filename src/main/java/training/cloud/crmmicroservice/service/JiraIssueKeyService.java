package training.cloud.crmmicroservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.cloud.crmmicroservice.model.JiraIssueKey;
import training.cloud.crmmicroservice.repository.JiraIssueKeyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class JiraIssueKeyService {

    private JiraIssueKeyRepository repository;

    public List<JiraIssueKey> generateKeys(int totalItems) {
        List<JiraIssueKey> listOfKeys = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
            JiraIssueKey jiraIssueKey = buildJiraIssueKey("JIRA_KEY_" + i);
            listOfKeys.add(jiraIssueKey);
        }
        return listOfKeys;
    }

    private JiraIssueKey buildJiraIssueKey(String value) {
        JiraIssueKey jiraIssueKey = new JiraIssueKey();
        jiraIssueKey.setValue(value);
        return jiraIssueKey;
    }

    @Transactional
    public void saveLargeDataSet(Integer totalItems, Integer chunkSize) {
        List<JiraIssueKey> issues = generateKeys(totalItems);

        for (int i = 0; i < issues.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, issues.size());
            List<JiraIssueKey> subBatch = issues.subList(i, end);
            repository.saveAllAndFlush(subBatch);
            log.info("Saving item from {} to {} ", i, end);
        }
    }

    @Transactional
    public void streamAndInsert(Stream<String> jiraKeysStream, int batchSize) {

        List<JiraIssueKey> batch = new ArrayList<>();

        jiraKeysStream.forEach(jiraKey -> {
            JiraIssueKey issueKey = buildJiraIssueKey(jiraKey);
            batch.add(issueKey);

            //Insert when batch size is reached
            if (batch.size() == batchSize) {
                repository.saveAllAndFlush(batch);
                batch.clear(); //clear the batch for the next set
            }
        });

        //Insert the remaining items
        if (!batch.isEmpty()) {
            repository.saveAllAndFlush(batch);
        }
    }

    public Page<JiraIssueKey> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }

}
