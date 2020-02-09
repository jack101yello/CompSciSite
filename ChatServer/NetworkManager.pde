ArrayList<Client> clientList = new ArrayList<Client>();

void check() { // Checks incoming messages
  Client client = server.available();
  if(client != null) {
    String incoming = client.readString();
    if(incoming != null) {
      server.write(client.ip() + " : " + incoming);
      log.add(client.ip() + " sent a message.");
    }
  }
}

void serverEvent(Server someServer, Client someClient) { // Runs when a new client joins
  clientList.add(someClient);
  log.add(someClient.ip() + " has joined.");
}

void disconnectEvent(Client someClient) { // Runs when a client leaves
  clientList.remove(someClient);
  log.add(someClient.ip() + " has left.");
}