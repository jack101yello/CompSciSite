import processing.net.*;

Client client;
Chat chat;
Typing typing;
int loginStage; // 1 : IP address    0 : normal application
String serverIP;

void setup() {
  size(1000, 750);
  chat = new Chat(10);
  typing = new Typing();
  loginStage = 1;
}

void draw() {
  background(0);
  switch(loginStage) {
  case 0: // application itself
    chat.getMessage();
    chat.show();
    typing.show();
    typing.type();
    break;
  case 1: // IP
    text("Please enter the server IP.", width/2, height/2);
    typing.show();
    typing.type();
    break;
  }
}

void connect() {
  client = new Client(this, serverIP, 4444);
}
