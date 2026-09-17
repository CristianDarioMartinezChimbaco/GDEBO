package com.gestion.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UnidadesMedida {
    public static final String KILOGRAMO = "Kilogramo";
    public static final String GRAMO = "Gramo";
    public static final String LIBRA = "Libra";
    public static final String LITRO = "Litro";
    public static final String MILILITRO = "Mililitro";
    public static final String METRO = "Metro";
    public static final String CENTIMETRO = "Centímetro";
    public static final String UNIDAD = "Unidad";

    public static final ObservableList<String> UNIDADES_ORIGINALES =
        FXCollections.observableArrayList(
            KILOGRAMO,
            GRAMO,
            LIBRA,
            LITRO,
            MILILITRO,
            UNIDAD,
            METRO,
            CENTIMETRO
        )
    ;
}
