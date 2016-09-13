p = Pin(4, Pin.OUT)
portaServidorESP = 8080

def open(responseReceived):
	print('Aberto')
	p.high()
	
	auth = getAuth(responseReceived)

	if not auth:
		response = Response()
		response.notAuthorized()
		return response.build()

	data = ujson.dumps({'key':auth, 'ip':wlan.ifconfig()[0] + ":" + str(portaServidorESP)})
	data = POST(data, "/IFOpenDoors_SERVICE/door/checkKey", "192.168.0.8", 8080)

	if getCode(data)!=200:
		response = Response()
		response.notAuthorized()
		return response.build()

	response = Response()
	response.code(200)
	response.contentType("text/plain")
	response.data("Aberto")
	return response.build()
	
def close(responseReceived):
	print('Fechado')
	p.low()
	
	auth = getAuth(responseReceived)

	if not auth:
		response = Response()
		response.notAuthorized()
		return response.build()

	data = ujson.dumps({'key':auth, 'ip':wlan.ifconfig()[0] + ":" + str(portaServidorESP)})
	data = POST(data, "/IFOpenDoors_SERVICE/door/checkKey", "192.168.0.8", 8080)

	if getCode(data)!=200:
		response = Response()
		response.notAuthorized()
		return response.build()
		
	response = Response()
	response.code(200)
	response.contentType("text/plain")
	response.data("Fechado")
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

data = POST(data, "/IFOpenDoors_SERVICE/door/insert", "192.168.0.8", 8080)

p.low()

server = Server(portaServidorESP)
server.start(paths)
