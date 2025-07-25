package pl.mational.GithubReposSeeker.entity;

import java.util.List;

public class RepoInfo {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchInfo> branches;

    public RepoInfo() {
    }

    public RepoInfo(String repositoryName, String ownerLogin, List<BranchInfo> branches) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<BranchInfo> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchInfo> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "RepoInfo{" +
                "repositoryName='" + repositoryName + '\'' +
                ", ownerLogin='" + ownerLogin + '\'' +
                ", branches=" + branches +
                '}';
    }
}


