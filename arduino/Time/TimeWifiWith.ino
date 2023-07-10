#include <WiFi.h>
#include "time.h"
#include "sntp.h"

const char* ssid       = "nome wifi";
const char* password   = "senha wifi";

const char* ntpServer1 = "pool.ntp.org";
const char* ntpServer2 = "time.nist.gov";
const long  gmtOffset_sec = 3600;
const int   daylightOffset_sec = 3600;

#define time_zone	PSTR("<-03>3")

void printLocalTime()
{
  struct tm timeinfo;
  if(!getLocalTime(&timeinfo)){
    Serial.println("Ainda nao inicializou o tempo, aguarde");
    return;
  }
  char timeStringBuff[50]; 
  strftime(timeStringBuff, sizeof(timeStringBuff), "%H:%M", &timeinfo);
  String asString(timeStringBuff);
  int value = strcmp(timeStringBuff, "23:42"); // tempo estimado para execucao
  if(value == 0){
    // escopo para executar alguma coisa
    Serial.println("Aciona alguma coisa");
  }else{
    //escape
    Serial.println(timeStringBuff);
  }
}

// Callback function (get's called when time adjusts via NTP)
void timeavailable(struct timeval *t)
{
  Serial.println("Obteve ajuste de tempo do NTP!");
  printLocalTime();
}

void setup()
{
  Serial.begin(9600);

  // set notification call-back function
  sntp_set_time_sync_notification_cb( timeavailable );

  /**
   * NTP server address could be aquired via DHCP,
   *
   * NOTE: This call should be made BEFORE esp32 aquires IP address via DHCP,
   * otherwise SNTP option 42 would be rejected by default.
   * NOTE: configTime() function call if made AFTER DHCP-client run
   * will OVERRIDE aquired NTP server address
   */
  sntp_servermode_dhcp(1);    // (optional)

  /**
   * This will set configured ntp servers and constant TimeZone/daylightOffset
   * should be OK if your time zone does not need to adjust daylightOffset twice a year,
   * in such a case time adjustment won't be handled automagicaly.
   */
  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer1, ntpServer2);

  /**
   * A more convenient approach to handle TimeZones with daylightOffset 
   * would be to specify a environmnet variable with TimeZone definition including daylight adjustmnet rules.
   * A list of rules for your zone could be obtained from https://github.com/esp8266/Arduino/blob/master/cores/esp8266/TZ.h
   */
  configTzTime(time_zone, ntpServer1, ntpServer2);

  //connect to WiFi
  Serial.printf("Conectando ao %s ...", ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.print(".");
  }
  Serial.println(" conectado");

}

void loop()
{
  delay(5000);
  printLocalTime();     // it will take some time to sync time :)
}
