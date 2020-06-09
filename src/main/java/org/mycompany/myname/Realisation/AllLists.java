package org.mycompany.myname.Realisation;

import java.io.File;
import java.util.ArrayList;

public class AllLists {
    ArrayList listOfDir = new ArrayList();
    ArrayList listOfFiles = new ArrayList();
    String path;
    String parentPath;

    public AllLists(String path){
        this.path=path;
        fillLists();
    }

    public void fillLists(){
        File allFiles = new File(this.path);
        parentPath=allFiles.getParent();

        for (File el:allFiles.listFiles()){
            if (el.isDirectory())
                listOfDir.add(el);
            else
                listOfFiles.add(el);
        }
    }

    public ArrayList getDir(){
        return listOfDir;
    }

    public ArrayList getFiles(){
        for (Object el:listOfFiles){
            System.out.println(el);
        }
        return listOfFiles;
    }

    public String getCurDir(){
        return parentPath;
    }
}