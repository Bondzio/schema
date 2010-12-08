package com.scandihealth.olympicsc.utilities;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 07-12-2010
 * Time: 09:21:32
 * To change this template use File | Settings | File Templates.
 */
@Name("resetBean")
@Scope(ScopeType.SESSION)
public class ResetBean {
    private String abc;

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }
}
