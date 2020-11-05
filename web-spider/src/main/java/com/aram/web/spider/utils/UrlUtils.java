package com.aram.web.spider.utils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * The url utils helper class.
 * @author aram
 */
public class UrlUtils {
    
    /**
     * Get domain name without exceptions.
     * @param url
     * @return
     */
    public static String getDomainNameNoException(String url) {
        try {
            return getDomainName(url);
        } catch (URISyntaxException ignored) { }

        return null;
    }

    /**
     * Get domain name by url string.
     * @param url
     * @return
     * @throws URISyntaxException
     */
    public static String getDomainName(String url) throws URISyntaxException {
        return getDomainName(new URI(url));
    }

    /**
     * Get domain name by URI.
     * @param uri
     * @return
     * @throws URISyntaxException
     */
    public static String getDomainName(URI uri) throws URISyntaxException {
        String domain = uri.getHost();
        return domain != null && domain.startsWith("www.") ? domain.substring(4) : domain;
    }
    
    /**
     * Make full url.
     * ToDo add other cases.
     * @param domain
     * @param url
     * @return
     */
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
