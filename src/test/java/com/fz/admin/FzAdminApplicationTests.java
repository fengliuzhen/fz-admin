package com.fz.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.event.ItemEvent;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FzAdminApplicationTests {

    @Test
    public void contextLoads() {
        /*int[] array={1,9,30,8,2,500,23,98,70,18};
        //冒泡排序
        for(int i=0;i<array.length-1;i++)
        {
            int tmp=0;
            for(int k=0;k<array.length-1-i;k++)
            {
                tmp=array[k];
                if(array[k]<array[k+1])
                {
                    array[k]=array[k+1];
                    array[k+1]=tmp;
                }
            }
        }
        //打印结果
        System.out.println(Arrays.toString(array));*/

        //iterator使用
        //List<String> list=new ArrayList<String>();
        List<String> list=new ArrayList<String>();
        list.add("Tom");
        list.add("Bob");
        list.add("Joy");

        list.add(2,"may");

        list.set(0,"Tomee");
        //排序
        //list.sort(String::compareTo);
        //Collections.sort(list);

        Iterator iterator=list.iterator();
        while(iterator.hasNext())
        {
            Object object=iterator.next();
            System.out.println(object);
        }
    }

}
