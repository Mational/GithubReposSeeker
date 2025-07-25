package pl.mational.GithubReposSeeker.service;

import pl.mational.GithubReposSeeker.entity.RepoInfo;

import java.util.List;

public interface GithubUserService {
    List<RepoInfo> getUserRepos(String username);
}
