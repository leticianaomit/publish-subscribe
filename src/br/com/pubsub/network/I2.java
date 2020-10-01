package br.com.pubsub.network;

import br.com.pubsub.Intermediary;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class I2 {
    public static void main(String[] args) throws RemoteException, MalformedURLException, UnknownHostException {
        Intermediary I2 = new Intermediary("I2", 1098);
        I2.addNeighbor("I1");
        I2.addNeighbor("S1");
        I2.addNeighbor("S2");

        LocateRegistry.createRegistry(I2.getPort());
        Naming.rebind("I2", I2);
        I2.showStatus();
    }
}
