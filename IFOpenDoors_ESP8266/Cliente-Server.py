import RestServer
import network
from machine import Pin
import ujson

p = Pin(0, Pin.OUT)
ip = "192.168.0.1"

def open():
	p.high()
	response = Response()
	response.code(200)
	response.contentType("text/plain")
	response.data("Aberto")
	return response.build()
def close():
	p.low()
	response = Response()
	response.code(200)
	response.contentType("text/plain")
	response.data("Fechado")
	return response.build()

paths = {"/open":open,
	 	"/close":close}

wlan = network.WLAN(network.STA_IF)
wlan.active(True)
wlan.connect('Vilar', 'defarias')

while not wlan.isconnected():
	pass

data = ujson.dumps({'ip':wlan.ifconfig()[0]})

requisicao = "POST /IFOpenDoors_SERVICE/door/insert HTTP/1.0" + "\r\n" +
			 "Host: " + ip + "\r\n" +
			 "User-Agent: ESP" + "\r\n" +
			 "Accept: application/json" + "\r\n" +
			 "If-modified-since: Sat, 29 Oct 1999 19:43:31 GMT" + "\r\n" +
			 "Content-Type: application/json" + "\r\n" +
			 "Content-Length: " + len(data) + "\r\n" +
		 	 "Connection: keep-alive" + "\r\n" + "\r\n" + data

addr = lwip.getaddrinfo(ip, 8080)
s = lwip.socket()
s.connect(addr[0][-1])
s.send(requisicao.encode())
data = s.recv(1000)
s.close()

server = Server(8080)
server.start(paths)