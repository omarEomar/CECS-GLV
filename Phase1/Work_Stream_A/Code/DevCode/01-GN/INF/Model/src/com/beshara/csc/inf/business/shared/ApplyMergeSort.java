package com.beshara.csc.inf.business.shared;


import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;

import java.util.Arrays;
import java.util.List;

/**
 * this class used Merge Sort Algorithm.
 * to merge to List in one list sorted
 * @author KareemSayed
 */
public class ApplyMergeSort {
    private static ApplyMergeSort instance;
    private static IKwtWorkDataTreeDTO[] templist;
    private static IKwtWorkDataTreeDTO[] sortedlist;
    private static int length;

    private ApplyMergeSort() {

    }

    public static ApplyMergeSort getInstance() {
        if (instance == null) {
            instance = new ApplyMergeSort();
        }
        return instance;
    }

    /**
     * pass unsorted list as a param 
     * @param unsortedList
     * @return Sorted List
     */
    public static List<IKwtWorkDataTreeDTO> sortList(List<IKwtWorkDataTreeDTO> unsortedList) {
        length = unsortedList.size();
        sortedlist = new IKwtWorkDataTreeDTO[length];
        sortedlist = unsortedList.toArray(sortedlist);
        templist = new IKwtWorkDataTreeDTO[length];
        doMergeSort(0, length - 1);
        // this for loop to Test Result only
//        for (IKwtWorkDataTreeDTO i : sortedlist) {
//            System.out.println(i.getCode().getKey()+" ...... "+i.getFromDate()+" ...... "+i.getUntilDate());
//            System.out.print(" ");
//        }
        List<IKwtWorkDataTreeDTO> resultSortedList = Arrays.asList(sortedlist);
        return resultSortedList;
    }

    private static void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            getInstance().mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    protected void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            templist[i] = sortedlist[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (templist[i].getFromDate().before(templist[j].getFromDate())) {
                sortedlist[k] = templist[i];
                i++;
            } else {
                sortedlist[k] = templist[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            sortedlist[k] = templist[i];
            k++;
            i++;
        }

    }
}

