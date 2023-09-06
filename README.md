# Desafio RSA – Criptografia Assimétrica com Java

![Badge](https://img.shields.io/badge/version-1.0.0-green)
![Badge](https://img.shields.io/badge/license-MIT-blue)

## Table of Contents

- [Introdução](#introdução)
- [Pré-requisitos](#prerequisitos)
- [Algoritmo](#algoritmo)
- [Equipe](#contribuições)

## Introdução

Este código é um exemplo de programa Java que usa o protocolo de transporte UDP para enviar uma mensagem cifrada e chaves necessárias para decifrar a mensagem que implementa a criptografia RSA.
O código gera números primos, calcula as chaves públicas e privadas do algoritmo RSA e cifra a mensagem. 
Em seguida, envia a mensagem cifrada e as chaves pela rede utilizando o protocolo UDP. 
O servidor (https://github.com/guiTavares13/challenge-rsa-java-decrypted) recebe esses dados, decifra a mensagem e a exibe na tela.


### Pré-requisitos

- IDE que mais goste
- JDK 17
- Git (opcional)


### Algoritmo


1. Primeiro, ele define uma mensagem e variáveis relacionadas à criptografia RSA.
2. Gera números primos `p` e `q` com um tamanho específico de bits (`bitLen`).
3. Calcula o valor `n` como a multiplicação de `p` e `q`.
4. Calcula a função totiente `phi(n)` como (p - 1)(q - 1).
5. Escolhe um número inteiro `e` tal que `e` seja primo em relação a `phi(n)`.
6. Encontra `d`, o inverso multiplicativo de `e` módulo `phi(n)`.
7. Imprime os valores gerados: p, q, n, e, d.
8. Cifra a mensagem original usando RSA_encrypt() e armazena no objeto BigInteger `msgCifrada`.
9. Chama a função `udpProtocol(msgCifrada, d, n)` para enviar a mensagem cifrada e as chaves para o receptor.
10. Decifra a mensagem usando RSA_decrypt() e imprime a mensagem decifrada.

A função `udpProtocol(BigInteger msgCifrada, BigInteger d, BigInteger n)` faz o seguinte:

1. Converte os objetos `BigInteger` msgCifrada, d e n em arrays de bytes.
2. Define endereço IP e porta do destinatário.
3. Cria um socket UDP (DatagramSocket).
4. As próximas três partes enviam, respectivamente, a mensagem cifrada, a chave `d` e a chave `n`, fazendo:
   - Criação de um pacote (DatagramPacket) com o array de bytes, endereço IP e porta.
   - Enviando o pacote pelo socket usando a função `socket.send()`.

5. Fecha o socket.

### Equipe

- Guilherme Tavares
- Roger Kenich
- Yago Jose
- Carlos Alexandre
- Felipe Kevin

Engenharia de Computação 10° Semestre
Disciplina: Tópicos Avançados em Redes de Computadores
Prof° Fabio Henrique Cabrini
