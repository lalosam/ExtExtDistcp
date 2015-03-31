package org.apache.hadoop.tools.rename;

/**
 * Created by lobo on 10/19/14.
 */
public class PrefixSufixName extends Rename {

    String prefix;
    String sufix;

    @Override
    public void init() {
        super.init();
        prefix = getConf().get("distcp.rename.prefix");
        sufix = getConf().get("distcp.rename.sufix");
    }

    @Override
    public String getNewName(String currentName) {
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        if(prefix != null){
            sb.append(prefix);
        }
        sb.append(currentName.substring(1));
        if(sufix != null){
            sb.append(sufix);
        }
        return sb.toString();
    }

}
