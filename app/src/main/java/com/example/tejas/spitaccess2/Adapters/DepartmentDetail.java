package com.example.tejas.spitaccess2.Adapters;

/**
 * Created by Tejas on 01-07-2017.
 */

public class DepartmentDetail {

    public String dept_title,dept_programs,dept_intake,dept_hod,dept_hodemailaddress,dept_websiteurl;
    public String dept_theme;

    public DepartmentDetail(String mtitle,String mprogram,String mintake,String mhod,String mhodemail,String mweburl,String dept_theme)
    {
        this.dept_title=mtitle;
        this.dept_programs=mprogram;
        this.dept_intake=mintake;
        this.dept_hod=mhod;
        this.dept_hodemailaddress=mhodemail;
        this.dept_websiteurl=mweburl;
        this.dept_theme=dept_theme;
    }

}
