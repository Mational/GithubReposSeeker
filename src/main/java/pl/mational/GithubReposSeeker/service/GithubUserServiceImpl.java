package pl.mational.GithubReposSeeker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.mational.GithubReposSeeker.entity.BranchInfo;
import pl.mational.GithubReposSeeker.entity.RepoInfo;
import pl.mational.GithubReposSeeker.rest.UserNotFoundException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GithubUserServiceImpl implements GithubUserService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<RepoInfo> getUserRepos(String username) {
        List<RepoInfo> result = new ArrayList<>();

        try {
            HttpRequest repoRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/" + username + "/repos"))
                    .header("Accept", "application/vnd.github+json")
                    .build();

            HttpResponse<String> repoResponse = httpClient.send(repoRequest, HttpResponse.BodyHandlers.ofString());

            List<Map<String, Object>> repos = objectMapper.readValue(
                    repoResponse.body(), new TypeReference<>() {});

            for (Map<String, Object> repo : repos) {
                String repoName = (String) repo.get("name");
                String ownerLogin = (String) ((Map<?, ?>) repo.get("owner")).get("login");
                Boolean isFork = (Boolean) repo.get("fork");

                if (isFork) continue;

                String branchesUrl = "https://api.github.com/repos/" + ownerLogin + "/" + repoName + "/branches";

                HttpRequest branchRequest = HttpRequest.newBuilder()
                        .uri(URI.create(branchesUrl))
                        .header("Accept", "application/vnd.github+json")
                        .build();

                HttpResponse<String> branchResponse = httpClient.send(branchRequest, HttpResponse.BodyHandlers.ofString());

                List<Map<String, Object>> branches = objectMapper.readValue(
                        branchResponse.body(), new TypeReference<>() {});

                List<BranchInfo> branchInfos = new ArrayList<>();

                for (Map<String, Object> branch : branches) {
                    String branchName = (String) branch.get("name");
                    String commitSha = (String) ((Map<?, ?>) branch.get("commit")).get("sha");

                    branchInfos.add(new BranchInfo(branchName, commitSha));
                }

                RepoInfo repoInfo = new RepoInfo(repoName, ownerLogin, branchInfos);
                result.add(repoInfo);
            }

        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }

        return result;
    }
}
