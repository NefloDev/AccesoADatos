package org.ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Backup {

    private static final String PATH = ".\\data";
    private static final String FILEPATH = PATH + "\\funkos.dat";

    public static void serialize(List<Funko> list) {

        createIfNotExists();

        ObjectOutputStream oos = null;
        FileOutputStream fs = null;

        try{
            fs = new FileOutputStream(FILEPATH);
            oos = new ObjectOutputStream(fs);
            for (Funko o: list) {
                oos.writeObject(o);
            }
            oos.close();
            fs.close();
        }catch (Exception e){
            System.err.println("Couldn't serialize");
        }finally {
            try {
                if (oos != null){
                    oos.close();

                }
                if(fs != null){
                    fs.close();
                }
            } catch (IOException e) {
                System.err.println("Error encountered while exiting the program");
            }
        }

    }

    public static List<Funko> deserialize(){

        createIfNotExists();

        ObjectInputStream ois = null;
        FileInputStream fi = null;
        List<Funko> list = new ArrayList<>();
        Funko funko;
        try{
            fi = new FileInputStream(FILEPATH);
            ois = new ObjectInputStream(fi);
            try{
                do {
                    funko = (Funko) ois.readObject();
                    if(funko != null){
                        list.add(funko);
                    }
                }while (funko != null);
            }catch (Exception e){
                System.out.println("Finished serialization");
            }
            ois.close();
            fi.close();
        }catch (Exception e){
            System.err.println("Couldn't serialize");
        }finally {
            try {
                if (ois != null){
                    ois.close();

                }
                if(fi != null){
                    fi.close();
                }
            } catch (IOException e) {
                System.err.println("Error encountered while exiting the program");
            }
        }
        return list;
    }

    private static void createIfNotExists(){
        File f = new File(FILEPATH);
        File dir = new File(PATH);
        try{
            if (!f.exists()){
                dir.mkdirs();
            }
            f.createNewFile();
        }catch (IOException e){
            System.out.println("Couldn't create new file");
        }
    }

}
