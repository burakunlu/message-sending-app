# Java Multi Thread Message Sending App

This project aims to carry messages between two users until a certain amount of message is carried. The first message is carried between those users by concatenating number of messages that has been sent. 

Users can be created on either single thread or multi thread.

## Versions 
* Maven 3 (3.6.3) 
* Java 8 (1.8.0_251)

## Instructions

To build and execute the jar, you can simply use: 
```
sh ./start.sh
```

In addition to that you can use Makefile to build the project and run the application with your own parameters
```
make build
make run TYPE=multi FIRST_MESSAGE=hello USER_NAME=user2____
```

* TYPE: Start conversation using single or multithread (single/multi)
* FIRST_MESSAGE: First message that starts the conversation
* PLAYER_NAME: Name of the user that communicates with the "initiator" user

When the application started, it will create a user named "initiator" and it will start the conversation.