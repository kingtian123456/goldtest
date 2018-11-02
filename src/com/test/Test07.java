package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月26日 下午5:58:42
* 类说明
*/
public class Test07 {
	
	public static List<String> getImageSrc(String htmlCode) {  
        List<String> imageSrcList = new ArrayList<String>();  
        Pattern p = Pattern.compile("\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\.mp4|\\.avi|\\.flv|\\.mpg|\\.wav)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);  
        //Pattern p = Pattern.compile("\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\.mp4|\\.avi|\\.flv|\\.mpg|\\.wav)\\b)[^>]*>");
        Matcher m = p.matcher(htmlCode);  
        String quote = null;  
        String src = null;  
        while (m.find()) {  
            quote = m.group(1);  
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);  
            imageSrcList.add(src);  
        }  
        return imageSrcList;  
    }  
	
	public static void main(String[] args) {
		String mi = "<p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/goldtest/utf8-jsp/jsp/upload/video/1530007742411036262.mp4\" data-setup=\"{}\"></video><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/goldtest/utf8-jsp/jsp/upload/video/1530007742411036262.mp4\" data-setup=\"{}\"></video></p>";
		String dd = "<p><img src='/项目名称/ueditor/jsp/upload/image/20170515/1494837823710043270.jpg' title='1494837823710043270.jpg' alt='sqjy.jpg' ><img src='/项目名称/ueditor/jsp/upload/image/20170515/14944632630043270.jpg' title='1494837823710043270.jpg' alt='sqjy.jpg' ></p>";
		List<String> imageSrcList = getImageSrc(mi);
		for (String str : imageSrcList) {
			System.err.println(str);
		}
	}
}
