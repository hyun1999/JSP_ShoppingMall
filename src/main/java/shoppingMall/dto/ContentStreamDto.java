package shoppingMall.dto;

import shoppingMall.domain.Content;

import java.io.File;
import java.io.InputStream;

public class ContentStreamDto {
    private final Content content;
    private final File file;
    private final InputStream inputStream;

    public ContentStreamDto(Content content, File file, InputStream inputStream) {
        this.content = content;
        this.file = file;
        this.inputStream = inputStream;
    }

    public Content getContent() {
        return content;
    }

    public File getFile() {
        return file;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
