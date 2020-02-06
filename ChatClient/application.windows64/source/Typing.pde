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