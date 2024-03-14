package br.com.fiap.loja.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "tb_loja")
public class Loja {

    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Campo requerido!")
    @Size(min = 3, message = "O nome de ter no minimo 3 caracteres.")
    private String nome;
    @NotBlank(message = "Campo requerido!")
    @Size(min = 3, message = "O nome de ter no minimo 3 caracteres.")
    private String endereco;
    @NotBlank(message = "Campo requerido!")
    @Size(min = 3, message = "O nome de ter no minimo 3 caracteres.")
    private String contato;
    @NotBlank(message = "Campo requerido!")
    @Email(message = "E-Mail Inválido.")
    private String email;
    @NotBlank(message = "Campo requerido!")
    @Size(min = 12, message = "Insira um número de telefone válido.")
    private String telefone;

    //constructor
    public Loja(String nome, String endereco, String contato, String email, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.email = email;
        this.telefone = telefone;
    }

    public Loja() {}

    //getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    //equals & hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loja loja = (Loja) o;
        return id == loja.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //toString
    @Override
    public String toString() {
        return "Loja{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", contato='" + contato + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
