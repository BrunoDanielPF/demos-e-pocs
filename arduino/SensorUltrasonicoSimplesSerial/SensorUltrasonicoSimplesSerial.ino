#include "Ultrasonic.h"  //INCLUSÃO DA BIBLIOTECA NECESSÁRIA PARA FUNCIONAMENTO DO CÓDIGO
#define som 9
#define LED_RED_PIN 11 // PINO DIGITAL UTILIZADO PELO LED VERMELHO
#define LED_GREEN_PIN 12 // PINO DIGITAL UTILIZADO PELO LED VERDE

const int echoPin = 7;  //PINO DIGITAL UTILIZADO PELO HC-SR04 ECHO(RECEBE)
const int trigPin = 6;  //PINO DIGITAL UTILIZADO PELO HC-SR04 TRIG(ENVIA)

Ultrasonic ultrasonic(trigPin, echoPin);

int distancia;
String result;

void setup() {
  pinMode(echoPin, INPUT);
  pinMode(trigPin, OUTPUT);
  pinMode(LED_RED_PIN, OUTPUT);
  pinMode(LED_GREEN_PIN, OUTPUT);
  Serial.begin(9600);
}
void loop() {
  hcsr04();
  Serial.print("Distancia ");
  Serial.print(result);
  Serial.println("cm");
  validateDistanceForSound(distancia);
}
//MÉTODO RESPONSÁVEL POR CALCULAR A DISTÂNCIA
void hcsr04() {
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  distancia = (ultrasonic.read(CM));
  result = String(distancia);
  delay(500);
}

void validateDistanceForSound(int distancia) {
  //SE A DISTANCIA FOR MENOR QUE 5 CM IRA APITAR E LIGAR A LUZ VERMELHA
  if (distancia < 5) {
    tone(som, 600, 1500);
    digitalWrite(LED_RED_PIN, HIGH);
    digitalWrite(LED_GREEN_PIN, LOW);
  } else {
    //SE NAO LIGAR A LUZ VERDE
    tone(som, 0, 1500);
    digitalWrite(LED_RED_PIN, LOW);
    digitalWrite(LED_GREEN_PIN, HIGH);
  }
}
