package br.com.register.apiproject.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "cellphone", length = 20, nullable = false)
    private String cellphone;

    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;

    public UserModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id.equals(userModel.id) && name.equals(userModel.name) && email.equals(userModel.email) && cellphone.equals(userModel.cellphone) && password.equals(userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, cellphone, password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
