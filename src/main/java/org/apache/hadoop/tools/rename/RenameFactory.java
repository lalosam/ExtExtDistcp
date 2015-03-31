package org.apache.hadoop.tools.rename;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;

/**
 * Created by lobo on 10/19/14.
 */
public class RenameFactory {

    private static Log LOG = LogFactory.getLog(org.apache.hadoop.tools.rename.RenameFactory.class);


    public static Rename getRenameClass(Configuration conf){
        try {
            String renameClassName = conf.get("distcp.rename.class");
            if(renameClassName == null){
                renameClassName = "org.apache.hadoop.tools.rename.IdentityName";
            }
            LOG.info("Instantiating rename class [" + renameClassName + "]");

            Class<?> clazz = Class.forName(renameClassName);
            Rename rename = (Rename)clazz.newInstance();
            rename.setConf(conf);
            return rename;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
