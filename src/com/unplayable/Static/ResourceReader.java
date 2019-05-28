package com.unplayable.Static;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {
    private static ResourceReader instance;

    private ResourceReader(){

    }

    public static ResourceReader getInstance(){
        if (instance == null){
            instance = new ResourceReader();
        }
        return instance;
    }

    public List<String> getResourceDirectory(String path) throws IOException {
        List<String> contents = new ArrayList<>();
        try {
            InputStream in = this.getClass().getResourceAsStream(path);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String resource;
            while ((resource = buffer.readLine()) != null) {
                contents.add(Paths.get(path, resource).toString().replace("\\", "/"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return contents;
        }
    }
}
