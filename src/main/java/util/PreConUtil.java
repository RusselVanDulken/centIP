package util;

import model.InfoEntity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 7w1st22
 * @package_name util    创建新文件的包的名称
 * @date 2022/2/18	当前系统日期
 * @time 13:48	当前系统时间
 */
public class PreConUtil {

    public static String path = "src/main/resources/test/ifcfg-";

    public static void Prevention(String file) throws IOException {
        String[] filename_all={"ge1","ge2","ge3"};
        //  path+file     src/main/resources/test/ifcfg-  ge1
        for(String checkfile:filename_all){
            if(!file.equals(checkfile)){
                String pathip= getProperty(path+file,"IPADDR");
                String otherip=getProperty(path+filename_all,"IPADDR");
                if(pathip.equals(otherip)){
                    Initialize(file);
                }
            }
        }
    }

    private static void Initialize(String file) throws IOException {
        Runtime.getRuntime().exec("nmcli dev disconnect iface "+file);
    }

    public static String getProperty(String confpath,String property){
        BufferedReader br ;
        property=property.toUpperCase();
        String line ;
        String result = new String();
        try{
            br = new BufferedReader(new FileReader(confpath));
            while ((line = br.readLine()) != null) {
                if(line.substring(0, line.indexOf("=")).equals(property)){
                    result=line.substring(line.indexOf("=") + 1);
                }else{
                    result = "notexisted";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
