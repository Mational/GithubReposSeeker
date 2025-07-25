package pl.mational.GithubReposSeeker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.mational.GithubReposSeeker.entity.BranchInfo;
import pl.mational.GithubReposSeeker.entity.RepoInfo;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubUserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnNonForkReposWithBranchesForUser() {
        // Given
        String username = "octocat";

        // When
        int port = 8080;
        String url = "http://localhost:" + port + "/api/users/" + username + "/repos";
        RepoInfo[] response = restTemplate.getForObject(url, RepoInfo[].class);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.length).isGreaterThan(0);

        for (RepoInfo repo : response) {
            assertThat(repo.getRepositoryName()).isNotEmpty();
            assertThat(repo.getOwnerLogin()).isEqualToIgnoringCase(username);
            assertThat(repo.getBranches()).isNotNull().isNotEmpty();

            for (BranchInfo branch : repo.getBranches()) {
                assertThat(branch.getBranchName()).isNotEmpty();
                assertThat(branch.getLastCommitSha()).matches("^[a-f0-9]{40}$"); // SHA-1 format
            }
        }
    }
}
