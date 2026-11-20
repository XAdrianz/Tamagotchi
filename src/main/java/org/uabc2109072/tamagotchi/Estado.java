package org.uabc2109072.tamagotchi;

public enum Estado {
    BIEN("se siente bien"),
    NECESITA_ATENCION("necesita atencion"),
    MAL("se siente mal"),
    FALLECIDO("ha fallecido");

    private final String texto;

    Estado(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}