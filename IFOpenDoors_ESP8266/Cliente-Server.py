p = Pin(4, Pin.OUT)
portaServidorESP = 8080

def open():
	p.high()
	print('Aberto')
	response = Response()
	response.code(200)
	response.contentType("text/plain")
	response.data("Ligado")
	return response.build()
def close():
	p.low()
	print('Fechado')
	response = Response()
	response.code(200)
	response.contentType("text/plain")
	response.data("Desligado")
	return response.build()
def isOn():
	response = Response()
	response.code(200)
	return response.build()

paths = {"/open":open,
	 "/close":close,
	 "/isOn":isOn}

p.high()
wlan = network.WLAN(network.STA_IF)
wlan.active(True)

if not wlan.isconnected():
	wlan.connect('Vilar', 'defarias')

while not wlan.isconnected():
	pass

data = ujson.dumps({'ip':wlan.ifconfig()[0] + ":" + str(portaServidorESP)})

POST(data, "/IFOpenDoors_SERVICE/door/insert", "192.168.0.8", 8080)

p.low()

server = Server(portaServidorESP)
server.start(paths)
