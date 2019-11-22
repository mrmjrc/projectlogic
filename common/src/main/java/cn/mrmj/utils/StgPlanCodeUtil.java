package cn.mrmj.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * create by: mrmj
 * description: 生成6位英文+字母不重复验证码
 * create time: 2019/11/21 18:33
 */
public class StgPlanCodeUtil {

    //调用此方法即可,set来存，保证不重复
    public static String getVCode(){
        Set<String> store = new HashSet<>();
        do{
            store = getletterandnum(6);
        }while(store.size() != 6);
        String code = printSet(store);
        return code;
    }

    public static Set<String> getletterandnum(int length) {
        Set<String> set =new HashSet<String>();
        for (int i = 0; i < length; i++) {
            String value = getrandom();
            set.add(value);
        }
        //如果没有生成6位
        if (set.size()<length) {
            //继续调用生成随机数的方法
            String value = getrandom();
            set.add(value);
        }
        return set;
    }


    //生成随机字母和数字方法
    private static String getrandom() {
        String value = "";
        Random random = new Random();
        //0、1、2
        int gen = random.nextInt(2);
        String charornum = gen % 2 == 0 ? "char" : "num";
        if ("char".equals(charornum)) {
            int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
            int ascii = random.nextInt(26);
            value += (char) (ascii + temp);
        } else if ("num".equalsIgnoreCase(charornum)) {
            value += String.valueOf(random.nextInt(10));
        }
        return value;
    }


    //打印set的方法
    public static String printSet(Set set){
        Iterator iterator = set.iterator();
        String code = "";
        while (iterator.hasNext()) {
            String ele = (String) iterator.next();
            code += ele;
        }
        return code;
    }

    public static void main(String[] args) {
        String utiles = getVCode();
        System.out.println(utiles);
    }
}
