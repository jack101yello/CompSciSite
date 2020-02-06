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

public class ChatServer extends PApplet {



Server server;
Log log;

public void setup() {
  
  server = new Server(this, 4444);
  log = new Log(10);
}

public void draw() {
  background(0);
  check();
  log.show();
}
class Log {
  private ArrayList<String> loggedMessages;
  private int maxMessages;
  
  public Log(int maxMessages) {
    this.maxMessages = maxMessages;
    loggedMessages = new ArrayList<String>();
    while(loggedMessages.size() != 0) {
      loggedMessages.remove(0);
    }
  }
  
  public void add(String str) {
    loggedMessages.add(str);
    while(loggedMessages.size() > maxMessages) {
      loggedMessages.remove(0);
    }
  }
  
  public void show() {
    textSize(30);
    fill(255);
    stroke(255);
    for(int i = 0; i < loggedMessages.size(); i++) {
      text(loggedMessages.get(i), 50, 50+(height/(maxMessages+1)) * i);
    }
  }
}
ArrayList<Client> clientList = new ArrayList<Client>();

public void check() { // Checks incoming messages
  Client client = server.available();
  if(client != null) {
    String incoming = client.readString();
    if(incoming != null) {
      server.write(client.ip() + " : " + incoming);
      log.add(client.ip() + " sent a message.");
    }
  }
}

public void serverEvent(Server someServer, Client someClient) { // Runs when a new client joins
  clientList.add(someClient);
  log.add(someClient.ip() + " has joined.");
}

public void disconnectEvent(Client someClient) { // Runs when a client leaves
  clientList.remove(someClient);
  log.add(someClient.ip() + " has left.");
}
  public void settings() {  size(1000, 750); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ChatServer" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
