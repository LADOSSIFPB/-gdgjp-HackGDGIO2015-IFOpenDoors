import mfrc522
from os import uname


def do_read():

	if uname()[0] == 'WiPy':
		rdr = mfrc522.MFRC522("GP14", "GP16", "GP15", "GP22", "GP17")
	elif uname()[0] == 'esp8266':
		rdr = mfrc522.MFRC522(0, 2, 4, 5, 14)
	else:
		raise RuntimeError("Erro de disco")

	print("")
	print("Por favor, aproxime seu cartao")
	print("")

	try:
		while True:

			(stat, tag_type) = rdr.request(rdr.REQIDL)

			if stat == rdr.OK:

				(stat, raw_uid) = rdr.anticoll()

				if stat == rdr.OK:
					print("Cartao Detectado")
					print("  - tag: 0x%02x" % tag_type)
					print("  - ID	 : 0x%02x%02x%02x%02x" % (raw_uid[0], raw_uid[1], raw_uid[2], raw_uid[3]))
					print("")
					ID = ("0x%02x%02x%02x%02x"% (raw_uid[0], raw_uid[1], raw_uid[2], raw_uid[3]))

					if rdr.select_tag(raw_uid) == rdr.OK:

						key = [0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF]

						if rdr.auth(rdr.AUTHENT1A, 8, key, raw_uid) == rdr.OK:
							if rdr.read(8)[0] != 0:
								print("Cartao Cadatrado")
							else:
								print("Cartao Cadastrado")
							rdr.stop_crypto1()
							return ID;
						else:
							print("	Erro de Autenticacao")
					else:
						print("Erro")




	except:
		exit(1)
