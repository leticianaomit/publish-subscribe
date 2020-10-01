package br.com.pubsub.network;

import br.com.pubsub.Client;
import br.com.pubsub.Util;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class S2 {
    public static void main(String[] args) throws RemoteException, java.net.UnknownHostException, MalformedURLException, NotBoundException {
        Client S2 = new Client("S2", "João", "I2", 1094);

        LocateRegistry.createRegistry(S2.getPort());
        Naming.rebind("S2", S2);
        S2.showStatus();
        S2.showSubscriberMenu("P2");
    }
}
