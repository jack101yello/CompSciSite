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
  
  void getMessage() {
    String incoming = client.readString();
    if(incoming != null) {
      log.add(incoming);
    }
    while(log.size() > maxMessages) {
      log.remove(0);
    }
  }
  
  void show() {
    textSize(30);
    fill(255);
    stroke(255);
    for(int i = 0; i < log.size(); i++) {
      text(log.get(i), 50, 50+((height*3/4)/(maxMessages+1)) * i);
    }
    rect(0, height*3/4, width, 1);
  }
}