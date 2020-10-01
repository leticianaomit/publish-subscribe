package br.com.pubsub.network;

import br.com.pubsub.Intermediary;
import br.com.pubsub.Util;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class I3 {
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException {
        Intermediary I3 = new Intermediary("I3", 1097);
        I3.addNeighbor("I4");
        I3.addNeighbor("P2");

        LocateRegistry.createRegistry(I3.getPort());
        Naming.rebind("I3", I3);
        I3.showStatus();
    }
}
