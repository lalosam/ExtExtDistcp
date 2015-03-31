package org.apache.hadoop.tools.rename;

/**
 * Created by lobo on 10/19/14.
 */
public class IdentityName extends Rename {

    @Override
    public String getNewName(String currentName) {
        return currentName;
    }

}