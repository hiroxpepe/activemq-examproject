# activemq-examproject
A simple project by using Spring MVC + Spring JMS over Apache ActiveMQ.

## Overview
It's pair web applications that the one sends a message to Apache ActiveMQ message broker, the other receives the message from the broker.

## Content
|No|Purpose|Path|
|:--|:--|:--|
|1|Message sender|/source/queue/sender/webapp|
|2|Message receiver|/source/queue/receiver/webapp|
|3|Message broker|run via docker|

## What tools to use.
- Maven (* also JDK)
- Docker

## How to build the project.
```sh
$ cd PathToThisREADME.md
$ mvn install
```

## How to run the project.  
---
### STEP1: run Apache ActiveMQ via docker.  
```sh
> docker pull rmohr/activemq
> docker run -p 61616:61616 -p 8161:8161 rmohr/activemq
```
- check if it can access.  
http://localhost:8161/admin  

```sh
user:admin
pass:admin
```
---
### STEP2: run receiver webapp via jetty:run.
open new terminal.
```sh
$ cd PathToThisREADME.md
$ cd source/queue/receiver/webapp
$ mvn jetty:run
```
- check if it can access.  
http://localhost:8082/home.html  

- stop webapp.  
hit "Ctrl + C" in the terminal.
---
### STEP3: run sender webapp via jetty:run.
open new terminal.
```sh
$ cd PathToThisREADME.md
$ cd source/queue/sender/webapp
$ mvn jetty:run
```
- check if it can access.  
http://localhost:8081/home.html  

- stop webapp.  
hit "Ctrl + C" in the terminal.

## License
It's released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
