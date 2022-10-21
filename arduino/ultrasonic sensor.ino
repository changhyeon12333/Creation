const int trig =11;
const int echo =12;


void setup() {
  pinMode(trig,OUTPUT);
  pinMode(trig,INPUT);
  Serial.begin(9600);

}

void loop() {

  //신호발생
  digitalWrite(trig, LOW);
  delayMicroseconds(2);
  digitalWrite(trig,HIGH);
  delayMicroseconds(10);
  digitalWrite(trig,LOW);
  //신호입력
  long Length = pulseIn(echo, HIGH);
  long distance = (Length/2) /29.1;

  Serial.print(distance);
  Serial.println(" Cm");


}
