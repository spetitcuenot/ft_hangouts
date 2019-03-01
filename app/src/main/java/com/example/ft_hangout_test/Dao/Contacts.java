package com.example.ft_hangout_test.Dao;

public class Contacts {

    private long _id;
    private String _lastname;
    private String _firstname;
    private long _numobile;
    private long _nufix;
    private String  _mail;
    private String _address;



    public Contacts(long id, String lastname, String firstname, long numobile, long nufix, String  mail, String address){
        super();
        this.setId(id);
        this.setLastname(lastname);
        this.setFirstname(firstname);
        this.setNumobile(numobile);
        this.setNufix(nufix);
        this.setMail(mail);
        this.setAddress(address);
    }

    public Contacts() {

    }

    public Contacts(long id, String lastname, String firstname){
        this._id = id;
        this._lastname = lastname;
        this._firstname = firstname;

    }
    public Contacts(long id) {

        this._id = id;
    }


    public long getId(){
        return this._id;
    }

    public void setId(long id){ //definir si on doit donner la possibilite de modifier l'id ? je pense que non
        this._id = id;
    }


    public String getLastname() {
        return _lastname;
    }

    public void setLastname(String lastname) {
        this._lastname = lastname;
    }

    public String getFirstname() {
        return _firstname;
    }

    public void setFirstname(String firstname) {
        this._firstname = firstname;
    }

    public long getNumobile() {
        return _numobile;
    }

    public void setNumobile(long numobile) {
        this._numobile = numobile;
    }

    public long getNufix() {
        return _nufix;
    }

    public void setNufix(long nufix) {
        this._nufix = nufix;
    }

    public String getMail() {
        return _mail;
    }

    public void setMail(String mail) {
        this._mail = mail;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        this._address = address;
    }
}
