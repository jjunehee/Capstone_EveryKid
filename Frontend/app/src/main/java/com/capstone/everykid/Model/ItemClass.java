package com.capstone.everykid.Model;


import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name="item")
public class ItemClass {
    @PropertyElement
    String stcode;

    @PropertyElement
    String crname;

    @PropertyElement
    String crtel;

    @PropertyElement
    String crfax;

    @PropertyElement
    String craddr;

    @PropertyElement
    String crhome;

    @PropertyElement
    int crcapat;

    public String getStcode() {
        return stcode;
    }

    public void setStcode(String stcode) {
        this.stcode = stcode;
    }

    public String getCrname() {
        return crname;
    }

    public void setCrname(String crname) {
        this.crname = crname;
    }

    public String getCrtel() {
        return crtel;
    }

    public void setCrtel(String crtel) {
        this.crtel = crtel;
    }

    public String getCrfax() {
        return crfax;
    }

    public void setCrfax(String crfax) {
        this.crfax = crfax;
    }

    public String getCraddr() {
        return craddr;
    }

    public void setCraddr(String craddr) {
        this.craddr = craddr;
    }

    public String getCrhome() {
        return crhome;
    }

    public void setCrhome(String crhome) {
        this.crhome = crhome;
    }

    public int getCrcapat() {
        return crcapat;
    }

    public void setCrcapat(int crcapat) {
        this.crcapat = crcapat;
    }
}
