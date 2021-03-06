/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package corba.oneway;

import HelloApp._helloImplBase;
import java.util.Properties ;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

class helloServant extends _helloImplBase 
{
    public void shutdown()
    {
        System.err.println("In helloServant.shutdown, exiting..");
        System.exit(0);
    }

    public void sayHello()
    {
        System.err.println("In helloServant.sayHello(), waiting forever..");
        // Wait forever
        try {
            java.lang.Object sync = new java.lang.Object();
            synchronized (sync) {
                sync.wait();
            }
        } catch (Exception e) {}
    }
}

public class helloServer {

    public static void main(String args[])
    {
        try{
            // create and initialize the ORB
            Properties props = new Properties() ;
            props.put( "org.omg.CORBA.ORBClass", 
                       System.getProperty("org.omg.CORBA.ORBClass"));
            ORB orb = ORB.init(args, props);

            // create servant and register it with the ORB
            helloServant helloRef = new helloServant();
            orb.connect(helloRef);

            // get the root naming context
            org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            // bind the Object Reference in Naming
            NameComponent nc = new NameComponent("Hello", "");
            NameComponent path[] = {nc};
            ncRef.rebind(path, helloRef);

            System.out.println("Server is ready.");

            // wait for invocations from clients
            java.lang.Object sync = new java.lang.Object();
            synchronized (sync) {
                sync.wait();
            }

        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
            System.exit (1);
        }
    }
}
