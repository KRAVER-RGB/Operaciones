package com.example.operaciones;

public interface Conversiones {

    public default double convertirStringADouble(String numero){
        return (Double.parseDouble(numero));
    }

}
