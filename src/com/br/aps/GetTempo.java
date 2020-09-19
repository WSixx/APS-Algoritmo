package com.br.aps;

public class GetTempo {
    private long tempoTotal;

    public GetTempo() {
    }

    public GetTempo(long tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public void contagem(long start, long end){
        tempoTotal = end - start;
        setTempoTotal(tempoTotal);
    }

    public long getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(long tempoTotal) {
        this.tempoTotal = tempoTotal;
    }


}
