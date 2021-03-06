/**
 * Autor: Eri Jonhson
 * http://github.com/erijonhson
 *
 * Copyright © 2014-2015 LADOSS
 **/

#include <SPI.h>
#include <Ethernet.h>
#include <uHTTP.h>
#include <ArduinoJson.h>
#include "IFOpenDoors_ARDUINO_CLIENT.h"

uint8_t macaddr[6] = {0x00, 0x01, 0x02, 0x03, 0x04, 0x66};
uint16_t port = 5534;

uHTTP *server = new uHTTP(port);

EthernetClient response;
StaticJsonBuffer<200> jsonBuffer;
#define JSON_PARSE_ERROR -1

HardwareSerial Log = Serial;
HardwareSerial Display = Serial1;

#define DIGITAL_PIN 22

#define OPEN    1 
#define CLOSE   0

void setup(){
    pinMode(DIGITAL_PIN, OUTPUT);
    fecharPorta();
    Log.println(F("Pino digital configurado com sucesso."));
    
    Log.begin(115200);

    Ethernet.begin(macaddr);

    Log.print(F("Starting uHTTP at "));
    Log.print(Ethernet.localIP());
    Log.println(":5534");

    // arduino display
    // arduino_display->begin();
    // int idLab = arduino_display->chooseLab();
    int idLab = 20;

    // arduino cliente
    ArduinoClient arduino_client(&Log);
    char* ip = new char[16];
    IPAddress ipAddrs = Ethernet.localIP();
    sprintf(ip, "%d.%d.%d.%d", ipAddrs[0], ipAddrs[1], ipAddrs[2], ipAddrs[3]);
    uint8_t resp = 0;
    while (!resp) {
        resp = arduino_client.sendInformation(ip, port, idLab);
    }

    // arduino servidor
    server->begin();

    Display.begin(9600);
    Display.flush();

    logRoutes();
    
    Log.print(Ethernet.localIP());
    
}

void fecharPorta() {
    digitalWrite(DIGITAL_PIN, LOW);
}

void abrirPorta() {
    digitalWrite(DIGITAL_PIN, HIGH);
}

void logRoutes(){
    Log.println(F("+-----------------------------------------------+"));
    Log.println(F("| METHOD | URI         | DESCRIPTION            |"));
    Log.println(F("+-----------------------------------------------+"));
    Log.println(F("| GET    | /door       | show services          |"));
    Log.println(F("| POST   | /door/open  | open a specific doors  |"));
    Log.println(F("| POST   | /door/close | close a specific doors |"));
    Log.println(F("+-----------------------------------------------+"));
}

void loop(){
    if((response = server->available())){
        Log.println("Client connected.");
        if(server->uri(F("/door"))){
            show_services();
        } else{
            if(server->method(uHTTP_METHOD_POST)){
                Log.println("POST method identified.");
                if(server->uri(F("/door/open")) || server->uri(F("/door/close"))){
                    uint8_t door_number = required_door();
                    if (door_number != JSON_PARSE_ERROR)
                        open_door(door_number);
                    else
                        send_headers(400);
                }
            }else{
                send_headers(404);
            }
        }
        response.stop();
    }
}

void show_services() {
    Log.println("Show services");
    send_headers(200);
    response.println(F("Content-Type: text/plain"));
    response.println(F("+-----------------------------------------------+"));
    response.println(F("| METHOD | URI         | DESCRIPTION            |"));
    response.println(F("+-----------------------------------------------+"));
    response.println(F("| GET    | /door       | show services          |"));
    response.println(F("| POST   | /door/open  | open a specific doors  |"));
    response.println(F("| POST   | /door/close | close a specific doors |"));
    response.println(F("+-----------------------------------------------+"));
}

void open_door(uint8_t door_number) {
    Log.print("Presented door number: ");
    Log.println(door_number);
    if (door_number >= 1 && door_number <= 4) {
        if (server->uri(F("/door/open"))) {
            send_to_arduino_display(door_number, OPEN);
            abrirPorta();
        }
        else {
            send_to_arduino_display(door_number, CLOSE);
            fecharPorta();
        }
        send_headers(200);
    } else {
        send_headers(400);
    }
}

uint8_t required_door() {
    Log.println("Required door");
    char *json = new char[64];
    strcpy(json, server->body());
    JsonObject& root = jsonBuffer.parseObject(json);
    if (root.success())
        return root["number"];
    else
        return JSON_PARSE_ERROR;
}

void send_to_arduino_display(uint8_t number_door, uint8_t action){
    Log.println("Send to Arduino Display");
    uint8_t output_byte = number_door;
    bitWrite(output_byte, 7, action);
    Display.write(output_byte);
    Display.flush();
    Log.print("Value: ");
    Log.println(output_byte);
}

void send_headers(uint16_t code){
    response.print(F("HTTP/1.1"));
    response.print(F(" "));
    switch(code){
        case 200:
            response.println(F("200 OK"));
            break;
        case 400:
            response.println(F("400 Bad Request"));
            break;
        case 401:
            response.println(F("401 Unauthorized"));
            break;
        case 404:
            response.println(F("404 Not Found"));
            break;
    }
    response.println(F("Connection: close"));
    response.println();
}

