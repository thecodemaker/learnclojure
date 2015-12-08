# previous notes: https://gist.github.com/thecodemaker/36cb7e656cf7434a36c4
# use leiningen - leiningen.org

# create new project
lein new learnclojure

# project.clj
   - definition of the versions of software you are using
   - description, name of everything
   - equivalent to the pom.xml maven file

# run the main function, it will look for maine by default
lein run -m learnclojure.core
prints: Main Hello, World!

# setup main class so you don't need to specify it with run command
:main learnclojure.core
lein run
