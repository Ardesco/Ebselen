DO NOT USE THIS AS A FRAMEWORK
====================

### This is old code written many years ago and not the way I would suggest you do things now.  It's left here for posterity, it really is not the right way to go about things though.  I suggest you have a look at https://github.com/Ardesco/Selenium-Maven-Template instead

A Mavenised Selenium Test Framework (Built using Maven 2.2.1)

This project has been written using IntelliJ IDEA.  If you open the parent POM file with IDEA it will automatically create the IDEA project files for you.

The community edition of IDEA is available to download here and is free:

http://www.jetbrains.com/idea/download/

I don't use Eclipse so I can't provide .project and .classpath files, however eclipse should be able to work with POM files as well.  Please see the following links:

http://marketplace.eclipse.org/content/maven-integration-eclipse

http://maven.apache.org/guides/mini/guide-ide-eclipse.html

If you have dependency problems you probably need to do an initial build of the project, this can be done via the command line/terminal:

 * Open a terminal/command prompt
 * Navigate to the project root dir
 * type “mvn clean install -U”

This will do an initial build, update all your dependencies using maven and put the locally built ebselen jars into your .m2 directory.

To do the above you will need to have maven installed and accessible through the command line:

http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

All selenium tests should be placed within the ebselen-test module under the tests folder.  They *must* all end with ET, and they *must not* have "Test" in their name (e.g. GoogleExampleET.java).  This ensures that building the framework does not run the selenium tests, and running the selenium tests does not invoke all the framework tests.

To run the selenium tests type:

"mvn verify -Pebselen-tests"

By default the tests will run in htmlunit (good for a headless build server), if you want to run them in a different browser you can specify it on the command line.

For example to run the tests in FireFox type:

"mvn verify -Pebselen-tests -Dbrowser=firefox"

Currently supported browsers options are:

firefox
ie6
ie7
ie8
ie9
opera
googlechrome
htmlunit

(all ie options will use the version of ie currently installed upon your machine)
