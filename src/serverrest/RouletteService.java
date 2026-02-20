/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;


/**
 *
 * @author delfo
 */
public class RouletteService {
    
    /**
     * Esegue l'operazione matematica richiesta
     * 
     * @param giocata La giocata effettuata (PARI o DISPARI)
     * @return Il risultato della giocata
     * @throws IllegalArgumentException se la giocata non è valida
     */
    public static Integer logicaDiCalcolo(String giocata, Integer numero) 
            throws IllegalArgumentException {
        
        // Controllo se i parametri passati sono validi
                if (!parametriValidi(giocata, numero)) {
            throw new IllegalArgumentException("Parametri non validi");

        }
        
        try {
            switch(giocata.toUpperCase()) {
                case "PARI":
                    // Logica per PARI
                    if (numero % 2 == 0 && numero != 0) {
                        return 1; // Vincita
                    } else {
                        return 0; // Perdita
                    }
                    
                case "DISPARI":
                    // Logica per DISPARI
                    if (numero % 2 == 1 && numero != 0) {
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
    private static boolean parametriValidi(String giocata, Integer numero) {
        if (giocata == null || giocata.trim().isEmpty()) {
            return false;
        }

        if (numero == null || numero < 0 || numero > 36) {
            return false;
        }

        return true;
    }
}
