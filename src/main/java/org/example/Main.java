package org.example;

import java.io.IOException;
import java.math.BigInteger;
import java.net.*;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) throws IOException {

        String msg = "The information security is of significant importance to ensure the privacy of communications";
        BigInteger msgCifrada;
        String msgDecifrada = null;
        BigInteger n, d, e;
        int bitLen = 2048;

        // Escolher de forma aleatoria dois numeros primos grandes p e q
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitLen / 2, 100, r);
        BigInteger q = new BigInteger(bitLen / 2, 100, r);

        // compute n = p * q
        n = p.multiply(q);

        // computar a função totiente phi(n) = (p - 1) (q - 1)
        BigInteger m = (p.subtract(BigInteger.ONE))
                .multiply(q.subtract(BigInteger.ONE));

        // Escolher um inteiro "e", 1 < "e" < phi(n), "e" e phi(n) sejam primos entre si.
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));

        // d seja inverso multiplicativo de "e"
        d = e.modInverse(m);

        System.out.println("p:" + p);
        System.out.println("q:" + q);
        System.out.println("n:" + n);
        System.out.println("e:" + e);
        System.out.println("d:" + d);

        // mensagem cifrada - RSA_encrypt()
        msgCifrada = new BigInteger(msg.getBytes()).modPow(e, n);
        System.out.println("Mensagem cifrada: " + msgCifrada);

        udpProtocol(msgCifrada, d, n);

        // mensagem decifrada - RSA_decrypt()
        msgDecifrada = new String(new BigInteger(msgCifrada.toByteArray()).modPow(d, n).toByteArray());

        System.out.println("Mensagem decifrada: " + msgDecifrada);
    }

    public static void udpProtocol(BigInteger msgCifrada, BigInteger d, BigInteger n) throws IOException {

        byte[] msgCifradaBytes = msgCifrada.toByteArray();
        byte[] dBytes = d.toByteArray();
        byte[] nBytes = n.toByteArray();

        InetAddress IPAddress = InetAddress.getByName("localhost");
        int port = 12500;
        DatagramSocket socket = new DatagramSocket();

        // Enviando a mensagem cifrada
        DatagramPacket pacoteEnvioMsgCifrada = new DatagramPacket(msgCifradaBytes, msgCifradaBytes.length, IPAddress, port);
        socket.send(pacoteEnvioMsgCifrada);

        // Enviando a chave 'd'
        DatagramPacket pacoteEnvioChaveD = new DatagramPacket(dBytes, dBytes.length, IPAddress, port);
        socket.send(pacoteEnvioChaveD);

        // Enviando a chave 'n'
        DatagramPacket pacoteEnvioChaveN = new DatagramPacket(nBytes, nBytes.length, IPAddress, port);
        socket.send(pacoteEnvioChaveN);

        socket.close();
    }
}
