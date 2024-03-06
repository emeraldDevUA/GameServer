package org.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public interface BaseTask extends Serializable {

    void execute(InputStream is, OutputStream os);

}

