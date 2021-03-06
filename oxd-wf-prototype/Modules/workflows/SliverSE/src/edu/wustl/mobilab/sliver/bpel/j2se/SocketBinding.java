/* Sliver - a BPEL execution engine for lightweight and mobile devices.
 * Copyright (C) 2006, Washington University in Saint Louis
 * By Gregory Hackmann.
 *
 * Washington University states that Sliver is free software;
 * you can redistribute it and/or modify it under the terms of
 * the current version of the GNU Lesser General Public License
 * as published by the Free Software Foundation.
 *
 * Sliver is distributed in the hope that it will be useful, but
 * THERE ARE NO WARRANTIES, WHETHER ORAL OR WRITTEN, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO, IMPLIED WARRANTIES OF
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR USE.
 *
 * YOU UNDERSTAND THAT SLIVER IS PROVIDED "AS IS" FOR WHICH NO
 * WARRANTIES AS TO CAPABILITIES OR ACCURACY ARE MADE. THERE ARE NO
 * WARRANTIES AND NO REPRESENTATION THAT SLIVER IS FREE OF
 * INFRINGEMENT OF THIRD PARTY PATENT, COPYRIGHT, OR OTHER
 * PROPRIETARY RIGHTS.  THERE ARE NO WARRANTIES THAT SOFTWARE IS
 * FREE FROM "BUGS", "VIRUSES", "TROJAN HORSES", "TRAP DOORS", "WORMS",
 * OR OTHER HARMFUL CODE.
 *
 * YOU ASSUME THE ENTIRE RISK AS TO THE PERFORMANCE OF SOFTWARE AND/OR
 * ASSOCIATED MATERIALS, AND TO THE PERFORMANCE AND VALIDITY OF
 * INFORMATION GENERATED USING SOFTWARE. By using Sliver you agree to
 * indemnify, defend, and hold harmless WU, its employees, officers and
 * agents from any and all claims, costs, or liabilities, including
 * attorneys fees and court costs at both the trial and appellate levels
 * for any loss, damage, or injury caused by your actions or actions of
 * your officers, servants, agents or third parties acting on behalf or
 * under authorization from you, as a result of using Sliver.
 *
 * See the GNU Lesser General Public License for more details, which can
 * be found here: http://www.gnu.org/copyleft/lesser.html
 */

package edu.wustl.mobilab.sliver.bpel.j2se;

import java.io.*;
import java.net.*;

import edu.wustl.mobilab.sliver.bpel.*;
import edu.wustl.mobilab.sliver.soap.*;
import edu.wustl.mobilab.sliver.soap.j2se.*;

/**
 * Binds remote endpoints to raw TCP/IP sockets.
 * 
 * @author Greg Hackmann
 */
public class SocketBinding implements Binding
{
	/**
	 * The remote host's address.
	 */
	private final String host;
	/**
	 * The service's port on the remote host.
	 */
	private final int port;

	/**
	 * Creates a new SocketBinding.
	 * 
	 * @param host the remote host's address
	 * @param port the service's port on the remote host
	 */
	public SocketBinding(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	
	public Channel openChannel() throws IOException
	{
		Socket socket = new Socket(host, port);
		return new SocketChannel(socket);
	}
	
	public String toString()
	{
		return "SocketBinding(" + host + ", " + port + ")";
	}
}
