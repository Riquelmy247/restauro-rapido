package com.example.prova1clenio;

public class User {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private int pontos;
    private int permissao;
    private boolean present;

    public User() {}

    public User(String nome, String senha, String email, int pontos, int permissao) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.pontos = pontos;
        this.permissao = permissao;
    }

    public User(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.pontos = 0;
    }

    public User(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.pontos = 0;
    }

    public User(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPermissao() {
        return permissao;
    }

    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    // Método toString ajustado para mostrar apenas nome e pontos
    @Override
    public String toString() {
        return "Nome: " + nome + ", Pontos: " + pontos;
    }
}
