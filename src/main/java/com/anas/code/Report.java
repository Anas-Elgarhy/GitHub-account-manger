package com.anas.code;

import java.util.Date;

/**
 * @author <a href="https://github.com/Anas-Elgarhy">Anas-Elgarhy</a>
 * at 2022-02-18
 */
public interface Report {
    public String getName();
    public ReportType getType();
    public default Date createDate() {
        return new Date();
    }
}
