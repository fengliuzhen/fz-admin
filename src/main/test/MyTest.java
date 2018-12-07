import com.google.common.collect.Lists;
import io.netty.buffer.PoolChunkListMetric;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyTest {

    @Test
    public void test() {
        //排序算法
        int[] mpArray = {3, 2, 38, 25, 9, 59, 16};
        //1、冒泡排序
        for (int i = 0; i < mpArray.length; i++) {
            for (int j = i + 1; j < mpArray.length; j++) {
                int tmpMath = mpArray[j];
                if (mpArray[i] > tmpMath) {
                    mpArray[j] = mpArray[i];
                    mpArray[i] = tmpMath;
                }
            }
        }
        System.out.println(String.format("冒泡排序：%s", Arrays.toString(mpArray)));

        //2、插入排序
        int[] crArray = {3, 2, 38, 25, 9, 59, 16};
        for (int m = 1; m < crArray.length; m++) {
            int tmpMath = crArray[m];
            int leftIndex = m - 1;
            while (leftIndex>=0&&crArray[leftIndex]> tmpMath) {
                crArray[leftIndex+1]=crArray[leftIndex];
                leftIndex--;
            }
            crArray[leftIndex+1]=tmpMath;
        }

        System.out.println(String.format("插入排序：%s", Arrays.toString(crArray)));

        //3、选择排序 在冒泡基础上进行优化 效率比冒泡高
        int[] xzArray = {3, 2, 38, 25, 9, 59, 16};
        for (int k = 0; k < xzArray.length - 1; k++) {
            int tmpIndex = k;
            for (int h = k + 1; h < xzArray.length; h++) {
                if (xzArray[h] < xzArray[tmpIndex]) {
                    tmpIndex = h;
                }
            }
            int tmpOld = xzArray[k];
            if (xzArray[k] > xzArray[tmpIndex]) {
                xzArray[k] = xzArray[tmpIndex];
                xzArray[tmpIndex] = tmpOld;
            }
        }
        System.out.println(String.format("选择排序：%s", Arrays.toString(xzArray)));

        //贪心算法1 计算一个会议室 同一天最多可以预约几场会议,假如开始时间为早9点到21点
        Metting m1=new Metting(1,9,10);
        Metting m2=new Metting(2,9,11);
        Metting m3=new Metting(3,10,13);
        Metting m4=new Metting(4,12,20);
        Metting m5=new Metting(5,11,12);
        Metting m6=new Metting(6,15,17);
        Metting m7=new Metting(7,14,16);
        Metting m8=new Metting(8,18,20);
        Metting m9=new Metting(9,20,21);
        Metting m10=new Metting(10,10,11);
        Metting m11=new Metting(11,12,14);

        List<Metting> list= Lists.newArrayList();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);
        list.add(m6);
        list.add(m7);
        list.add(m8);
        list.add(m9);
        list.add(m10);
        list.add(m11);

        //时间范围是 早9点 到晚21点
        int startTime=9;
        int endTime=21;

        List<List<Metting>> bestList=getBestMetting(list,startTime,endTime);
        int day=1;
        for(List<Metting> listM: bestList) {
            System.out.println(String.format("第【%s】天安排：%s ",day,com.fz.admin.core.JsonCore.toJson(listM)));
            day++;
        }

        //贪心算法2 计算567元 零钱使用情况
        int money=679;
        int[] baseMoney=new int[]{1,2,5,10,20,50,100};
        //结果
        //int[] result=new int[baseMoney.length];
        StringBuilder result=new StringBuilder();

        //对应的零钱数量
        int[] countMoney={1,1,1,3,2,4,6};

        for(int i=baseMoney.length-1;i>=0;i--)
        {
            int minMoney=min(money/baseMoney[i],countMoney[i]);
            if(minMoney<=0)
            {
                continue;
            }
            money=money-(minMoney*baseMoney[i]);
            if(money>=0) {
                //result[i] = minMoney;
                result.append(String.format("%s张面额为【%s】元,",minMoney,baseMoney[i]));
            }
            else
            {
                break;
            }
        }

        System.out.println(String.format("【%s】元需要：%s ",679,result));
    }

    public int min(int m,int n)
    {
        return m>n?n:m;
    }

    public List<List<Metting>> getBestMetting(List<Metting> list,int startTime,int endTime)
    {
        List<List<Metting>> listlist=Lists.newArrayList();
        //最佳方案
        List<Metting> bestList=Lists.newArrayList();

        //将活动按照最早结束时间排序
        //list.sort(null);
        Collections.sort(list, new Comparator<Metting>() {
            @Override
            public int compare(Metting o1, Metting o2) {
                if(o1.getId()>o2.getId())
                {
                    return  1;
                }
                else if(o1.getId()==o2.getId())
                {
                    return  0;
                }
                return -1;
            }
        });


        //临时变量 上次的结束时间
        int nowTime=startTime;

        for(int i=0;i<list.size();i++)
        {
            Metting metting=list.get(i);
            if(metting.getStartTime()>=nowTime&&metting.getEndTime()<=endTime)
            {
                bestList.add(metting);
                nowTime=metting.endTime;
            }
        }
        listlist.add(bestList);
        //判断会议是否安排完
        if(bestList.size()!=list.size())
        {
            list.removeAll(bestList);
            listlist.addAll(getBestMetting(list,startTime,endTime));
        }
        return listlist;
    }
}
