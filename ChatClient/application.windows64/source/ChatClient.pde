import processing.net.*;

Client client;
Chat chat;
Typing typing;

void setup() {
  size(1000, 750);
  client = new Client(this, "192.168.56.1", 4444);
  chat = new Chat(10);
  typing = new Typing();
}

void draw() {
  background(0);
  chat.getMessage();
  chat.show();
  typing.show();
  typing.type();
}