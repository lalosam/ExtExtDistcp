package org.apache.hadoop.tools.rename;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lobo on 10/19/14.
 */
public class RegExName extends Rename {

    private static Log LOG = LogFactory.getLog(org.apache.hadoop.tools.rename.RegExName.class);

    private Pattern pattern;
    private MessageFormat format;
    private MessageFormat noMatchFormat;
    private boolean strict;

    @Override
    public void init() {
        super.init();
        String strPattern = getConf().get("distcp.rename.pattern");
        String strFormat = getConf().get("distcp.rename.format");
        String noMatch = getConf().get("distcp.rename.no_match_format");
        strict = getConf().getBoolean("distcp.rename.strict", true);

        LOG.info("distcp.rename.pattern: [" + strPattern + "]" );
        LOG.info("distcp.rename.format: [" + strFormat + "]" );
        LOG.info("distcp.rename.no_match_format: [" + noMatch + "]" );
        LOG.info("distcp.rename.strict: [" + strict + "]" );

        pattern = Pattern.compile(strPattern);
        format = new MessageFormat(strFormat);
        if(noMatch != null){
            noMatchFormat = new MessageFormat(noMatch);
        }

    }

    @Override
    public String getNewName(String currentName){
        String currentNameNorm = currentName.substring(1);
        Matcher m = pattern.matcher(currentNameNorm);
        Object[] params = null;
        if(m.matches()){
            params = new Object[m.groupCount()];
            for(int i=1; i <= m.groupCount(); ++i){
                params[i-1] = m.group(i);
            }
            return "/" + format.format(params);
        }
        if(strict){
            throw new RuntimeException("Distcp rename exception: RegEx doesn't match [" + currentNameNorm + "]");
        }else{
            if(noMatchFormat != null){
                return "/" + noMatchFormat.format(new Object[]{currentNameNorm});
            }
            return currentName;
        }
    }

}

