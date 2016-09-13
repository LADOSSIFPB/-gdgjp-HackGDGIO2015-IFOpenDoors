def do_connect():
    import network
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    if not wlan.isconnected():
        print('connecting to network...')
        wlan.connect('officinarum', '16062016%')
        while not wlan.isconnected():
            pass
    print('network config:', wlan.ifconfig())

import network, webrepl

do_connect()
webrepl.start()
