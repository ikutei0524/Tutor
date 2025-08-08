package com.gtalent.tutor.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 對應 auto_increment
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd") // 可選，如果你資料表有 pwd 欄位才加
    private String pwd;

    // ✅ 無參數建構子：JPA 必須要有
    public User() {}

    // 有參數建構子：選擇性
    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // ✅ Getter / Setter（必要）
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

