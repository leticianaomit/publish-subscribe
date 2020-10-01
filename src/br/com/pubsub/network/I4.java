package br.com.pubsub.network;

import br.com.pubsub.Intermediary;
import br.com.pubsub.Util;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class I4 {
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException {
        Intermediary I4 = new Intermediary("I4",1096);
        I4.addNeighbor("I1");
        I4.addNeighbor("I3");
        I4.addNeighbor("S3");

        LocateRegistry.createRegistry(I4.getPort());
        Naming.rebind("I4", I4);
        I4.showStatus();
    }
}
