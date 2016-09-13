from flask import Flask
import requests

url = "http://localhost:8080/IFOpenDoors_SERVICE/door/insert"

payload = "{\n    \"ip\":\"localhost:8085\"\n}"
headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "3d4dbbd8-d70b-e8f3-92fd-9e69faf254b0"
    }

response = requests.request("POST", url, data=payload, headers=headers)

print(response.text)

app = Flask(__name__)

@app.route("/isOn")
def isOn():
	print("ok")
	return "",200

@app.route("/open")
def open():
	print("Aberta")
	return "",200

@app.route("/close")
def close():
	print("Fechada")
	return "",200

app.run(port=8085)
