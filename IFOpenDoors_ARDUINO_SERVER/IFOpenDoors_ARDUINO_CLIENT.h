/*
   Web client sketch for IDE v1.0.1 and w5100/w5200
   Uses POST method.
   Posted November 2012 by SurferTim
*/

#include <SPI.h>
#include <Ethernet.h>

class ArduinoClient {
  public:
    ArduinoClient(HardwareSerial* serial);
    int sendInformation(char* ip, uint16_t, uint8_t);
  
  private:
    
    HardwareSerial* Log;
    
    //Change to your server domain
    char webServiceAddr[14];
    
    // change to your server's port
    int serverPort = 8080;
    
    // change to the page on that server
    char pageName[34];
    
    EthernetClient client;
    
    // insure params is big enough to hold your variables
    char params[128];
    
    byte postPage(char*, int, char* ,char*);
};
