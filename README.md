# NAV-IT-SIF-bachelor backend

#### NOTE: REMEMBER TO ADD A OWN OAUTH SERVER
also: **Remember to remove the /mock before the real endpoint first**

**We have some test methods, this need to be changed to the enviorment methods in a dev/real database method**
### How to prepare the project for the dockerfile:
**The compose is depended on a frontend image, please remeber to create a frontend image!**

The dockerfile looks after jar file in the target folder. **This is created in the dockerfile by multistaging**. 

if you need to create a manual jar file you can use teese two commands. 

```bash
./mvnw compile
```
and then:  
```bash 
./mvnw package -Dmaven.test.skip
```


After this, the dockerfile is ready to use