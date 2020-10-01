package br.com.pubsub.network;

import br.com.pubsub.Intermediary;
import br.com.pubsub.Util;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class I1 {
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException {
        Intermediary I1 = new Intermediary("I1", 1099);
        I1.addNeighbor("I2");
        I1.addNeighbor("I4");
        I1.addNeighbor("P1");

        LocateRegistry.createRegistry(I1.getPort());
        Naming.rebind("I1", I1);
        I1.showStatus();
    }
}
