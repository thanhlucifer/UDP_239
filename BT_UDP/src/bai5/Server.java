/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai5;

/**
 *
 * @author thanh
 */
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);

            System.out.println("Server dang cho ket noi...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String N = new String(receivePacket.getData(), 0, receivePacket.getLength());

                byte[] receiveData2 = new byte[1024];
                DatagramPacket receivePacket2 = new DatagramPacket(receiveData2, receiveData2.length);
                socket.receive(receivePacket2);

                String M = new String(receivePacket2.getData(), 0, receivePacket2.getLength());

                java.math.BigInteger bigN = new java.math.BigInteger(N);
                java.math.BigInteger bigM = new java.math.BigInteger(M);
                java.math.BigInteger sum = bigN.add(bigM);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = sum.toString().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

