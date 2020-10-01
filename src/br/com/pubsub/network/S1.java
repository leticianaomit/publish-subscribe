package br.com.pubsub.network;

import br.com.pubsub.Client;
import br.com.pubsub.Util;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class S1 {
    public static void main(String[] args) throws RemoteException, java.net.UnknownHostException, MalformedURLException, NotBoundException {
        Client S1 = new Client("S1", "Andressa", "I2", 1095);

        LocateRegistry.createRegistry(S1.getPort());
        Naming.rebind("S1", S1);
        S1.showStatus();
        S1.showSubscriberMenu("P1");
    }
}
