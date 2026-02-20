/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package serverrest;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;



/**
 * Server REST per la calcolatrice
 * 
 * @author delfo
 */
public class ServerRest {

    /**
     * Avvia il server REST sulla porta specificata
     * 
     * @param porta la porta su cui avviare il server
     */
    public static void avviaServer(int porta) {
        try {
            // Crea il server sulla porta specificata
            HttpServer server = HttpServer.create(new InetSocketAddress(porta), 0);
            
            // Registra gli handler per gli endpoint
            server.createContext("/api/roulette/paridispari/post", new RoulettePostHandler());
            server.createContext("/api/roulette/paridispari/get", new RouletteGetHandler());
            
            // Endpoint di benvenuto
            server.createContext("/", ServerRest::gestisciBenvenuto);
            
            // Avvia il server
            server.setExecutor(null); // Usa il default executor
            server.start();
            
            // Messaggi di conferma
            System.out.println("==============================================");
            System.out.println("  Server REST con GSON avviato!");
            System.out.println("==============================================");
            System.out.println("Porta: " + porta);
            System.out.println();
            System.out.println("Endpoint disponibili:");
            System.out.println("  - POST: http://localhost:" + porta + "/api/roulette/paridispari/post");
            System.out.println("  - GET:  http://localhost:" + porta + "/api/roulette/paridispari/get");
            System.out.println("  - Info: http://localhost:" + porta + "/");
            System.out.println();
            System.out.println();
            System.out.println("Premi Ctrl+C per fermare il server");
            System.out.println("==============================================");
            
        } catch (IOException e) {
            System.err.println("Errore nell'avvio del server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Gestisce l'endpoint di benvenuto che fornisce informazioni sull'API
     * 
     * @param exchange l'oggetto HttpExchange per gestire la richiesta/risposta
     * @throws IOException in caso di errori durante la comunicazione
     */
    private static void gestisciBenvenuto(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        Map<String, Object> info = new HashMap<>();
        info.put("messaggio", "Benvenuto alla Roulette Pari/Dispari API!");
        info.put("versione", "1.0.0");
        info.put("tecnologia", "Java + GSON");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("POST", "http://localhost:8080/api/roulette/paridispari/post");
        endpoints.put("GET", "http://localhost:8080/api/roulette/paridispari/get");
        info.put("endpoints", endpoints);
        
        Map<String, String> giocate = new HashMap<>();
        giocate.put("pari", "PARI");
        giocate.put("dispari", "DISPARI");
        info.put("giocate_supportate", giocate);
        
        String jsonRisposta = gson.toJson(info);
        
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        byte[] bytes = jsonRisposta.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.getResponseBody().close();
    }
}