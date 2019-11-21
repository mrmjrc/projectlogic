package cn.mrmj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * create by: mrmj
 * description: 代码行数统计
 * create time: 2019/11/21 18:39
 */
public class StatisticCodeLines {
    // Java类的数量
static long classcount = 0;
    // 空行
static long normalLines = 0;
    // 注释行
static long commentLines = 0;
    // 代码行
static long writeLines = 0;
    // 代码行
static long allLines = 0;

       public static void main(String[] args) throws Exception {
       // 目录
       File f = new File("E://IDEA//yxgt//projectlogic");
       //查找什么类型的代码，如".java"就是查找以java开发的代码量，".php"就是查找以PHP开发的代码量
       String type = ".java";
       StatisticCodeLines.treeFile(f,type);
       System.out.println("路径：" + f.getPath());
       System.out.println(type+"类数量：" + classcount);
       System.out.println("代码数量：" + writeLines);
       System.out.println("注释数量：" + commentLines);
       System.out.println("空行数量：" + normalLines);
       if(classcount==0){
              System.out.println("代码平均数量:" + 0);
       }else{
              System.out.println("代码平均数量:" + writeLines / classcount);
       }
       System.out.println("总代码行数量：" + allLines);
}

    /**
     * create by: mrmj
     * description: 查找出一个目录下所有的.java文件
     * create time: 2019/11/21 18:43
     */
    public static void treeFile(File f, String type) throws Exception {
       File[] childs = f.listFiles();
       for (int i = 0; i < childs.length; i++) {
               File file = childs[i];
               if (!file.isDirectory()) {
                       if (file.getName().endsWith(".vue") || file.getName().endsWith(".js")|| file.getName().endsWith(".css") ) {
                               classcount++;
                               BufferedReader br = null;
                               boolean comment = false;
                               br = new BufferedReader(new FileReader(file));
                               String line = "";
                               while ((line = br.readLine()) != null) {
                                      allLines++;
                                       line = line.trim();
                                   //这一行匹配以空格开头，但不是以回车符开头，但以回车符结尾
                                       if (line.matches("^[//s&&[^//n]]*$")) {
                                               normalLines++;
                                       } else if (line.startsWith("/*")
                                               //匹配以/*......*/括住的多行注释
                                                       && !line.endsWith("*/")) {
                                               commentLines++;
                                               comment = true;
                                       } else if (true == comment) {
                                               commentLines++;
                                               if (line.endsWith("*/")) {
                                                       comment = false;
                                               }
                                           //匹配以//开头的单行注释，及以/*......*/括住的单行注释
                                       } else if (line.startsWith("//") || (line.startsWith("/*")&&line.endsWith("*/"))) {
                                               commentLines++;
                                           //其他的就是代码行
                                       } else {
                                               writeLines++;
                                       }
                               }
                               if (br != null) {
                                       br.close();
                                       br = null;
                               }
                       }
               } else {
                       treeFile(childs[i],type);
               }
       }
    }
}