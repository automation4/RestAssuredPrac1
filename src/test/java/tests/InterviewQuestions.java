package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class InterviewQuestions {

@Test
    public void RemoveDuplicatesfromtheSortedArray(){

        List<Integer> in = new ArrayList<Integer>();
        int arr[] = {1,1,1,3,3,5,5};
        int count =0;
        for(int i=0;i<arr.length;i++){
            if(!in.contains(arr[i])){
                in.add(arr[i]);
                count +=1;
            }
        }
        System.out.println(count);
    System.out.println(in);
    }

    @Test
    public void RemoveDuplicatesfromtheSortedArray1(){
       // int arr[] = {1,1,1,3,3,5,5};
        int arr[] = {5,5,10,10,30};

        int count =1;
        int checkint = arr[0];

        for(int i=0;i<arr.length;i++){
            if(!(arr[i]==checkint)){
                checkint = arr[i];
                count +=1;

            }
        }
        System.out.println(count);
    }
}