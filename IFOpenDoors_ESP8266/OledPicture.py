lcd.init_display()

ok = {'13 64': 1, '13 63': 1, '13 62': 1, '21 66': 1, '21 67': 1, '21 64': 1, '21 65': 1, '21 62': 1, '21 63': 1, '21 60': 1, '21 61': 1, '19 58': 1, '19 59': 1, '20 59': 1, '17 58': 1, '17 59': 1, '21 59': 1, '20 67': 1, '18 59': 1, '18 58': 1, '17 63': 1, '17 62': 1, '17 61': 1, '17 60': 1, '17 67': 1, '17 66': 1, '17 65': 1, '17 64': 1, '17 69': 1, '17 68': 1, '20 66': 1, '22 66': 1, '20 65': 1, '20 64': 1, '15 74': 1, '15 75': 1, '20 61': 1, '19 64': 1, '22 63': 1, '22 62': 1, '22 61': 1, '22 60': 1, '19 67': 1, '22 65': 1, '22 64': 1, '17 74': 1, '17 75': 1, '17 76': 1, '20 62': 1, '17 70': 1, '17 71': 1, '17 73': 1, '19 69': 1, '20 60': 1, '20 68': 1, '16 59': 1, '20 63': 1, '15 65': 1, '15 64': 1, '15 67': 1, '15 66': 1, '15 61': 1, '15 60': 1, '15 63': 1, '15 62': 1, '15 69': 1, '15 68': 1, '19 61': 1, '19 60': 1, '19 63': 1, '19 62': 1, '19 65': 1, '15 73': 1, '15 70': 1, '19 66': 1, '16 73': 1, '19 68': 1, '16 71': 1, '16 70': 1, '16 76': 1, '16 75': 1, '16 74': 1, '18 71': 1, '18 70': 1, '18 73': 1, '18 75': 1, '18 74': 1, '18 76': 1, '16 64': 1, '16 65': 1, '16 66': 1, '16 67': 1, '16 60': 1, '16 61': 1, '16 62': 1, '16 63': 1, '16 68': 1, '16 69': 1, '19 70': 1, '18 68': 1, '18 69': 1, '18 62': 1, '18 63': 1, '18 60': 1, '18 61': 1, '18 66': 1, '18 67': 1, '18 64': 1, '18 65': 1, '23 64': 1, '23 62': 1, '23 63': 1, '14 66': 1, '14 64': 1, '14 65': 1, '14 62': 1, '14 63': 1, '14 60': 1, '14 61': 1, '15 59': 1}

for i in ok:
	aux = i.split()
	lcd.pixel(int(aux[1]), int(aux[0]), 1)

lcd.show()

while True:
	lcd.invert(1)
	time.sleep_ms(750)
	lcd.invert(0)
	time.sleep_ms(750)
