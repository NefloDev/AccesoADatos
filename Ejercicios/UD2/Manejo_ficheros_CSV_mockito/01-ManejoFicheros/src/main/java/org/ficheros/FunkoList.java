package org.ficheros;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunkoList {

    private List<Funko> funkos;

    public FunkoList(List<Funko> list){
        funkos = list;
    }

    public Funko mostExpensiveFunko(){
        return OperacionesCSV.mostExpensiveFunko(funkos);
    }

    public double medianPrice(){
        return OperacionesCSV.medianPrice(funkos);
    }

    public Map<String, List<Funko>> funkosByModel(){
        return OperacionesCSV.funkosByModel(funkos);
    }

    public void amountOfFunkosByModel(){
        OperacionesCSV.funkosByModel(funkos);
    }

    public List<Funko> funkosReleasedIn2023(){
        return OperacionesCSV.funkosReleasedIn2023(funkos);
    }

}
