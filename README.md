# Github Repositories Seeker

A simple Spring Boot application that provides a REST API to fetch non-fork public repositories of a given GitHub user. For each repository, it also lists all branches along with the latest commit SHA.

This project uses the official [GitHub REST API v3](https://developer.github.com/v3) as the data source.

---

## Features

- Lists **non-fork** public repositories of a specified GitHub user.
- For each repository, returns:
  - Repository name
  - Owner login
  - List of branches, each with:
    - Branch name
    - Last commit SHA
- Returns a **404 error** for non-existent GitHub users.

---

## Technologies Used

- Java 21
- Spring Boot 3.5
- Java HTTP Client (`java.net.http`)
- Jackson (JSON mapping)
- JUnit 5
- Maven

---

## API Endpoint

### `GET /api/users/{username}/repos`

Fetches all non-fork repositories of the given GitHub username.

#### 🔹 Successful response (`200 OK`)

```json
[
  {
    "repositoryName": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "branchName": "main",
        "lastCommitSha": "7fd1a60b01f91b314f599a5d43ff02e22464c65d"
      },
      {
        "branchName": "dev",
        "lastCommitSha": "3b1a6f1a9f23de3e8e84a207dfd67d2d3f2c5567"
      }
    ]
  }
]
```

#### 🔸 Not Found (`404 Not Found`)

```json
{
  "status": 404,
  "message": "Exception message"
}
```

---

## Running the Application

Make sure you have Java 21 and Maven installed.

### Build and run:

```bash
./mvnw clean spring-boot:run
```

### API available at:

```
http://localhost:8080/api/users/{username}/repos
```
