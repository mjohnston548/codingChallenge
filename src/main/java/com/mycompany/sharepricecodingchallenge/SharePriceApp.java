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
            
            ArrayList<Object> daysAndPrices=positiveProfitFinder(sharePriceList);
            
            displayDaysAndPrices(daysAndPrices);
            
            
            

        } catch (FileNotFoundException ex) {
            System.out.println("That file does not exist.");
        }

    }

    /**
     * Finds the largest positive difference in an array list of doubles. 
     * Returns the optimal prices along with their corresponding days in an array list containing two arrays.
     * @param priceList
     * @return 
     */
    public static ArrayList positiveProfitFinder(ArrayList<Double> priceList) {
        double currentMaximumDifference = 0;
        double priceDifference;

        int dayOfCurrentMaximumDifference = 0;
        int dayOfOptimisedMinimum = 0;
        double sharePriceOnBuyDay = 0;
        double sharePriceOnSellDay = 0;

        int[] optimalDays= new int[2];
        double[] optimalPrices=new double[2];
        ArrayList<Object> daysAndPrices=new ArrayList<>();

        for (int i = 0; i < priceList.size() - 1; i++) {
            //Find the positive difference between every element and every element to the right of it.

            for (int j = i + 1; j < priceList.size(); j++) {

                if (priceList.get(j) <= priceList.get(i)) {
                    //Price has gone down, not up. Disregard. Not the best idea to compare doubles like this.

                } else {
                    //Take the difference of the prices
                    priceDifference = priceList.get(j) - priceList.get(i);
                    //See if the difference is a new maximum difference
                    if (priceDifference > currentMaximumDifference) {

                        currentMaximumDifference = priceDifference;

                        //remember the position and value of share price on buying and selling days for new maximum.
                        dayOfCurrentMaximumDifference = j + 1;
                        dayOfOptimisedMinimum = i + 1;
                        sharePriceOnSellDay = priceList.get(j);
                        sharePriceOnBuyDay = priceList.get(i);

                        optimalDays[0] = dayOfOptimisedMinimum;
                        optimalDays[1] = dayOfCurrentMaximumDifference;
                        
                        optimalPrices[0] = sharePriceOnBuyDay;
                        optimalPrices[1] = sharePriceOnSellDay;
                        
                        daysAndPrices.add(optimalDays);
                        daysAndPrices.add(optimalPrices);
                        

                    } else {
                        //Price is not a new maximum. Disregard.

                    }
                }

            }
        }
        return daysAndPrices;

    }
    
    /**
     * This method unpacks the given array list and outputs the contents so that the caller doesn't have to do the unpacking. 
     * Taking an array list of Object could be improved due to lack of specificity.
     * @param daysAndPrices 
     */
    public static void displayDaysAndPrices(ArrayList<Object> daysAndPrices){
        int[] optimalDays=(int[]) daysAndPrices.get(0);
        double[] optimalPrices=(double[]) daysAndPrices.get(1);
        
        System.out.println(optimalDays[0] + "(" + optimalPrices[0] + ")" + " ," + optimalDays[1] + "(" + optimalPrices[1] + ")");
        
    }

}
