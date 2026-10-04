package com.gestion.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MotivosMovimientoInventario {
    public static final String COMPRA = "Compra(s)";
    public static final String VENTA = "Venta(s)";
    public static final String DEVOLUCION_COMPRA = "Devolucion de compra(s)";
    public static final String DEVOLUCION_VENTA = "Devolucion de ventas(s)";
    public static final String AJUSTE = "Ajuste(s)";
    public static final String MERMA = "Merma(s)";
    public static final String VENCIMIENTO = "Vencimiento(s)";
    public static final String DANIO = "Daño(s)";
    public static final String INGRESO_INICIAL = "Ingreso inicial(es)";

    public static final ObservableList<String> MOTIVOS_ORIGINALES =
        FXCollections.observableArrayList(
            COMPRA,
            VENTA,
            DEVOLUCION_COMPRA,
            DEVOLUCION_VENTA,
            AJUSTE,
            MERMA,
            VENCIMIENTO,
            DANIO,
            INGRESO_INICIAL
        )
    ;
}



