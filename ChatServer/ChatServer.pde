import processing.net.*;

Server server;
Log log;

void setup() {
  size(1000, 750);
  server = new Server(this, 4444);
  log = new Log(10);
  log.add("This IP address is " + Server.ip());
}

void draw() {
  background(0);
  check();
  log.show();
}
