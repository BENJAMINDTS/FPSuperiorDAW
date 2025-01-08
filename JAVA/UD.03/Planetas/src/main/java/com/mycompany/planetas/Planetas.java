package com.mycompany.planetas;

import com.mycompany.planetas.Metodos.tipo_Planeta;

public class Planetas {

    public static void main(String[] args) {
        
        Metodos tierra = new Metodos("Tierra", 1, 5972000000000000000000000.0, 
                1083210000000., 149600000, 149600000, true, 
                tipo_Planeta.TERRESTRE);
        
        Metodos ogle2016blg1195lb = new Metodos("OGLE-2016-BLG-1195Lb", 0,
                2000000000000.0, 10000000000.0, 2000000000, 1300000, false,
                tipo_Planeta.GASEOSO);
        tierra.imprimir();
        ogle2016blg1195lb.imprimir();
    }   
    
}
