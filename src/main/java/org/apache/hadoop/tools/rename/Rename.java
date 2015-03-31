package org.apache.hadoop.tools.rename;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;

import java.net.URL;

/**
 * Created by lobo on 10/19/14.
 */
public abstract class Rename implements Configurable {

    private Configuration conf;

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
        init();
    }

    @Override
    public Configuration getConf() {
        return conf;
    }

    public void init(){};
    public abstract String getNewName(String currentName);

    @Override
    public String toString() {
        ClassLoader loader = Rename.class.getClassLoader();
        URL path = loader.getResource("org/apache/hadoop/tools/rename/Rename.class");
        return getClass().getCanonicalName() + " [" + path + "]";
    }
}
