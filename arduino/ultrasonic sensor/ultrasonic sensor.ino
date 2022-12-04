#include <SoftwareSerial.h>

#include<SoftwareSerial.h>
SoftwareSerial HM10 (6,7);

const int trig =11;
const int echo =12;


void setup() {


  pinMode(trig,OUTPUT);
  pinMode(trig,INPUT);
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
  digitalWrite(trig, LOW);
  delayMicroseconds(1000);
  digitalWrite(trig,HIGH);
  delayMicroseconds(1000);
  digitalWrite(trig,LOW);
  //신호입력
  long Length = pulseIn(echo, HIGH);
  long distance = (Length/2) /29.1;
  Serial.print(distance);
  Serial.println(" Cm");



}
