#include "Ultrasonic.h"  //INCLUSÃO DA BIBLIOTECA NECESSÁRIA PARA FUNCIONAMENTO DO CÓDIGO
#define som 9
#define LED_RED_PIN 11
#define LED_GREEN_PIN 12
#define LED_RED_2_PIN 3
#define LED_RED_3_PIN 5

const int echoPin = 7;  //PINO DIGITAL UTILIZADO PELO HC-SR04 ECHO(RECEBE)
const int trigPin = 6;  //PINO DIGITAL UTILIZADO PELO HC-SR04 TRIG(ENVIA)

Ultrasonic ultrasonic(trigPin, echoPin);

int distancia;
String result;

void setup() {
  pinMode(echoPin, INPUT);
  pinMode(trigPin, OUTPUT);
  pinMode(LED_RED_PIN, OUTPUT);
  pinMode(LED_RED_2_PIN, OUTPUT);
  pinMode(LED_RED_3_PIN, OUTPUT);
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
  if (distancia < 10) {
    tone(som, 1500, 1500);
    digitalWrite(LED_RED_PIN, HIGH);
    digitalWrite(LED_RED_2_PIN, HIGH);
    digitalWrite(LED_RED_3_PIN, HIGH);
    digitalWrite(LED_GREEN_PIN, LOW);
  }else if(distancia < 15){
    digitalWrite(LED_RED_PIN, HIGH);
    digitalWrite(LED_RED_2_PIN, HIGH);
    digitalWrite(LED_RED_3_PIN, LOW);
    digitalWrite(LED_GREEN_PIN, LOW);
  }else if(distancia < 20) {
    digitalWrite(LED_RED_PIN, HIGH);
    digitalWrite(LED_RED_2_PIN, LOW);
    digitalWrite(LED_RED_3_PIN, LOW);
    digitalWrite(LED_GREEN_PIN, LOW);
  } else {
    tone(som, 0, 1500);
    digitalWrite(LED_RED_PIN, LOW);
    digitalWrite(LED_RED_2_PIN, LOW);
    digitalWrite(LED_RED_3_PIN, LOW);
    digitalWrite(LED_GREEN_PIN, LOW);
    delay(500);
    digitalWrite(LED_GREEN_PIN, HIGH);
    delay(500);
  }
}
