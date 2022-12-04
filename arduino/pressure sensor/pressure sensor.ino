const int pressSensor =A1;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(57600);
}

void loop() {
  // put your main code here, to run repeatedly:
  int value = analogRead(pressSensor);
  Serial.println(value);
  delay(100);
}
