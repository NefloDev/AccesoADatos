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
        funkosByModel(list).entrySet().forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue().size()));
    }

    public static List<Funko> funkosReleasedIn2023(List<Funko> list){
        return list.stream().filter(l -> l.getReleaseDate().getYear() == 2023).toList();
    }

}
