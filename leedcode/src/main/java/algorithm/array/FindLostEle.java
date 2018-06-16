package algorithm.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 最简便方法： 0到n+1求和减去当前数组的和即可，时间复杂度O（n）
public class FindLostEle {
    public static void main(String []args){
        Integer []a = {5,6,0,1,2,3};
        System.out.println(find(a));
    }
    private static int find(Integer[] array){
        int n = array.length;
        if(n <= 1){
            throw new IllegalArgumentException("length must >1" );
        }
        List<Integer> list = Arrays.asList(array);
        Collections.sort(list);
        int i=0;
        while(i<=n){
            if(list.get(i+1)-list.get(i) >0 && list.get(i+1)-list.get(i) > 1){
                return (list.get(i+1)-1);
            }else if (list.get(i+1)-list.get(i) <0 &&list.get(i) - list.get(i+1) > 1){
                return list.get(i)-1;
            }
            i++;

        }
        return -1;
    }

}
