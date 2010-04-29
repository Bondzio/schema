/*
* Copyright (c) 2009 CSC Scandihealth
* P.O. Pedersenvej 2, DK-8200 Denmark
* All rights reserved.
* This software is the confidential and proprietary information of
* CSC Scandihealth. ("Confidential Information").  You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with CSC Scandihealth.
* @author MAH
*/

package com.scandihealth.olympicsc.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * TODO Add javadoc description...
 */
public class UTF8Filter implements Filter {
    public static final String COPYRIGHT = "Copyright 2009 CSC Scandihealth";
    /**
     * @noinspection UNUSED_SYMBOL
     */
    private final static String RCS_ID = "$Id: RCS_ID.txt,v 1.3 2007/07/19 09:24:17 ths Exp $";

    private String m_encoding;

    public void init(FilterConfig aConfig) throws ServletException {

        m_encoding = aConfig.getInitParameter("requestEncoding");
        if (m_encoding == null) {
            m_encoding = "UTF-8";
        }

    }

    public void doFilter(ServletRequest aRequest, ServletResponse aResponse, FilterChain aNext) throws IOException, ServletException {
        // Respect the client-specified character m_encoding // (see HTTP specification section 3.4.1) if(null == aRequest.getCharacterEncoding())
        aRequest.setCharacterEncoding(m_encoding);
        aNext.doFilter(aRequest, aResponse);

    }

    public void destroy() {
    }
}
