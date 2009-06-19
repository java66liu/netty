/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @author tags. See the COPYRIGHT.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.netty.handler.codec.http;

import static org.jboss.netty.handler.codec.http.HttpCodecUtil.*;

import java.io.UnsupportedEncodingException;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Encodes an {@link HttpResponse} or an {@link HttpChunk} into
 * a {@link ChannelBuffer}.
 *
 * @author The Netty Project (netty-dev@lists.jboss.org)
 * @author Andy Taylor (andy.taylor@jboss.org)
 * @author Trustin Lee (tlee@redhat.com)
 * @version $Rev$, $Date$
 */
public class HttpResponseEncoder extends HttpMessageEncoder {

    /**
     * Creates a new instance.
     */
    public HttpResponseEncoder() {
        super();
    }

    @Override
    protected void encodeInitialLine(ChannelBuffer buf, HttpMessage message) {
        HttpResponse response = (HttpResponse) message;
        try {
            buf.writeBytes(response.getProtocolVersion().toString().getBytes("ASCII"));
            buf.writeByte(SP);
            buf.writeBytes(String.valueOf(response.getStatus().getCode()).getBytes("ASCII"));
            buf.writeByte(SP);
            buf.writeBytes(String.valueOf(response.getStatus().getReasonPhrase()).getBytes("ASCII"));
            buf.writeBytes(CRLF);
        } catch (UnsupportedEncodingException e) {
            throw (Error) new Error().initCause(e);
        }
    }
}
