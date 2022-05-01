# Git Manual

## Working on something new
1. Go to a common branch like `devel`
```
git switch devel
```

2. Checkout a new branch. It should be named something relevent, like the issue number (`issue-x`)
```
git checkout -b issue-x
```

3. Here you can do what you please. Make changes and work on stuff

4. Commit your changes as you work
```
git commit -m "commit message"
```

5. Once you are done working on the feature switch back to the common branch
```
git switch devel
```

6. Pull the changes made by others into the common branch
```
git pull
```

7. Merge your changes into the common branch
```
git merge issue-x
```

8. Push the changes made to devel
```
git push origin devel
```

9. Delete the feature branch
```
git branch -d issue-x
```

10. Done. Repeat the process



## Working on something new
This is assuming you already have a branch that you havn't deleted.

1. Go to a common branch like `devel`
```
git switch devel
```

2. Switch to a local branch, such as `issue-x`
```
git switch issue-x
```

3. Pull in all the changes made to the common branch
```
git merge devel
```

4. Now continue working on the branch

5. Commit your changes as you work
```
git commit -m "commit message"
```

6. Once you are done working on the feature switch back to the common branch
```
git switch devel
```

7. Pull the changes made by others into the common branch
```
git pull
```

8. Merge your changes into the common branch
```
git merge issue-x
```

9. Push the changes made to devel
```
git push origin devel
```

10. Delete the feature branch
```
git branch -d issue-x
```

11. Done. Repeat the process

## Setup
1. Set your identity:
```
git config --global user.name "Your Name"
git config --global user.email "Your Email"
```

2. Set your default branch name:
```
git config --global init.defaultBranch main
```

3. Set up some funky aliases that will make your log experience better:
```
git config --global alias.logg "log --graph --pretty=format:'%Cred%h%Creset %Cgreen(%cr) %C(bold blue)<%an>%Creset -%C(yellow)%d%Creset %s' --abbrev-commit"
git config --global alias.logg "log --graph --stat"
```

4. Clone the repo and cd into it:
```
git clone https://github.com/ffavale/BotS.git
cd BotS
```

5. Get the development branch
```
git checkout -b devel
```

6. Done; enjoy your git experience and start working
