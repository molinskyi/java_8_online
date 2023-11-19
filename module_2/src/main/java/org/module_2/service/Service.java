package org.module_2.service;

import org.module_2.db.Db;
import org.module_2.entity.City;
import org.module_2.entity.Neighbor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Service {
    Path path = Paths.get("input.txt");
    Path pathOut = Paths.get("output.txt");

    List<String> input = Files.readAllLines(path);



    public Service() throws IOException {
    }

    private int findMinPrice(int start, int finish, int minPrice, List<City> cities, List<String> route) {
        if (start == finish) {
            return minPrice;
        }
        int currentPrice = Integer.MAX_VALUE;
        for (Neighbor neighbor : cities.get(start - 1).getNeighbors()) {
            if (!route.contains(cities.get(Integer.parseInt(neighbor.getId()) - 1).getName())) {
                route.add(cities.get(Integer.parseInt(neighbor.getId()) - 1).getName());
                int price = findMinPrice(Integer.parseInt(neighbor.getId()), finish, minPrice + neighbor.getPrice(), cities, route);
                if (price != Integer.MAX_VALUE) {
                    currentPrice = Math.min(currentPrice, price);
                }
                route.remove(route.size() - 1);
            }
        }

        return currentPrice;
    }

    public void findMinPrice() throws IOException {
        Db db = new Db();
        db.createCities(input);
        int startPoint = 0;
        int finishPoint = 0;
        int minPrice = 0;
        List<String> route = new ArrayList<>();
        route.add("");
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathOut), true));
        for (int i = db.getLastDigit(); i < input.size(); i++) {
            startPoint = db.getCityIdByName(input.get(i).substring(0, input.get(i).indexOf(" "))) + 1;
            finishPoint = db.getCityIdByName(input.get(i).substring(input.get(i).indexOf(" ") + 1, input.get(i).length())) + 1;
            route.set(0, db.getCities().get(startPoint - 1).getName());
            minPrice = findMinPrice(startPoint, finishPoint, 0, db.getCities(), route);
            writer.append(String.valueOf(minPrice));
            writer.newLine();
        }
        writer.close();
    }
}
