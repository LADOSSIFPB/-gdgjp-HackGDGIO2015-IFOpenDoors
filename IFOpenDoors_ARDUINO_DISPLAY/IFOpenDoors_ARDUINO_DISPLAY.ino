/**
 * Autor: Eri Jonhson
 * http://github.com/erijonhson
 *
 * Copyright © 2014-2015 LADOSS
 **/

#include <LiquidCrystal.h>;

// Inicializa a biblioteca com os números dos pinos da interface
LiquidCrystal lcd(8, 9, 4, 5, 6, 7); // Cria um LCD objeto com estes pinos

char* laboratories[] = {"Informatica", "Mineracao", "Quimica", "Matematica"};

HardwareSerial Log = Serial;
HardwareSerial ArduinoServer = Serial1;

void setup() {
    Log.begin(115200);
    ArduinoServer.begin(9600);
    lcd.begin(16, 2); // Seta o display 16 colunas por 2 linhas
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
        uint8_t number_door = incomingByte;
        if(open_door)
            number_door = incomingByte - 128;
        laboratory_print(number_door);
        door_situation_print(open_door);
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

void laboratory_print(uint8_t number_door) {
    Log.print("Laboratory print: ");
    Log.println(number_door);
    lcd.clear();
    lcd.print("Lab ");
    lcd.print(laboratories[number_door]);
}

void door_situation_print(uint8_t open_door) {
    Log.print("Door situation print: ");
    Log.println(open_door);
    lcd.setCursor(0,1);
    if(open_door)
        lcd.print("ABERTO!");
    else
        lcd.print("FECHADO!");
}

