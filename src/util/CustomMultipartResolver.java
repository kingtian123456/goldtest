package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CustomMultipartResolver extends CommonsMultipartResolver{
	
	private static Map<String, String> progressMap = new HashMap<String, String>();
    
    @Override
    public MultipartParsingResult parseRequest(HttpServletRequest request)      
            throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);
        final String para = request.getParameter("flag");
        ProgressListener progressListener = new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int pItems) {
                float tmp = (float)pBytesRead;
                float percentage = tmp/pContentLength*100;
                if (percentage >= 100) {
                    progressMap.put(para, "100");
                } else {
                    progressMap.put(para, percentage + "");
                }
            }
        };
        fileUpload.setProgressListener(progressListener);
        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        }
        catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
        }
        catch (FileUploadException ex) {
            throw new MultipartException("Could not parse multipart servlet request", ex);
        }
    }
  
    public String getProgress(String flag) {
        return progressMap.get(flag);
    }
}
