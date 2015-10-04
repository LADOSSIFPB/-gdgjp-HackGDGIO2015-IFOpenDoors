/*
   Web client sketch for IDE v1.0.1 and w5100/w5200
   Uses POST method.
   Posted November 2012 by SurferTim
*/

#include "IFOpenDoors_ARDUINO_CLIENT.h"

ArduinoClient::ArduinoClient(HardwareSerial* serial) {
    strcpy(webServiceAddr, "192.168.0.111");
    strcpy(pageName, "/IFOpenDoors_SERVICE/door/arduino");
    Log = serial;
}

int ArduinoClient::sendInformation(char* ip, uint16_t port, uint8_t idLab)
{
    // If using a static IP, comment out the next line
    Ethernet.maintain();
    
    // params must be url encoded.
    sprintf(params,"{\"ip\": \"%s\",\"porta\": %d,\"lab\": %d}", ip , port, idLab);
    if(!postPage(webServiceAddr, serverPort, pageName, params)) {
  	  Log->println(F("Fail"));
  	  return 0;
    }
    else {
  	  Log->println(F("Pass"));
  	  return 1;
    }
}

byte ArduinoClient::postPage(char* domainBuffer,int thisPort,char* page,char* thisData)
{
    int inChar;
    char outBuf[64];
  
    Log->print(F("connecting..."));
  
    if(client.connect(domainBuffer,thisPort) == 1)
    {
  	Log->println(F("connected"));
  
  	// send the header
  	sprintf(outBuf,"POST %s HTTP/1.1",page);
  	client.println(outBuf);
  	sprintf(outBuf,"Host: %s",domainBuffer);
  	client.println(outBuf);
  	client.println(F("Connection: close\r\nContent-Type: application/json"));
  	sprintf(outBuf,"Content-Length: %u\r\n",strlen(thisData));
  	client.println(outBuf);
  
  	// send the body (variables)
  	client.print(thisData);
    } 
    else
    {
  	Log->println(F("failed"));
  	return 0;
    }
  
    int connectLoop = 0;
  
    while(client.connected())
    {
  	while(client.available())
  	{
  	  inChar = client.read();
  	  Log->write(inChar);
  	  connectLoop = 0;
  	}
  
  	delay(1);
  	connectLoop++;
  	if(connectLoop > 10000)
  	{
  	  Log->println();
  	  Log->println(F("Timeout"));
  	  client.stop();
  	}
    }
  
    Log->println();
    Log->println(F("disconnecting."));
    client.stop();
    return 1;
}
