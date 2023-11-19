package org.module_2.db;

import org.module_2.entity.City;
import org.module_2.entity.Neighbor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Db {
    static int cityCounter = 0;
    static int lastDigit = 0;

    public List<City> getCities() {
        return cities;
    }

    Path path = Paths.get("input.txt");

    List<String> input = Files.readAllLines(path);

    public City getCityById(int id) {
        return cities.get(id - 1);
    }

    public int getCityIdByName(String name) {
        int id = -1;
        for (int i = 0; i < cities.size(); i++) {
            if(cities.get(i).getName().equalsIgnoreCase(name)){
                id = i;
            }
        }
        return id;
    }

    List<City> cities = new ArrayList<City>(Integer.parseInt(input.get(0)));
    List<Neighbor> neighbors = new ArrayList<Neighbor>();


    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s.substring(0, 1));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getLastDigit() {
        return lastDigit;
    }

    public void createCities(List<String> input) {
        int counter = 0;
        int ways = 0;
        for (int i = 1; i < input.size(); i++) {
            if (isDigit(input.get(i))) {
                int length = input.get(i).length();
                if (length == 1) {
                    ways = Integer.parseInt(input.get(i));
                }
                if(length > 1){
                    List<Neighbor> neighbors1 = new ArrayList<Neighbor>();
                    for (int j = 0; j < ways; j++) {
                        Neighbor neighbor = new Neighbor();
                        neighbor.setId(input.get(i).substring(0, 1));
                        neighbor.setPrice(Integer.parseInt(input.get(i).substring(2)));
                        neighbors1.add(neighbor);
                        i++;
                    }
                    neighbors = neighbors1;
                    cities.get(cityCounter - 1).setNeighbors(neighbors);
                    lastDigit = i;
                }
            }

            if (!isDigit(input.get(i)) && cityCounter < Integer.parseInt(input.get(0))) {
                City city1 = new City();
                city1.setName(input.get(i));
                city1.setId(String.valueOf(counter + 1));
                cities.add(cityCounter, city1);
                cityCounter++;
            }
        }
    }

    public Db() throws IOException {
    }
}