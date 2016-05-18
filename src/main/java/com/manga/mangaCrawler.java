package com.manga;

/**
 * Created by hliu on 5/18/2016.
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
class Crawler {
    public void crawl(String url){
        for(int i=1016;i<9999;++i){
            for(int j=0;j<50000;++j){
                for(int k=0;k<9999;++k){
                   // String url="http://hicomic.bbhou.com/comic/1/"+String.valueOf(i)+"/"+String.valueOf(j)+"/"+String.valueOf(k)+".jpg";
                    byte[] btImg = getImageFromNetByUrl(url);
                    if(btImg==null) {
                        break;
                    }
                    else{
                        String fileName = String.valueOf(k)+".jpg";
                        writeImageToDisk(btImg,String.valueOf(j), fileName);
                    }
                }
            }
        }
    }

    public void writeImageToDisk(byte[] img, String folderName,String fileName){
        try {
            File file = new File("C:\\manga"+"\\"+folderName+"\\" + fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
            System.out.println("图片已经写入到相应文件夹");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    public byte[] getImageFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
