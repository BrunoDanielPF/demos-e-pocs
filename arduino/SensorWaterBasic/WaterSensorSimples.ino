/* Pinos correspondentes ao sensor */
#define sensorvcc 2     /*Define vcc como pino 2 */
#define sensorsinal A1  /*Define sinal como pino A1 */

int val = 0;

void setup() { /*abre laço de configuração*/
  /* define 2 como pino de saída do Arduino */
  pinMode(sensorvcc, OUTPUT);

  digitalWrite(sensorvcc, LOW); /* vcc tem nível lógico baixo até que haja alguma variação na leitura */
  Serial.begin(9600);
}

void loop() { /*laço de repetição */
  int level = readSensor();
  Serial.print("Nível de água: ");
  Serial.println(level);
  delay(1000);
}

/* leitura do sensor */
int readSensor() {
digitalWrite(sensorvcc, HIGH);  /* alimenta o sensor */
delay(10);
val = analogRead(sensorsinal);    /* Realiza a leitura analógica do sinal do sensor */
digitalWrite(sensorvcc, LOW);   /* Desliga o sensor */
return val;             /* envia leitura */
}