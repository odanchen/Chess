package root.ui.graphics.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class reader {
    public String fileReader(){
        String TogSet = "";
        try{
            File myObj = new File("src/root/settings.txt");
            Scanner Reader = new Scanner(myObj);
            TogSet = Reader.nextLine();
            System.out.println(TogSet);
        }catch (FileNotFoundException e){
            System.out.println("no settings saved");
            System.out.println(e);
        }

        if(Objects.equals(TogSet, "file toggle:true")){

        }
        return "in progress";
    }
}
