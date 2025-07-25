package pl.mational.GithubReposSeeker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mational.GithubReposSeeker.entity.RepoInfo;
import pl.mational.GithubReposSeeker.service.GithubUserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GithubUserController {
    private final GithubUserService githubUserService;

    @Autowired
    public GithubUserController(GithubUserService theGithubUserService) {
        this.githubUserService = theGithubUserService;
    }

    @GetMapping("/users/{username}/repos")
    public List<RepoInfo> getUserRepos(@PathVariable String username) {
        return githubUserService.getUserRepos(username);
    }
}
