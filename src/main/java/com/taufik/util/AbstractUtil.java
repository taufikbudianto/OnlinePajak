package com.taufik.util;

import com.taufik.model.TaxSchema;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author taufik.budiyanto
 * @date 05/01/2021
 * com.taufik.util
 */
public abstract class AbstractUtil {
    protected List<TaxSchema> getListTaxSchema(){
        List<TaxSchema> listTaxSchema = new ArrayList<>();
        String path = System.getProperty("user.dir")+ File.separator+"config"+File.separator;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path+"configtaxschema.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                TaxSchema taxSchema = new TaxSchema();
                String [] dataConfig =line.toString().split(";");
                for(int i =0;i<dataConfig.length-1;i++){
                    taxSchema.setMin(Double.valueOf(dataConfig[0]));
                    taxSchema.setTaxRate(Double.valueOf(dataConfig[2]));
                    if(dataConfig[1].equals("xx")){
                        taxSchema.setMax(Double.MAX_VALUE);
                    }else{
                        taxSchema.setMax(Double.valueOf(dataConfig[1]));
                    }

                }
                listTaxSchema.add(taxSchema);
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        listTaxSchema=listTaxSchema.stream().sorted((f2, f1)
                -> Double.compare(f2.getMin(), f1.getMin())).collect(Collectors.toList());
        return listTaxSchema;
    }

    protected Map<String,String> getRelief(){
        Map<String,String> map = new HashMap<>();
        String path = System.getProperty("user.dir")+ File.separator+"config"+File.separator;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path+"configtaxrelief.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] dataConfig =line.toString().split(";");
                map.put(dataConfig[0],dataConfig[1]);
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return map;
    }
}
