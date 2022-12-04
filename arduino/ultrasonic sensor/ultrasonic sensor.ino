#include <SoftwareSerial.h>

#include<SoftwareSerial.h>
SoftwareSerial HM10 (6,7);

const int trig_0 = 9;
const int echo_0 = 8;
const int trig_1 =11;
const int echo_1=12;


void setup() {
  pinMode(trig_0,OUTPUT);
  pinMode(trig_0,INPUT);
  pinMode(trig_1,OUTPUT);
  pinMode(trig_1,INPUT);
  Serial.begin(9600);
  HM10.begin(9600);

}

void loop() {
  //Bluth
  if(HM10.available())
    Serial.write(HM10.read());
  if(Serial.available())
    HM10.write(Serial.read());

  //신호발생
  digitalWrite(trig_0, LOW);
  digitalWrite(trig_1, LOW);
  delayMicroseconds(100);
  digitalWrite(trig_0,HIGH);
  digitalWrite(trig_1,HIGH);
  delayMicroseconds(100);
  digitalWrite(trig_0,LOW);
  digitalWrite(trig_1,LOW);

  //신호입력
  long Length_0 = pulseIn(echo_0, HIGH);
  long distance_0 = (Length_0/2) /29.1;
  long Length_1 = pulseIn(echo_1, HIGH);
  long distance_1 = (Length_1/2) /29.1;
  
  Serial.print("sensor_0 :");
  Serial.println(distance_0);
  Serial.print("sensor_1 :");
  Serial.println(distance_1);


}
