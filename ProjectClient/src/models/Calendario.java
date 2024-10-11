package models;

import java.time.LocalDateTime;
import java.util.Map;

public class Calendario {
    private Map<String, GP> gps; // (nomeGP-GP)

    public Calendario() {
        super();
    }

    public void aggiungiGP(GP gp) {
        this.gps.put(gp.getNome(), gp);
    }

    public void eliminaGP(String nome) {
        this.gps.remove(nome);
    }

    public Map<String, GP> getGps() {
        return gps;
    }

    public GP getGp(String nome) {
        return this.gps.get(nome);
    }

    public void setGps(Map<String, GP> gps) {
        this.gps = gps;
    }

    // Metodo per ottenere l'ultimo GP svolto prima della data attuale
    public GP lastGp() {
        LocalDateTime now = LocalDateTime.now();
        GP lastGp = null;

        for (GP gp : gps.values()) {
            if (gp.getData().isBefore(now)) {  // Verifica se il GP è già svolto
                if (lastGp == null || gp.getData().isAfter(lastGp.getData())) {
                    lastGp = gp;  // Aggiorna lastGp con il più recente prima di oggi
                }
            }
        }

        return lastGp;  // Restituisce il GP più recente o null se non esiste
    }
}
