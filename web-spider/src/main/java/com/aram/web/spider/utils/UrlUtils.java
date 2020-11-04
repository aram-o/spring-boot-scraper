package com.aram.web.spider.utils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author aram
 */
public class UrlUtils {
    
    public static String getDomainNameNoException(String url) {
        try {
            return getDomainName(url);
        } catch (URISyntaxException ignored) { }

        return null;
    }

    public static String getDomainName(String url) throws URISyntaxException {
        return getDomainName(new URI(url));
    }

    public static String getDomainName(URI uri) throws URISyntaxException {
        String domain = uri.getHost();
        return domain != null && domain.startsWith("www.") ? domain.substring(4) : domain;
    }
    
    public static String makeFullUrl(String domain, String url) {
        
        if( domain == null || domain.equals("") ) {
            return null;
        }
        
        if ( !domain.matches("^\\w+?://.*") ) {
            domain = "http://" + domain;
        }
        
        if( url == null ) {
            return domain;
        }
        
        return domain + (url.startsWith("/") ? url : ("/" + url));

    }
    
}
