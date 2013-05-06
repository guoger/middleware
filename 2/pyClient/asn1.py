#!/usr/bin/python
'''
Description: write a first py client
Author: Niklas Semmler
'''
import sys
import os
from pyasn1.type import univ, namedtype
import socket
import random

class Log(object):
    """ substitute for log object """ 

    def warn(self, msg):
        print "WARNING: %s" % msg

    def info(self, msg):
        print "INFO: %s" % msg

class Client(object):
    """ takes care of all socket/connection stuff """
    def __init__(self, src_address = ("127.0.0.1", 5001), dst_address = ("127.0.0.1", 5000)):
        self.log = Log()
        self.sock = self._create_socket(src_address)
        self._connect(dst_address)

    def _create_socket(self, address=None):
        """ create socket
        """
        self.log.info('creating socket')
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        except socket.error:
            self.log.warn('Socket initialization failed')
            raise
        else:
            if address:
                self.log.info('binding to %s at %d' % address)
                try:
                    sock.bind(address)
                except socket.error:
                    self.log.warn('Binding to address failed')
                    raise
        return sock
    
    def _connect(self, address):
        self.log.info('connecting to %s at %d' % address)
        try:
            self.sock.connect(address)
        except socket.error:
            self.log.warn('Could not connect') 

    def send(self, msg):
        self.log.info("sending message")
        self.sock.send(msg)

    def close(self):
        self.log.info("closing connection")
        self.sock.close()

class Message(univ.Sequence):
    def __init__(self, number=None, string=None):
        univ.Sequence.__init__(self)
        self._componentType = namedtype.NamedTypes(
            namedtype.NamedType('code', univ.Integer()),
            namedtype.NamedType('randStr', univ.OctetString())
        )
        self._componentTypeLen = len(self._componentType)
        if number:
            self.setComponentByName("code", number)
        if string:
            self.setComponentByName("randStr", string)

class MessageOpen(Message):
    def __init__(self):
        Message.__init__(self, number=random.randint(0,1000))
        
class MessageResponse(Message):
    def __init__(self, recvstr):
        Message.__init__(self, number=recvstr.swapcase())

m = MessageOpen()
print m.prettyPrint()
#main = Client()
#main.send("stuff")
#main.close()
