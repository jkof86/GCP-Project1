package com.revature.models;


import java.sql.Date;

public class Reimbursement {
    private int id;
    private double amount;

    //might need to change this, we need this to be a timestamp
    private Date submitted;
    private Date resolved;

    private String description;
    private Receipt receipt;

    private int author;
    private int resolver;
    private int statusId;
    private int typeId;

    public Reimbursement(){

    }

    public Reimbursement(Double amount, Date submitted, Date resolved, String description, Receipt receipt,
                         int author, int resolver, int statusId, int typeId) {
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(Double amount, Date submitted, String description, int author, int statusId, int typeid) {

        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.author = author;
//        this.statusId = statusId;
        setStatusId(1);
        this.statusId = getStatusId();
        this.typeId = typeid;

    }

    public Reimbursement(Date resolved, int resolver, int statusId, int id) {
        this.resolved = resolved;
        this.resolver = resolver;
        this.statusId = statusId;
        this.id = id;
    }

    public String toString(){
        return "ID#" + this.id + " " +
                "Author: " + this.author + " " + "\n" +
                "Amount: " + this.amount + "\n" +
                "StatusId: " + this.statusId + "\n" +
                "TypeId: " + this.typeId + "\n" +
                "Submitted: " + this.submitted + "\n" +
                "Resolved: " + this.resolved + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
