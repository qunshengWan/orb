/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.sun.corba.ee.impl.ior;

import com.sun.corba.ee.spi.ior.TaggedProfileTemplate ;

import com.sun.corba.ee.spi.orb.ORB ;

import org.omg.CORBA_2_3.portable.InputStream ;

/**
 * @author Ken Cavanaugh
 */
public class TaggedProfileTemplateFactoryFinderImpl extends
    IdentifiableFactoryFinderBase<TaggedProfileTemplate> 
{
    public TaggedProfileTemplateFactoryFinderImpl( ORB orb )
    { 
        super( orb ) ;
    }

    public TaggedProfileTemplate handleMissingFactory( int id, InputStream is) 
    {
        throw wrapper.taggedProfileTemplateFactoryNotFound( id ) ;
    }
}
