#final script of chutteur
# date of creation : 16/03/2020 (m.a.j 27/03/2020 - ajout base de données)
#author : BasmaAwatefSaadaoui

'''
This script treats the sensors linked to Raspberry Pi all together.
The sensors used in this project are linked to the Raspberry Pi by the GrovePi+ board.
    - Analog port A1 : Sound sensor (Sound sensor v1.6)
    - Digital port D7 : Buzzer (Grove Buzzer v1.2)
    - Digital port I2C-2 : Display LCD RGB Backlight (Grove-LCD RGB Backlight v4.0)
  
    - Analog port A0 : Light sensor (Light snesor v1.1)
    - Digital port D3 : LED (LED Socket Kit v1.5)
    - Digital port D4 : Button (Button v1.2)
    - Digital port D8 : Temperature and humidity sensor (Grove Temperatureumidity Sensor v1.2)
'''

from datetime import *
import os
import sys
import time
import grovepi
import smbus
import RPi.GPIO as GPIO



# Connect the Grove LCD-RGB Backlight to port I2C. This devidce has two I2C addresses
DISPLAY_RGB_ADDR = 0x62
DISPLAY_TEXT_ADDR = 0x3e

# Connect the Grove Light Sensor to analog port A0
LIGHT_SENSOR = 0
# Connect the Grove Sound Sensor to analog port A1
SOUND_SENSOR = 1
# Connect the Grove LED to digital port D3
LED = 3
# Connect the Grove Button to digital port D4
BUTTON = 4
# Connect the Grove Buzzer to digital port D7
BUZZER = 7
# Connect the DHT sensor (temperature & hmidity sensor) to digital port D8
DHT_SENSOR_PIN = 8
# The DHT_SENSOR_TYPE below may need to be changed depending on which DHT sensor you have:
#  0 - DHT11 - blue one - comes with the GrovePi+ Starter Kit
#  1 - DHT22 - white one, aka DHT Pro or AM2302
#  2 - DHT21 - black one, aka AM2301
DHT_SENSOR_TYPE = 0



grovepi.pinMode(LIGHT_SENSOR,"INPUT")
grovepi.pinMode(SOUND_SENSOR,"INPUT")
grovepi.pinMode(LED,"OUTPUT")
grovepi.pinMode(BUTTON,"INPUT")
grovepi.pinMode(BUZZER,"OUTPUT")

#LCD config
if sys.platform == 'uwp':
    import winrt_smbus as smbus
    bus = smbus.SMBus(1)
else:
    rev = GPIO.RPI_REVISION
    if rev == 2 or rev == 3:
        bus = smbus.SMBus(1)
    else:
        bus = smbus.SMBus(0)



# set backlight to (R,G,B) (values from 0..255 for each)
def setRGB(r,g,b):
    bus.write_byte_data(DISPLAY_RGB_ADDR,0,0)
    bus.write_byte_data(DISPLAY_RGB_ADDR,1,0)
    bus.write_byte_data(DISPLAY_RGB_ADDR,0x08,0xaa)
    bus.write_byte_data(DISPLAY_RGB_ADDR,4,r)
    bus.write_byte_data(DISPLAY_RGB_ADDR,3,g)
    bus.write_byte_data(DISPLAY_RGB_ADDR,2,b)

# send command to display (no need for external use)
def textCommand(cmd):
    bus.write_byte_data(DISPLAY_TEXT_ADDR,0x80,cmd)

# set display text \n for second line(or auto wrap)
def setText(text):
    textCommand(0x01) # clear display
    time.sleep(.05)
    textCommand(0x08 | 0x04) # display on, no cursor
    textCommand(0x28) # 2 lines
    time.sleep(.05)
    count = 0
    row = 0
    for c in text:
        if c == '\n' or count == 16:
            count = 0
            row += 1
            if row == 2:
                break
            textCommand(0xc0)
            if c == '\n':
                continue
        count += 1
        bus.write_byte_data(DISPLAY_TEXT_ADDR,0x40,ord(c))

