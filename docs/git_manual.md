# Git Manual

## Working on something new
1. Go to a common branch like `devel`
```
git switch devel
```

2. Pull the changes made by others into the common branch
```
git pull
```

3. Checkout a new branch. It should be named something relevent, like the issue number (`issue-x`)
```
git checkout -b issue-x
```

4. Here you can do what you please. Make changes and work on stuff

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

10. Delete the feature branch (optional)
```
git branch -d issue-x
```

11. Done. Repeat the process


## Working on something for which you already have a local branch 
This is assuming you already have a branch that you havn't deleted.

1. Go to a common branch like `devel`
```
git switch devel
```

2. Pull the changes made by others into the common branch
```
git pull
```

3. Switch to a local branch, such as `issue-x`
```
git switch issue-x
```

4. Pull in all the changes made to the common branch
```
git merge devel
```

5. Now continue working on the branch

6. Commit your changes as you work
```
git commit -m "commit message"
```

7. Once you are done working on the feature switch back to the common branch
```
git switch devel
```

8. Pull the changes made by others into the common branch
```
git pull
```

9. Merge your changes into the common branch
```
git merge issue-x
```

10. Push the changes made to devel
```
git push origin devel
```

11. Delete the feature branch (optional)
```
git branch -d issue-x
```

12. Done. Repeat the process

## Setup
0. Set your identity:
```
git config --global user.name "Your Name"
git config --global user.email "Your Email"
```

0. Set your default branch name:
```
git config --global init.defaultBranch main
```

0. Set up some funky aliases that will make your log experience better:
```
git config --global alias.logg "log --graph --pretty=format:'%Cred%h%Creset %Cgreen(%cr) %C(bold blue)<%an>%Creset -%C(yellow)%d%Creset %s' --abbrev-commit"
git config --global alias.loff "log --graph --stat"
git config --global alias.logff "log --graph --stat"
```

0. Clone the repo and cd into it:
```
git clone https://github.com/ffavale/BotS.git
cd BotS
```
0. Get the development branch
```
git checkout -b devel
```
0. Done; enjoy your git experience and start working
Now by typing `git logg`, `git loff` and `git logff` a better log output is generated.
