package com.meskitah.githublist.data.remote

val validPullRequestListResponse = """
   [
     {
       "url": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls/2407",
       "id": 1894351064,
       "node_id": "PR_kwDOB-U_C85w6YDY",
       "html_url": "https://github.com/Snailclimb/JavaGuide/pull/2407",
       "diff_url": "https://github.com/Snailclimb/JavaGuide/pull/2407.diff",
       "patch_url": "https://github.com/Snailclimb/JavaGuide/pull/2407.patch",
       "issue_url": "https://api.github.com/repos/Snailclimb/JavaGuide/issues/2407",
       "number": 2407,
       "state": "open",
       "locked": false,
       "title": "Update unsafe.md",
       "user": {
         "login": "uuanqin",
         "id": 61202052,
         "node_id": "MDQ6VXNlcjYxMjAyMDUy",
         "avatar_url": "https://avatars.githubusercontent.com/u/61202052?v=4",
         "gravatar_id": "",
         "url": "https://api.github.com/users/uuanqin",
         "html_url": "https://github.com/uuanqin",
         "followers_url": "https://api.github.com/users/uuanqin/followers",
         "following_url": "https://api.github.com/users/uuanqin/following{/other_user}",
         "gists_url": "https://api.github.com/users/uuanqin/gists{/gist_id}",
         "starred_url": "https://api.github.com/users/uuanqin/starred{/owner}{/repo}",
         "subscriptions_url": "https://api.github.com/users/uuanqin/subscriptions",
         "organizations_url": "https://api.github.com/users/uuanqin/orgs",
         "repos_url": "https://api.github.com/users/uuanqin/repos",
         "events_url": "https://api.github.com/users/uuanqin/events{/privacy}",
         "received_events_url": "https://api.github.com/users/uuanqin/received_events",
         "type": "User",
         "site_admin": false
       },
       "body": "fix typo：显示 -\u003E 显式",
       "created_at": "2024-05-29T17:04:52Z",
       "updated_at": "2024-05-29T17:04:52Z",
       "closed_at": null,
       "merged_at": null,
       "merge_commit_sha": "9af407bc4d9de48645ac4d9ad011ffdd35fa5000",
       "assignee": null,
       "assignees": [],
       "requested_reviewers": [],
       "requested_teams": [],
       "labels": [],
       "milestone": null,
       "draft": false,
       "commits_url": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls/2407/commits",
       "review_comments_url": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls/2407/comments",
       "review_comment_url": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls/comments{/number}",
       "comments_url": "https://api.github.com/repos/Snailclimb/JavaGuide/issues/2407/comments",
       "statuses_url": "https://api.github.com/repos/Snailclimb/JavaGuide/statuses/2e9e3fe193730c404d7b464579ce078d0473ee36",
       "head": {
         "label": "uuanqin:main",
         "ref": "main",
         "sha": "2e9e3fe193730c404d7b464579ce078d0473ee36",
         "user": {
           "login": "uuanqin",
           "id": 61202052,
           "node_id": "MDQ6VXNlcjYxMjAyMDUy",
           "avatar_url": "https://avatars.githubusercontent.com/u/61202052?v=4",
           "gravatar_id": "",
           "url": "https://api.github.com/users/uuanqin",
           "html_url": "https://github.com/uuanqin",
           "followers_url": "https://api.github.com/users/uuanqin/followers",
           "following_url": "https://api.github.com/users/uuanqin/following{/other_user}",
           "gists_url": "https://api.github.com/users/uuanqin/gists{/gist_id}",
           "starred_url": "https://api.github.com/users/uuanqin/starred{/owner}{/repo}",
           "subscriptions_url": "https://api.github.com/users/uuanqin/subscriptions",
           "organizations_url": "https://api.github.com/users/uuanqin/orgs",
           "repos_url": "https://api.github.com/users/uuanqin/repos",
           "events_url": "https://api.github.com/users/uuanqin/events{/privacy}",
           "received_events_url": "https://api.github.com/users/uuanqin/received_events",
           "type": "User",
           "site_admin": false
         },
         "repo": {
           "id": 807725363,
           "node_id": "R_kgDOMCTpMw",
           "name": "JavaGuide",
           "full_name": "uuanqin/JavaGuide",
           "private": false,
           "owner": {
             "login": "uuanqin",
             "id": 61202052,
             "node_id": "MDQ6VXNlcjYxMjAyMDUy",
             "avatar_url": "https://avatars.githubusercontent.com/u/61202052?v=4",
             "gravatar_id": "",
             "url": "https://api.github.com/users/uuanqin",
             "html_url": "https://github.com/uuanqin",
             "followers_url": "https://api.github.com/users/uuanqin/followers",
             "following_url": "https://api.github.com/users/uuanqin/following{/other_user}",
             "gists_url": "https://api.github.com/users/uuanqin/gists{/gist_id}",
             "starred_url": "https://api.github.com/users/uuanqin/starred{/owner}{/repo}",
             "subscriptions_url": "https://api.github.com/users/uuanqin/subscriptions",
             "organizations_url": "https://api.github.com/users/uuanqin/orgs",
             "repos_url": "https://api.github.com/users/uuanqin/repos",
             "events_url": "https://api.github.com/users/uuanqin/events{/privacy}",
             "received_events_url": "https://api.github.com/users/uuanqin/received_events",
             "type": "User",
             "site_admin": false
           },
           "html_url": "https://github.com/uuanqin/JavaGuide",
           "description": "「Java学习+面试指南」一份涵盖大部分 Java 程序员所需要掌握的核心知识。准备 Java 面试，首选 JavaGuide！",
           "fork": true,
           "url": "https://api.github.com/repos/uuanqin/JavaGuide",
           "forks_url": "https://api.github.com/repos/uuanqin/JavaGuide/forks",
           "keys_url": "https://api.github.com/repos/uuanqin/JavaGuide/keys{/key_id}",
           "collaborators_url": "https://api.github.com/repos/uuanqin/JavaGuide/collaborators{/collaborator}",
           "teams_url": "https://api.github.com/repos/uuanqin/JavaGuide/teams",
           "hooks_url": "https://api.github.com/repos/uuanqin/JavaGuide/hooks",
           "issue_events_url": "https://api.github.com/repos/uuanqin/JavaGuide/issues/events{/number}",
           "events_url": "https://api.github.com/repos/uuanqin/JavaGuide/events",
           "assignees_url": "https://api.github.com/repos/uuanqin/JavaGuide/assignees{/user}",
           "branches_url": "https://api.github.com/repos/uuanqin/JavaGuide/branches{/branch}",
           "tags_url": "https://api.github.com/repos/uuanqin/JavaGuide/tags",
           "blobs_url": "https://api.github.com/repos/uuanqin/JavaGuide/git/blobs{/sha}",
           "git_tags_url": "https://api.github.com/repos/uuanqin/JavaGuide/git/tags{/sha}",
           "git_refs_url": "https://api.github.com/repos/uuanqin/JavaGuide/git/refs{/sha}",
           "trees_url": "https://api.github.com/repos/uuanqin/JavaGuide/git/trees{/sha}",
           "statuses_url": "https://api.github.com/repos/uuanqin/JavaGuide/statuses/{sha}",
           "languages_url": "https://api.github.com/repos/uuanqin/JavaGuide/languages",
           "stargazers_url": "https://api.github.com/repos/uuanqin/JavaGuide/stargazers",
           "contributors_url": "https://api.github.com/repos/uuanqin/JavaGuide/contributors",
           "subscribers_url": "https://api.github.com/repos/uuanqin/JavaGuide/subscribers",
           "subscription_url": "https://api.github.com/repos/uuanqin/JavaGuide/subscription",
           "commits_url": "https://api.github.com/repos/uuanqin/JavaGuide/commits{/sha}",
           "git_commits_url": "https://api.github.com/repos/uuanqin/JavaGuide/git/commits{/sha}",
           "comments_url": "https://api.github.com/repos/uuanqin/JavaGuide/comments{/number}",
           "issue_comment_url": "https://api.github.com/repos/uuanqin/JavaGuide/issues/comments{/number}",
           "contents_url": "https://api.github.com/repos/uuanqin/JavaGuide/contents/{+path}",
           "compare_url": "https://api.github.com/repos/uuanqin/JavaGuide/compare/{base}...{head}",
           "merges_url": "https://api.github.com/repos/uuanqin/JavaGuide/merges",
           "archive_url": "https://api.github.com/repos/uuanqin/JavaGuide/{archive_format}{/ref}",
           "downloads_url": "https://api.github.com/repos/uuanqin/JavaGuide/downloads",
           "issues_url": "https://api.github.com/repos/uuanqin/JavaGuide/issues{/number}",
           "pulls_url": "https://api.github.com/repos/uuanqin/JavaGuide/pulls{/number}",
           "milestones_url": "https://api.github.com/repos/uuanqin/JavaGuide/milestones{/number}",
           "notifications_url": "https://api.github.com/repos/uuanqin/JavaGuide/notifications{?since,all,participating}",
           "labels_url": "https://api.github.com/repos/uuanqin/JavaGuide/labels{/name}",
           "releases_url": "https://api.github.com/repos/uuanqin/JavaGuide/releases{/id}",
           "deployments_url": "https://api.github.com/repos/uuanqin/JavaGuide/deployments",
           "created_at": "2024-05-29T16:49:06Z",
           "updated_at": "2024-05-29T16:59:16Z",
           "pushed_at": "2024-05-29T16:58:26Z",
           "git_url": "git://github.com/uuanqin/JavaGuide.git",
           "ssh_url": "git@github.com:uuanqin/JavaGuide.git",
           "clone_url": "https://github.com/uuanqin/JavaGuide.git",
           "svn_url": "https://github.com/uuanqin/JavaGuide",
           "homepage": "https://javaguide.cn",
           "size": 176066,
           "stargazers_count": 0,
           "watchers_count": 0,
           "language": "Java",
           "has_issues": false,
           "has_projects": true,
           "has_downloads": true,
           "has_wiki": true,
           "has_pages": false,
           "has_discussions": false,
           "forks_count": 0,
           "mirror_url": null,
           "archived": false,
           "disabled": false,
           "open_issues_count": 0,
           "license": {
             "key": "apache-2.0",
             "name": "Apache License 2.0",
             "spdx_id": "Apache-2.0",
             "url": "https://api.github.com/licenses/apache-2.0",
             "node_id": "MDc6TGljZW5zZTI="
           },
           "allow_forking": true,
           "is_template": false,
           "web_commit_signoff_required": false,
           "topics": [],
           "visibility": "public",
           "forks": 0,
           "open_issues": 0,
           "watchers": 0,
           "default_branch": "main"
         }
       },
       "base": {
         "label": "Snailclimb:main",
         "ref": "main",
         "sha": "a7c89a870289d0e5721b0876115c51e4a5f43502",
         "user": {
           "login": "Snailclimb",
           "id": 29880145,
           "node_id": "MDQ6VXNlcjI5ODgwMTQ1",
           "avatar_url": "https://avatars.githubusercontent.com/u/29880145?v=4",
           "gravatar_id": "",
           "url": "https://api.github.com/users/Snailclimb",
           "html_url": "https://github.com/Snailclimb",
           "followers_url": "https://api.github.com/users/Snailclimb/followers",
           "following_url": "https://api.github.com/users/Snailclimb/following{/other_user}",
           "gists_url": "https://api.github.com/users/Snailclimb/gists{/gist_id}",
           "starred_url": "https://api.github.com/users/Snailclimb/starred{/owner}{/repo}",
           "subscriptions_url": "https://api.github.com/users/Snailclimb/subscriptions",
           "organizations_url": "https://api.github.com/users/Snailclimb/orgs",
           "repos_url": "https://api.github.com/users/Snailclimb/repos",
           "events_url": "https://api.github.com/users/Snailclimb/events{/privacy}",
           "received_events_url": "https://api.github.com/users/Snailclimb/received_events",
           "type": "User",
           "site_admin": false
         },
         "repo": {
           "id": 132464395,
           "node_id": "MDEwOlJlcG9zaXRvcnkxMzI0NjQzOTU=",
           "name": "JavaGuide",
           "full_name": "Snailclimb/JavaGuide",
           "private": false,
           "owner": {
             "login": "Snailclimb",
             "id": 29880145,
             "node_id": "MDQ6VXNlcjI5ODgwMTQ1",
             "avatar_url": "https://avatars.githubusercontent.com/u/29880145?v=4",
             "gravatar_id": "",
             "url": "https://api.github.com/users/Snailclimb",
             "html_url": "https://github.com/Snailclimb",
             "followers_url": "https://api.github.com/users/Snailclimb/followers",
             "following_url": "https://api.github.com/users/Snailclimb/following{/other_user}",
             "gists_url": "https://api.github.com/users/Snailclimb/gists{/gist_id}",
             "starred_url": "https://api.github.com/users/Snailclimb/starred{/owner}{/repo}",
             "subscriptions_url": "https://api.github.com/users/Snailclimb/subscriptions",
             "organizations_url": "https://api.github.com/users/Snailclimb/orgs",
             "repos_url": "https://api.github.com/users/Snailclimb/repos",
             "events_url": "https://api.github.com/users/Snailclimb/events{/privacy}",
             "received_events_url": "https://api.github.com/users/Snailclimb/received_events",
             "type": "User",
             "site_admin": false
           },
           "html_url": "https://github.com/Snailclimb/JavaGuide",
           "description": "「Java学习+面试指南」一份涵盖大部分 Java 程序员所需要掌握的核心知识。准备 Java 面试，首选 JavaGuide！",
           "fork": false,
           "url": "https://api.github.com/repos/Snailclimb/JavaGuide",
           "forks_url": "https://api.github.com/repos/Snailclimb/JavaGuide/forks",
           "keys_url": "https://api.github.com/repos/Snailclimb/JavaGuide/keys{/key_id}",
           "collaborators_url": "https://api.github.com/repos/Snailclimb/JavaGuide/collaborators{/collaborator}",
           "teams_url": "https://api.github.com/repos/Snailclimb/JavaGuide/teams",
           "hooks_url": "https://api.github.com/repos/Snailclimb/JavaGuide/hooks",
           "issue_events_url": "https://api.github.com/repos/Snailclimb/JavaGuide/issues/events{/number}",
           "events_url": "https://api.github.com/repos/Snailclimb/JavaGuide/events",
           "assignees_url": "https://api.github.com/repos/Snailclimb/JavaGuide/assignees{/user}",
           "branches_url": "https://api.github.com/repos/Snailclimb/JavaGuide/branches{/branch}",
           "tags_url": "https://api.github.com/repos/Snailclimb/JavaGuide/tags",
           "blobs_url": "https://api.github.com/repos/Snailclimb/JavaGuide/git/blobs{/sha}",
           "git_tags_url": "https://api.github.com/repos/Snailclimb/JavaGuide/git/tags{/sha}",
           "git_refs_url": "https://api.github.com/repos/Snailclimb/JavaGuide/git/refs{/sha}",
           "trees_url": "https://api.github.com/repos/Snailclimb/JavaGuide/git/trees{/sha}",
           "statuses_url": "https://api.github.com/repos/Snailclimb/JavaGuide/statuses/{sha}",
           "languages_url": "https://api.github.com/repos/Snailclimb/JavaGuide/languages",
           "stargazers_url": "https://api.github.com/repos/Snailclimb/JavaGuide/stargazers",
           "contributors_url": "https://api.github.com/repos/Snailclimb/JavaGuide/contributors",
           "subscribers_url": "https://api.github.com/repos/Snailclimb/JavaGuide/subscribers",
           "subscription_url": "https://api.github.com/repos/Snailclimb/JavaGuide/subscription",
           "commits_url": "https://api.github.com/repos/Snailclimb/JavaGuide/commits{/sha}",
           "git_commits_url": "https://api.github.com/repos/Snailclimb/JavaGuide/git/commits{/sha}",
           "comments_url": "https://api.github.com/repos/Snailclimb/JavaGuide/comments{/number}",
           "issue_comment_url": "https://api.github.com/repos/Snailclimb/JavaGuide/issues/comments{/number}",
           "contents_url": "https://api.github.com/repos/Snailclimb/JavaGuide/contents/{+path}",
           "compare_url": "https://api.github.com/repos/Snailclimb/JavaGuide/compare/{base}...{head}",
           "merges_url": "https://api.github.com/repos/Snailclimb/JavaGuide/merges",
           "archive_url": "https://api.github.com/repos/Snailclimb/JavaGuide/{archive_format}{/ref}",
           "downloads_url": "https://api.github.com/repos/Snailclimb/JavaGuide/downloads",
           "issues_url": "https://api.github.com/repos/Snailclimb/JavaGuide/issues{/number}",
           "pulls_url": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls{/number}",
           "milestones_url": "https://api.github.com/repos/Snailclimb/JavaGuide/milestones{/number}",
           "notifications_url": "https://api.github.com/repos/Snailclimb/JavaGuide/notifications{?since,all,participating}",
           "labels_url": "https://api.github.com/repos/Snailclimb/JavaGuide/labels{/name}",
           "releases_url": "https://api.github.com/repos/Snailclimb/JavaGuide/releases{/id}",
           "deployments_url": "https://api.github.com/repos/Snailclimb/JavaGuide/deployments",
           "created_at": "2018-05-07T13:27:00Z",
           "updated_at": "2024-06-09T15:33:06Z",
           "pushed_at": "2024-06-06T09:31:19Z",
           "git_url": "git://github.com/Snailclimb/JavaGuide.git",
           "ssh_url": "git@github.com:Snailclimb/JavaGuide.git",
           "clone_url": "https://github.com/Snailclimb/JavaGuide.git",
           "svn_url": "https://github.com/Snailclimb/JavaGuide",
           "homepage": "https://javaguide.cn",
           "size": 176135,
           "stargazers_count": 144211,
           "watchers_count": 144211,
           "language": "Java",
           "has_issues": true,
           "has_projects": true,
           "has_downloads": true,
           "has_wiki": true,
           "has_pages": false,
           "has_discussions": true,
           "forks_count": 45353,
           "mirror_url": null,
           "archived": false,
           "disabled": false,
           "open_issues_count": 60,
           "license": {
             "key": "apache-2.0",
             "name": "Apache License 2.0",
             "spdx_id": "Apache-2.0",
             "url": "https://api.github.com/licenses/apache-2.0",
             "node_id": "MDc6TGljZW5zZTI="
           },
           "allow_forking": true,
           "is_template": false,
           "web_commit_signoff_required": false,
           "topics": [
             "algorithms",
             "interview",
             "java",
             "jvm",
             "mysql",
             "redis",
             "spring",
             "system",
             "system-design",
             "zookeeper"
           ],
           "visibility": "public",
           "forks": 45353,
           "open_issues": 60,
           "watchers": 144211,
           "default_branch": "main"
         }
       },
       "_links": {
         "self": {
           "href": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls/2407"
         },
         "html": {
           "href": "https://github.com/Snailclimb/JavaGuide/pull/2407"
         },
         "issue": {
           "href": "https://api.github.com/repos/Snailclimb/JavaGuide/issues/2407"
         },
         "comments": {
           "href": "https://api.github.com/repos/Snailclimb/JavaGuide/issues/2407/comments"
         },
         "review_comments": {
           "href": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls/2407/comments"
         },
         "review_comment": {
           "href": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls/comments{/number}"
         },
         "commits": {
           "href": "https://api.github.com/repos/Snailclimb/JavaGuide/pulls/2407/commits"
         },
         "statuses": {
           "href": "https://api.github.com/repos/Snailclimb/JavaGuide/statuses/2e9e3fe193730c404d7b464579ce078d0473ee36"
         }
       },
       "author_association": "NONE",
       "auto_merge": null,
       "active_lock_reason": null
     }
   ]
""".trimIndent()