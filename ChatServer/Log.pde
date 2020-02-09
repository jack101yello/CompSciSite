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