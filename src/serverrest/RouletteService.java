/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

import java.util.Random;

/**
 *
 * @author delfo
 */
public class RouletteService {
    
    /**
     * Esegue l'operazione matematica richiesta
     * 
     * @param giocata La giocata effettuata (PARI o DISPARI)
     * @return 1 se la giocata è vincente, 0 se perdente
     * @throws IllegalArgumentException se la giocata non è valida
     */
    public static double logicaDiCalcolo(String giocata) 
            throws IllegalArgumentException {
        
        // Controllo se i parametri passati sono validi
                if (!parametriValidi(giocata)) {
            throw new IllegalArgumentException("Parametri non validi");

        }
        
        // Generazione di un numero random
        Random rnd = new Random();
        Integer num = rnd.nextInt(37); // Simula un numero casuale tra 0 e 36
        
        try {
            switch(giocata.toUpperCase()) {
                case "PARI":
                    // Logica per PARI
                    if (num % 2 == 0 && num != 0) {
                        return 1; // Vincita
                    } else {
                        return 0; // Perdita
                    }
                    
                case "DISPARI":
                    // Logica per DISPARI
                    if (num % 2 == 1 && num != 0) {
                        return 1; // Vincita
                    } else {
                        return 0; // Perdita
                    }
                    
                default:
                    throw new IllegalArgumentException(
                            "Opzione non valida. Opzione deve essere PARI o DISPARI");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Opzione non valida. Opzione deve essere PARI o DISPARI");
        }
    }

    // Metodo di validazione dei parametri
    private static boolean parametriValidi(String giocata) {
        if (giocata == null || giocata.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
