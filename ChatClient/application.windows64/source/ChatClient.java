import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.net.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ChatClient extends PApplet {



Client client;
Chat chat;
Typing typing;

public void setup() {
  
  client = new Client(this, "192.168.56.1", 4444);
  chat = new Chat(10);
  typing = new Typing();
}

public void draw() {
  background(0);
  chat.getMessage();
  chat.show();
  typing.show();
  typing.type();
}
class Chat {
  private int maxMessages;
  ArrayList<String> log;
  
  public Chat(int maxMessages) {
    this.maxMessages = maxMessages;
    log = new ArrayList<String>();
    while(log.size() != 0) {
      log.remove(0);
    }
  }
  
  public void getMessage() {
    String incoming = client.readString();
    if(incoming != null) {
      log.add(incoming);
    }
    while(log.size() > maxMessages) {
      log.remove(0);
    }
  }
  
  public void show() {
    textSize(30);
    fill(255);
    stroke(255);
    for(int i = 0; i < log.size(); i++) {
      text(log.get(i), 50, 50+((height*3/4)/(maxMessages+1)) * i);
    }
    rect(0, height*3/4, width, 1);
  }
}
class Typing {
  private String message;
  
  public Typing() {
    message = "";
  }
  
  public void type() {
    if(keyPressed && (int)(key) >= 32 && (int)(key) <= 126) {
      message += key;
    }
    switch(key) {
      case BACKSPACE: 
      if(message.length() > 0) {
        message = message.substring(0, message.length()-1);
      }
      break;
      case ENTER: send();
      case RETURN: send();
    }
    key = (char)(0); // Sets key to the null character in order to prevent sticky keys
    keyPressed = false;
  }
  
  public void send() { // Send the message
    client.write(message);
    message = "";
  }
  
  public void show() {
    textSize(30);
    fill(255);
    stroke(255);
    text(message, 50, height*7/8);
  }
}
  public void settings() {  size(1000, 750); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ChatClient" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
