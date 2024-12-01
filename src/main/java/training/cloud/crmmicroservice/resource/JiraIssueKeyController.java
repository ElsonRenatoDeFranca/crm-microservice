package training.cloud.crmmicroservice.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import training.cloud.crmmicroservice.model.CustomerDto;
import training.cloud.crmmicroservice.model.JiraIssueKey;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/crm")
public interface JiraIssueKeyController {
    @PostMapping(value = "/jira-issue-keys", produces = {APPLICATION_JSON_VALUE})
    @Operation(summary = "Save a jira issue key to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Save a jira issue key to database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "406",
                    description = "The jira issue key is already at database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> save(Integer totalItems, Integer chunkSize);


    @GetMapping(value = "/issues", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Find all jira issue keys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find all jira issue keys",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    Page<JiraIssueKey> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "200") int size
    );


    @DeleteMapping(value = "/clear", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "Delete all jira issue keys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete all jira issue keys",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<Void> deleteAll();

}