#Update the display without erasing the display
def setText_norefresh(text):
    textCommand(0x02) # return home
    time.sleep(.05)
    textCommand(0x08 | 0x04) # display on, no cursor
    textCommand(0x28) # 2 lines
    time.sleep(.05)
    count = 0
    row = 0
    while len(text) < 32: #clears the rest of the screen
        text += ' '
    for c in text:
        if c == '\n' or count == 16:
            count = 0
            row += 1
            if row == 2:
                break
            textCommand(0xc0)
            if c == '\n':
                continue
        count += 1
        bus.write_byte_data(DISPLAY_TEXT_ADDR,0x40,ord(c))

# Create a custom character (from array of row patterns)
def create_char(location, pattern):
    """
    Writes a bit pattern to LCD CGRAM
    Arguments:
    location -- integer, one of 8 slots (0-7)
    pattern -- byte array containing the bit pattern, like as found at
               https://omerk.github.io/lcdchargen/
    """
    location &= 0x07 # Make sure location is 0-7
    textCommand(0x40 | (location << 3))
    bus.write_i2c_block_data(DISPLAY_TEXT_ADDR, 0x40, pattern)



if __name__=="__main__":
   
    temp_datafile = open("TempHum.csv",'a')
    snd_datafile = open("Sound.csv",'a')
    led_datafile = open("LED.csv",'a')
    light_datafile = open("Light.csv",'a')
    
    # The threshold to turn the led on 400.00 * 5 / 1024 = 1.95v
    threshold_value = 400
    
    setText("  Hello! Don't \n make any noise!")
    setRGB(255,255,0)
    time.sleep(5)
    while True:
        try:
            setRGB(0,255,0)
            text = str(datetime.now().strftime("Date: %d/%m/%Y\nTime: %H:%M"))
            print(text)
            for i in range(0,32):
                setText(text[:i])
                time.sleep(.2)
                
                # Read the sound level
                sound_sensor_value = grovepi.analogRead(SOUND_SENSOR)
                # If loud, buzz for .1 sec, otherwise stop buzzing
                if sound_sensor_value > threshold_value:
                    grovepi.digitalWrite(BUZZER,1)
                    time.sleep(.2)
                    grovepi.digitalWrite(BUZZER,0)
                    time.sleep(.2)
                    setRGB(255,0,0)
                    setText("     CHUT!     ")
                    time.sleep(5)
                    setRGB(0,255,0)
                else:
                    grovepi.digitalWrite(BUZZER,0)
                    grovepi.digitalWrite(LED,0)
                
                
                    if (grovepi.digitalRead(BUTTON) == 1)  :
                        grovepi.digitalWrite(LED,1)     # Send HIGH to switch on LED
                        time.sleep(0.3)
                        grovepi.digitalWrite(LED,0)
                        setRGB(255,255,0)
                        # Get light sensor value
                        light_sensor_value = grovepi.analogRead(LIGHT_SENSOR)
                        # Calculate resistance of light sensor in K
                        light_sensor_resistance = (1023 - light_sensor_value) * 10 / light_sensor_value
                        [temp_c,hum] = grovepi.dht(DHT_SENSOR_PIN,DHT_SENSOR_TYPE)
                        temp_f = temp_c * 9.0 / 5.0 + 32.0
                        setText("%.fC/%dF Hum:%.1f Light-res:%.1f"%(temp_c,temp_f,hum, light_sensor_resistance))
                        temp_datafile.write("%.f,%.f,%.f\n"%(temp_c,temp_f,hum))
                        snd_datafile.write("%d\n"%(sound_sensor_value))
                        led_datafile.write("1")
                        light_datafile.write("%d\n"%(light_sensor_value))
                        time.sleep(10)
                        setRGB(0,255,0)
            time.sleep(2)
       
 
                
        except KeyboardInterrupt:
            grovepi.digitalWrite(BUZZER,0)
            grovepi.digitalWrite(LED,0)
            temp_datafile.close()
            snd_datafile.close()
            led_datafile.close()
            light_datafile.close()
            break

        except IOError:
            print ("Error")

