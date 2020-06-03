package utils;

import com.mygdx.game.*;
import java.io.BufferedReader;

import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author antoniomejorado
 */
public class ReadandWrite {

    public static void Save(String strFileName, int healthLevel, int hungerLevel, int money) {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));
            writer.println("" + healthLevel + "/" + hungerLevel + "/" + money);
            writer.close();
        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }
    }

    public static void Load(String strFileName, ContagionGame game) {
        try {
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            int healthLevel = Integer.parseInt(datos[0]);
            int hungerLevel = Integer.parseInt(datos[1]);
            int money = Integer.parseInt(datos[2]);
            
            game.healthLevel=healthLevel;
            game.hungerLevel=hungerLevel;
            game.money=money;
            
            System.out.println("Se leyo  health = " + healthLevel + " y hunger = " + hungerLevel);
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


    }
}
