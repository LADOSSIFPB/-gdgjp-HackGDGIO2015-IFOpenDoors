/**
 * Autor: Eri Jonhson
 * http://github.com/erijonhson
 *
 * Copyright © 2014-2015 LADOSS
 **/

#include <LiquidCrystal.h>;

LiquidCrystal lcd(8, 9, 4, 5, 6, 7);

char* laboratories[] = {"Informatica", "Mineracao", "Quimica", "Matematica"};

HardwareSerial Log = Serial;
HardwareSerial ArduinoServer = Serial1;

void setup() {
    Log.begin(115200);
    ArduinoServer.begin(9600);
    lcd.begin(16, 2);
    wait();
    Log.println("Start Display OK!");
}

void loop() {}

void serialEvent1() {
    uint8_t incomingByte = 0X00;
    if(ArduinoServer.available() > 0) {
        incomingByte = ArduinoServer.read();
        Log.print("Incoming Byte: ");
        Log.println(incomingByte);
        uint8_t open_door = bitRead(incomingByte, 7);
        uint8_t number_door = incomingByte - 1;
        if(open_door)
            number_door = incomingByte - 128 - 1;
        show_lab(number_door);
        show_door_situation(open_door);
        delay(3000);
        wait();
    }
}

void wait() {
    Log.println("Aguardando conexao...");
    lcd.clear();
    lcd.print("Aguardando");
    lcd.setCursor(0,1);
    lcd.print("conexao...");
}

void show_lab(uint8_t number_door) {
    Log.print("Show lab: ");
    Log.println(number_door);
    lcd.clear();
    lcd.print("Lab ");
    lcd.print(laboratories[number_door]);
}

void show_door_situation(uint8_t open_door) {
    Log.print("Door situation print: ");
    Log.println(open_door);
    lcd.setCursor(0,1);
    if(open_door)
        lcd.print("ABERTO!");
    else
        lcd.print("FECHADO!");
}

