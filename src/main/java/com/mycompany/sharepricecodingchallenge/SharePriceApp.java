/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sharepricecodingchallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author johnston
 */
public class SharePriceApp {

    public static void main(String[] args) {
        File sharePriceFile = new File("ChallengeSampleDataSet1.txt");

        try {
            Scanner input = new Scanner(sharePriceFile);
            input.useDelimiter(",");

            ArrayList<Double> sharePriceList = new ArrayList<>();

            while (input.hasNext()) {
                sharePriceList.add(input.nextDouble());
            }

            double currentMaximumDifference = 0;
            double priceDifference;

            int dayOfCurrentMaximumDifference = 0;
            int dayOfOptimisedMinimum = 0;
            double sharePriceOnBuyDay = 0;
            double sharePriceOnSellDay = 0;

            for (int i = 0; i < sharePriceList.size() - 1; i++) {
                //Find the positive difference between every element and every element to the right of it.

                for (int j = i + 1; j < sharePriceList.size(); j++) {

                    if (sharePriceList.get(j) <= sharePriceList.get(i)) {
                        //Price has gone down, not up. Disregard. Not the best idea to compare doubles like this.
                        continue;
                    } else {
                        //Take the difference of the prices
                        priceDifference = sharePriceList.get(j) - sharePriceList.get(i);
                        //See if the difference is a new maximum difference
                        if (priceDifference > currentMaximumDifference) {

                            currentMaximumDifference = priceDifference;

                            //remember the position and value of share price on buying and selling days for new maximum.
                            dayOfCurrentMaximumDifference = j + 1;
                            dayOfOptimisedMinimum = i + 1;
                            sharePriceOnSellDay = sharePriceList.get(j);
                            sharePriceOnBuyDay = sharePriceList.get(i);
                        } else {
                            //Price is not a new maximum. Disregard.
                            continue;
                        }
                    }

                }
            }

            System.out.println(dayOfOptimisedMinimum + "(" + sharePriceOnBuyDay + ")" + " ," + dayOfCurrentMaximumDifference + "(" + sharePriceOnSellDay + ")");

        } catch (FileNotFoundException ex) {
            System.out.println("That file does not exist.");
        }

    }

}
