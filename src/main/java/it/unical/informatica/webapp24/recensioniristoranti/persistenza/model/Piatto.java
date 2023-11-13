package it.unical.informatica.webapp24.recensioniristoranti.persistenza.model;

import java.util.ArrayList;
import java.util.List;

public class Piatto {
    private Long id;
    private String nome;
    private Double prezzo;
    private List<Ristorante> ristoranti = new ArrayList<Ristorante>();

    public void addRistorante(Ristorante ristorante) {
        ristoranti.add(ristorante);
    }
    public void setRistoranti(List<Ristorante> ristoranti) {
        this.ristoranti = ristoranti;
    }

    public List<Ristorante> getRistoranti() {
        return ristoranti;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Double getPrezzo() { return prezzo; }

    public void setPrezzo(Double prezzo) { this.prezzo = prezzo; }

}
