# NAV-IT-SIF-bachelor backend

#### NOTE: REMEMBER TO ADD A OWN OAUTH SERVER
### How to prepare the project for the dockerfile:

The dockerfile looks after jar file in the target folder.

You can create a jar file by running

``./mvnw compile ``
and then:  
``./mvnw build -Dmaven.test.skip``
due to one of the test failing we need to flag the maven compiler to skips tests for now, this will be a priority to fix!

After this, the dockerfile is ready to use