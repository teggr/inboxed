package dev.rebelcraft.linksapp.domain;

public class URLToStringConverter implements org.springframework.core.convert.converter.Converter<java.net.URL, String> {
    
    @Override
    public String convert(java.net.URL source) {
        return source.toString();
    }

}
