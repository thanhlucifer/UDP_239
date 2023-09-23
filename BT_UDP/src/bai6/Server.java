/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai6;

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

                double a = Double.parseDouble(new String(receivePacket.getData(), 0, receivePacket.getLength()));

                byte[] receiveData2 = new byte[1024];
                DatagramPacket receivePacket2 = new DatagramPacket(receiveData2, receiveData2.length);
                socket.receive(receivePacket2);

                double b = Double.parseDouble(new String(receivePacket2.getData(), 0, receivePacket2.getLength()));

                double sum = a + b;
                double difference = a - b;
                double product = a * b;
                double quotient = a / b;

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String response = "Tong: " + sum + "\nHieu: " + difference + "\nTich: " + product + "\nThuong: " + quotient;

                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

