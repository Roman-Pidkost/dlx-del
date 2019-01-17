package ua.com.deluxedostavka.dto.other;


import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class BASE64DecodedMultipartFile implements MultipartFile {
    private final byte[] imgContent;
    private String name;
    private String expansion;

    public BASE64DecodedMultipartFile(byte[] imgContent,String expansion,String name ) {
        this.imgContent = imgContent;
        this.expansion = expansion;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return name+"."+expansion;
    }


    @Override
    public String getContentType()
    {
        return null;
    }

    @Override
    public boolean isEmpty()
    {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize()
    {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException
    {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException
    {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException
    {
        new FileOutputStream(dest).write(imgContent);
    }
}
