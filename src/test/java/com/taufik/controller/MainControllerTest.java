package com.taufik.controller;
import com.taufik.service.Pph21Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author taufik.budiyanto
 * @date 05/01/2021
 * com.taufik.controller
 */
@SpringBootTest
public class MainControllerTest {
    @Autowired
    private Pph21Service pph21Service;
    private String path = System.getProperty("user.dir")+ File.separator+"test"+File.separator;

    @DisplayName("Taxation Exclude Relief")
    @Test
    public void testExcludeRelief() throws Exception {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path+"exrelief.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] data =line.toString().split(";");
                assertEquals(Double.valueOf(data[0]), pph21Service.taxation(Double.valueOf(data[1]),false,""));
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    @DisplayName("Taxation Include Relief")
    @Test
    public void testIncludeRelief() throws Exception {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path+"withrelief.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] data =line.toString().split(";");
                assertEquals(Double.valueOf(data[0]), pph21Service.taxation(Double.valueOf(data[1]),true,data[2]));
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
