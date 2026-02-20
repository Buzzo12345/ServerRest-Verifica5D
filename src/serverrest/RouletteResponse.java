/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

/**
 *
 * @author delfo
 */
public class RouletteResponse {

    private String giocata;
    private Integer numero;
    private String risultato;

    //Costruttore vuoto necessario per GSON
    public RouletteResponse() {
    }
    
    // Costruttore con parametri
    public RouletteResponse(String giocata, Integer numero, String risultato) {
        this.giocata = giocata;
        this.numero = numero;
        this.risultato = risultato;
    }
    
    // Getter
    public String getGiocata() {
        return giocata;
    }
    public Integer getNumero() {
        return numero;
    }
    public String getRisultato() {
        return risultato;
    }

    
    // Setter
    public void setGiocata(String giocata) {
        this.giocata = giocata;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public void setRisultato(String risultato) {
        this.risultato = risultato;
    }

    //ToString
    @Override
    public String toString() {
        return "RouletteResponse{" +
                "giocata='" + giocata + '\'' +
                ", numero=" + numero +
                ", risultato='" + risultato + '\'' +
                '}';
    }
}
