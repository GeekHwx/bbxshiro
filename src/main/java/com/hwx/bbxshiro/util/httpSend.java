package com.hwx.bbxshiro.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public  class  httpSend {

    //接收没有加密
    public static String sendGet(String url, String param) {
        String s = sendGet(url, param, null);
        return s;
    };
    //接收gzip格式的
    public static String sendGet(String url,String param,String type) {
        String result = "";
        BufferedReader in = null;
        StringBuffer sb=null;
        InputStream stream=null;
        BufferedReader reader=null;
        try {
            String urlNameString ="";
            if(param==null){
                urlNameString = url;
            }else{
                urlNameString = url + "?" + param;
            }

            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("Accept-Charset", "utf-8");
//            connection.setRequestProperty("ContentType", "utf-8");
            connection.setRequestProperty("content-type", "text/javascript;charset=utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            if("gzip".equals(type)){
                 stream = new GZIPInputStream(connection.getInputStream());
                 reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
                sb = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    sb.append(line);
                }
            }else{
                sb = new StringBuffer();
                //             定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),"utf-8"));
                String line;
                while ((line = in.readLine()) != null){
                    sb.append(line);
                }
            }

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if(reader!=null){
                    reader.close();
                }
                if(stream!=null){
                    stream.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }
    public static String sendPost(String url, String param) {
        OutputStream outd = null;
        OutputStreamWriter outE = null;
        InputStream inputd = null;
        BufferedReader in = null;
        InputStreamReader inreader = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("ContentType", "utf-8");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            outd = conn.getOutputStream();
            outE = new OutputStreamWriter(outd, "utf-8");
            outE.write(param);
            outE.flush();
            inputd = conn.getInputStream();
            inreader = new InputStreamReader(inputd, "utf-8");

            String line;
            for(in = new BufferedReader(inreader); (line = in.readLine()) != null; result = result + line) {
                ;
            }
        } catch (Exception var20) {
            System.out.println("发送 POST 请求出现异常！" + var20);
            var20.printStackTrace();
        } finally {
            try {
                if (outE != null) {
                    outE.close();
                }

                if (in != null) {
                    in.close();
                }

                if (inputd != null) {
                    inputd.close();
                }

                if (outd != null) {
                    outd.close();
                }

                if (inreader != null) {
                    inreader.close();
                }
            } catch (IOException var19) {
                var19.printStackTrace();
            }

        }

        return result;
    }
    public static String sendGetByType(String url,String param) {
        String result = "";
        BufferedReader in = null;
        StringBuffer sb=null;
        InputStream stream=null;
        BufferedReader reader=null;
        try {
            String urlNameString ="";
            if(param==null){
                urlNameString = url;
            }else{
                urlNameString = url + "?" + param;
            }

            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("Accept-Charset", "utf-8");
//            connection.setRequestProperty("ContentType", "utf-8");
            connection.setRequestProperty("content-type", "text/javascript;charset=utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            return map.get("Content-Type").toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if(reader!=null){
                    reader.close();
                }
                if(stream!=null){
                    stream.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }

}
