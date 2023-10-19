package org.ficheros;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OperacionesCSV {
    public static Funko mostExpensiveFunko(List<Funko> list){
        return list.stream().sorted(((o1, o2) -> (int) (o2.getPrice() - o1.getPrice()))).limit(1).toList().get(0);
    }
    public static double medianPrice(List<Funko> list){
        return list.stream().mapToDouble(Funko::getPrice).average().orElse(0);
    }

    public static Map<String, List<Funko>> funkosByModel(List<Funko> list){
        return list.stream().collect(Collectors.groupingBy(Funko::getModel));
    }

    public static void amountOfFunkosByModel(List<Funko> list){
        funkosByModel(list).forEach((k, v) -> System.out.println(k + " - " + v.size()));
    }

    public static List<Funko> funkosReleasedIn2023(List<Funko> list, int year){
        return list.stream().filter(l -> l.getReleaseDate().getYear() == year).toList();
    }

}
