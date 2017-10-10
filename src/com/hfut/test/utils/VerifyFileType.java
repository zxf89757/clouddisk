package com.hfut.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class VerifyFileType {
    
    public final static Map<String, Integer> FILE_TYPE_MAP = new HashMap<String, Integer>();     
    
    private VerifyFileType(){}
    static{     
        getAllFileType(); //初始化文件类型信息     
    }     
         
    /**   
     * Discription:[getAllFileType,常见文件头信息] 
     */     
    private static void getAllFileType()     
    {     
        FILE_TYPE_MAP.put("ffd8ffe000104a464946", 1); //JPEG (jpg)     
        FILE_TYPE_MAP.put("89504e470d0a1a0a0000", 1); //PNG (png)     
        FILE_TYPE_MAP.put("47494638396126026f01", 1); //GIF (gif)     
        FILE_TYPE_MAP.put("49492a00227105008037", 1); //TIFF (tif)     
        FILE_TYPE_MAP.put("424d228c010000000000", 1); //16色位图(bmp)     
        FILE_TYPE_MAP.put("424d8240090000000000", 1); //24位位图(bmp)     
        FILE_TYPE_MAP.put("424d8e1b030000000000", 1); //256色位图(bmp)     
        FILE_TYPE_MAP.put("41433130313500000000", 1); //CAD (dwg)     
        FILE_TYPE_MAP.put("3c21444f435459504520", 5); //HTML (html)
        FILE_TYPE_MAP.put("3c21646f637479706520", 5); //HTM (htm)
        FILE_TYPE_MAP.put("48544d4c207b0d0a0942", 5); //css
        FILE_TYPE_MAP.put("696b2e71623d696b2e71", 5); //js
        FILE_TYPE_MAP.put("7b5c727466315c616e73", 5); //Rich Text Format (rtf)     
        FILE_TYPE_MAP.put("38425053000100000000", 1); //Photoshop (psd)     
        FILE_TYPE_MAP.put("46726f6d3a203d3f6762", 2); //Email [Outlook Express 6] (eml)       
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", 2); //MS Excel 注意：word、msi 和 excel的文件头一样     
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", 1); //Visio 绘图     
        FILE_TYPE_MAP.put("5374616E64617264204A", 5); //MS Access (mdb)      
        FILE_TYPE_MAP.put("252150532D41646F6265", 5);     
        FILE_TYPE_MAP.put("255044462d312e340a25", 2); //Adobe Acrobat (pdf)   
        FILE_TYPE_MAP.put("2e524d46000000120001", 3); //rmvb/rm相同  
        FILE_TYPE_MAP.put("464c5601050000000900", 3); //flv与f4v相同  
        FILE_TYPE_MAP.put("00000020667479706d70", 3); 
        FILE_TYPE_MAP.put("49443303000000096011", 4); 
        FILE_TYPE_MAP.put("000001ba210001000180", 1); //     
        FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", 3); //wmv与asf相同    
        FILE_TYPE_MAP.put("52494646e27807005741", 3); //Wave (wav)  
        FILE_TYPE_MAP.put("52494646d07d60074156", 3);  
        FILE_TYPE_MAP.put("4d546864000000060001", 3); //MIDI (mid)   
        FILE_TYPE_MAP.put("504b0304140000000800", 5);    
        FILE_TYPE_MAP.put("526172211a0700cf9073", 5);   
        FILE_TYPE_MAP.put("235468697320636f6e66", 5);   
        FILE_TYPE_MAP.put("504b03040a0000000000", 5); 
        FILE_TYPE_MAP.put("4d5a9000030000000400", 5);//可执行文件
        FILE_TYPE_MAP.put("3c25402070616765206c", 5);//jsp文件
        FILE_TYPE_MAP.put("4d616e69666573742d56", 5);//MF文件
        FILE_TYPE_MAP.put("3c3f786d6c2076657273", 5);//xml文件
        FILE_TYPE_MAP.put("494e5345525420494e54", 5);//xml文件
        FILE_TYPE_MAP.put("7061636b616765207765", 5);//java文件
        FILE_TYPE_MAP.put("406563686f206f66660d", 5);//bat文件
        FILE_TYPE_MAP.put("1f8b0800000000000000", 5);//gz文件
        FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", 5);//bat文件
        FILE_TYPE_MAP.put("cafebabe0000002e0041", 5);//bat文件
        FILE_TYPE_MAP.put("49545346030000006000", 5);//bat文件
        FILE_TYPE_MAP.put("04000000010000001300", 5);//bat文件
        FILE_TYPE_MAP.put("504b0304140006000800", 2);//docx文件
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", 2);//WPS文字wps、表格et、演示dps都是一样的
        FILE_TYPE_MAP.put("6431303a637265617465", 5);
        
        FILE_TYPE_MAP.put("3c68746d6c20786d6c6e", 5);//猎聘、智联简历。htm
        FILE_TYPE_MAP.put("46726f6d3a3cd3c920cd", 5);//51job简历。mht
          
        FILE_TYPE_MAP.put("6D6F6F76", 5); //Quicktime (mov)  
        FILE_TYPE_MAP.put("FF575043", 5); //WordPerfect (wpd)   
        FILE_TYPE_MAP.put("CFAD12FEC5FD746F", 5); //Outlook Express (dbx)     
        FILE_TYPE_MAP.put("2142444E", 5); //Outlook (pst)      
        FILE_TYPE_MAP.put("AC9EBD8F", 5); //Quicken (qdf)     
        FILE_TYPE_MAP.put("E3828596", 5); //Windows Password (pwl)         
        FILE_TYPE_MAP.put("2E7261FD", 5); //Real Audio (ram)   
    }                       
    
    /**
     * 得到上传文件的文件头
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    /**
     * 根据制定文件的文件头判断其文件类型
     * @param filePaht
     * @return
     */
    public static Integer getTypeid(File file){
        int res=5;
        try {
            FileInputStream is = new FileInputStream(file);
            byte[] b = new byte[10];
            is.read(b, 0, b.length);
            String fileCode = bytesToHexString(b);    
            
            System.out.println(fileCode);
            
            
            //这种方法在字典的头代码不够位数的时候可以用但是速度相对慢一点
            Iterator<String> keyIter = FILE_TYPE_MAP.keySet().iterator();
            while(keyIter.hasNext()){
                String key = keyIter.next();
                if(key.toLowerCase().startsWith(fileCode.toLowerCase()) || fileCode.toLowerCase().startsWith(key.toLowerCase())){
                    res = FILE_TYPE_MAP.get(key);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public static String savaFile(String root,CommonsMultipartFile file) throws Exception {
    	String uploadFileName=file.getOriginalFilename();
    	int hCode=uploadFileName.hashCode();
		String hex=Integer.toHexString(hCode);
		String savename = UUIDUtils.getUUID()+"_"+uploadFileName;
		String path = root+"/"+ hex.charAt(0)+"/"+hex.charAt(1)+"/"+savename;
		File dirFile=new File(root, hex.charAt(0)+"/"+hex.charAt(1));
		dirFile.mkdirs();
		File newFile=new File(path);
		file.transferTo(newFile);
		return hex.charAt(0)+"/"+hex.charAt(1)+"/"+savename;
	}
}