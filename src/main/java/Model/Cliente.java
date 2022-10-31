package Model;
/*
GRUPO 11

Matheus Perez Brassarotto RA: 821129320
Eduardo de Santana RA: 821224129
Victor Costa RA: 821140150
Italo  da Silva RA: 82118114
Lidyane Feitosa RA: 821224129
Giovanna LourenÃ§o RA: 821117542
*/

import javax.swing.*;

public class Cliente {

    //Atributos
    private int id;
    private String nome;
    private String cpf;

    //Construtor
    public Cliente() {
    }

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    

    // Set e Get
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
